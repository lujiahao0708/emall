package com.lujiahao.rest.controller;

import com.lujiahao.common.pojo.EasyUITreeNode;
import com.lujiahao.rest.service.ContentCategoryService;
import com.lujiahao.rest.testaaa.BizDistrictBean;
import com.lujiahao.rest.testaaa.ComRespon;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * @author lujiahao
 * @version 1.0
 * @date 2017-06-02 17:20
 */
@Controller
@RequestMapping(value = "/")
public class TestController {
    private static final Logger LOGGER = Logger.getLogger(TestController.class);
    private static final String url = "http://192.168.1.33:8080/webservice/driver/test";


    @RequestMapping(value = "/test/{id}")
    @ResponseBody
    public Object test(@PathVariable Integer id) {
        String preHourTime = "2017-04-27_15";
        String nowTime = "2017-04-27_16";
        switch (id){
            case 1:
                preHourTime = "2017-04-27_15";
                nowTime = "2017-04-27_16";
                break;
            case 2:
                preHourTime = "2017-04-27_16";
                nowTime = "2017-04-27_17";
                break;
            case 3:
                preHourTime = "2017-04-27_17";
                nowTime = "2017-04-27_18";
                break;
        }
        Integer cityId = 44;

        ComRespon comRespon = new ComRespon();

        Map<String, Object> map = new HashMap<String, Object>();

        Map<String, Object> preBizDistrictMap = getBizDistrictData(cityId, preHourTime);
        Map<String, Object> nowBizDistrictMap = getBizDistrictData(cityId, nowTime);
        boolean preStatus = false;//(boolean) preBizDistrictMap.get("status");
        boolean nowStatus = false;//(boolean) nowBizDistrictMap.get("status");
        if (preStatus || nowStatus) {
            map.put("preBizDistrict", preStatus ? preBizDistrictMap.get("result") : "");
            map.put("nowBizDistrict", nowStatus ? nowBizDistrictMap.get("result") : "");

            comRespon = ComRespon.ok("获取城市商圈数据成功",map);
        }
        if (!preStatus && !nowStatus) {
            comRespon = ComRespon.build("220","获取城市商圈数据为空");
        }
        return comRespon;
    }

    /**
     * 获取城市商圈数据
     */
    private static Map<String, Object> getBizDistrictData(Integer cityId, String time) {
        Map<String, Object> map = new HashMap<String, Object>();
        long startTime = System.currentTimeMillis();
        try {
            LOGGER.info("******************** 司机获取城市商圈数据 调用car-api获取城市商圈数据 : begin 入参cityId : " + cityId);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("cityId", cityId);
            params.put("time", time);
            //JSONObject jsonObject = carApiTemplate.postForObject(BIZ_DISTRICT_URL, JSONObject.class, params);

            //------------for test start------------
            String s = sendPost(url, "cityId=" + cityId + "&time=" + time);
            JSONObject jsonObject = JSONObject.fromObject(s);
            //------------for test end------------

            if (jsonObject != null) {
                Boolean isSuccess = jsonObject.getBoolean("isSuccess");
                if (isSuccess) {
                    String jsonString = jsonObject.getString("result");// 从jsonobject中取出result
                    BizDistrictBean driverBizDistrictResultBean = toBean(jsonString, BizDistrictBean.class);
                    map.put("status", isSuccess);
                    map.put("msg", "获取城市商圈数据成功");
                    map.put("result", driverBizDistrictResultBean);
                    LOGGER.info("******************** 司机获取城市商圈数据 调用car-api获取城市商圈数据 result内容 : " + jsonString);
                } else {
                    map.put("status", isSuccess);
                    map.put("msg", "获取城市商圈数据为空");
                    LOGGER.info("******************** 司机获取城市商圈数据 调用car-api获取城市商圈数据 result内容为空 cityId : " + cityId);
                }
            } else {
                map.put("status", false);
                map.put("msg", "car-api返回数据为空");
            }
            //int i = 1/0;
        } catch (Exception e) {
            map.put("status", false);
            map.put("msg", "获取城市商圈数据接口异常");
            //LOGGER.error("******************** 司机获取城市商圈数据 调用car-api获取城市商圈数据异常 :" + ExceptionUtil.getFullStackTrace(e));
        } finally {
            LOGGER.info("******************** 司机获取城市商圈数据 调用car-api获取城市商圈数据 : end 共耗时:" + (System.currentTimeMillis() - startTime) + "ms");
        }
        return map;
    }

    public static <T> T toBean(Object object, Class<T> beanClass) {
        JSONObject jsonObject = JSONObject.fromObject(object);

        return (T) JSONObject.toBean(jsonObject, beanClass);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
