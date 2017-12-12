package com.lujiahao.sso.dao;

import com.lujiahao.sso.domain.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lujiahao
 * @date 2017-12-10 下午8:49
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    int updateByPrimaryKeySelective(User record);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User userLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 校验用户名是否存在
     * @param username
     * @return
     */
    int checkUsername(String username);

    /**
     * 校验phone是否存在
     * @param phone
     * @return
     */
    int checkPhone(String phone);

    /**
     * 校验email是否存在
     * @param email
     * @return
     */
    int checkEmail(String email);
}
