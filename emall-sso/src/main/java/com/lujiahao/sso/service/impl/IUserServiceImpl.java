package com.lujiahao.sso.service.impl;

import com.lujiahao.common.utils.ExceptionUtil;
import com.lujiahao.sso.dao.JedisClientDao;
import com.lujiahao.sso.domain.EDataType;
import com.lujiahao.sso.domain.UserDTO;
import com.lujiahao.sso.service.IUserService;
import com.lujiahao.common.domain.ServerResponse;
import com.lujiahao.common.utils.CookieUtils;
import com.lujiahao.common.utils.JsonUtils;
import com.lujiahao.mapping.mapper.TbUserMapper;
import com.lujiahao.mapping.pojo.TbUser;
import com.lujiahao.mapping.pojo.TbUserExample;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户管理Service
 * Created by lujiahao on 2016/10/31.
 */
@Service
public class IUserServiceImpl implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IUserServiceImpl.class);

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private JedisClientDao jedisClientDao;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    /**
     * 校验数据
     *
     * @param content 数据内容
     * @param type    数据类型
     */
    @Override
    public ServerResponse checkData(String content, Integer type) {
        // 创建查询条件
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        // 对数据进行校验 1/2/3分别代表username/phone/email
        // 用户名校验
        if (EDataType.USERNAME.getValue() == type) {
            criteria.andUsernameEqualTo(content);
        } else if (EDataType.PHONE.getValue() == type) {
            criteria.andPhoneEqualTo(content);// 手机号校验
        } else if (EDataType.EMAIL.getValue() == type) {
            criteria.andEmailEqualTo(content);// email校验
        }
        // 执行查询
        List<TbUser> list = tbUserMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return ServerResponse.success(true);
        }
        return ServerResponse.success(false);
    }

    /**
     * 创建用户
     * Service中的操作应该提供最原始的  具体的返回值的判定应该放到controller中
     */
    @Override
    public int createUser(UserDTO userDTO) {
        try {
            Date nowDate = new Date();
            TbUser tbUser = new TbUser();
            tbUser.setUsername(userDTO.getUsername());
            // spring框架中的工具类  md5加密  这个加密是用来防止内部人员的,为了不能直接看出密码来
            tbUser.setPassword(DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes()));
            tbUser.setPhone(userDTO.getPhone());
            tbUser.setEmail(userDTO.getEmail());
            tbUser.setUpdated(nowDate);
            tbUser.setCreated(nowDate);
            int resultCount = tbUserMapper.insert(tbUser);
            LOGGER.debug("========== 创建用户 成功 ==========", tbUser.toString());
            return resultCount;
        } catch (Exception e) {
            LOGGER.error("========== 创建用户 异常 ==========", e);
            return -1;
        }
    }

    /**
     * 用户登录
     */
    @Override
    public ServerResponse userLogin(String username, String password) {
        try {
            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(username);
            List<TbUser> list = tbUserMapper.selectByExample(example);
            // 如果没有此用户名  没用用户名也返回这个信息是因为防止猜测用户名
            if (list == null || list.size() == 0) {
                return ServerResponse.build(400, "用户名或密码错误");
            }
            TbUser tbUser = list.get(0);
            // 对比密码
            if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(tbUser.getPassword())) {
                return ServerResponse.build(400, "用户名或密码错误");
            }
            // 生成token
            String token = UUID.randomUUID().toString();
            // 保存用户信息前先把密码清除,为了安全起见
            tbUser.setPassword(StringUtils.EMPTY);// 这样高大上点 // tbUser.setPassword(null);

            //TODO 后面把redis搭建起来再搞 saveUserInfoToRedis(tbUser, token);

            return ServerResponse.success(token);
        } catch (Exception e){
            ExceptionUtil.getStackTrace(e);
            return ServerResponse.error("服务器异常");
        }

    }

    /**
     * 保存token到redis
     */
    private void saveUserInfoToRedis(TbUser tbUser, String token) {
        // 把用户信息写入redis
        jedisClientDao.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(tbUser));
        // 设置session过期时间
        jedisClientDao.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
    }

    /**
     * 根据token查询用户信息
     */
    @Override
    public ServerResponse getUserByToken(String token) {
        // 根据token从redis中查询用户信息
        String json = jedisClientDao.get(REDIS_USER_SESSION_KEY + ":" + token);
        if (StringUtils.isBlank(json)) {
            return ServerResponse.build(400, "此Session已经过期,请重新登录");
        }
        // 更新过期时间
        jedisClientDao.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 返回用户信息
        return ServerResponse.success(JsonUtils.jsonToPojo(json, TbUser.class));
    }
}
