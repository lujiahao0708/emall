package com.lujiahao.mmall.controller.backend;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lujiahao
 * @version 1.0
 * @date 2017-08-16 19:20
 */
public class TestClass {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("1",1);
        map.put("2",2);
        test(map);
        System.out.println(map.toString());
    }

    public static Map<String,Object> test(Map<String,Object> map){
        try {
            int i = 1/0;
            map.put("hah","haha");
        } catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
