package com.taotao.search.test;

import java.util.Map;

/**
 * Created by lujiahao on 2016/10/24.
 */
public class TestUtils {
    public static void testUtils(){
        try {
            int i = 1/0;
        } catch (Exception e){
            System.out.println("异常发生了");
        }
    }
}
