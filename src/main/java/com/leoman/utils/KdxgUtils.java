package com.leoman.utils;

import java.util.Random;

/**
 * Created by Administrator on 2016/3/16.
 */
public class KdxgUtils {

    private static final double PROBABILITY = 0.25;
    private static final double PROBABILITY_GET = 0.9;

    public static boolean isGetByprobability() {
        if(Math.random() < PROBABILITY){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isGet() {
        if(Math.random() < PROBABILITY_GET){
            return true;
        }else {
            return false;
        }
    }

    public static String getFileName(String proId,String salemanId) {
        StringBuffer buffer = new StringBuffer("");
        buffer.append("PRODUCTID").append(proId).append("SALEMANID").append(salemanId).append(".jpg");
        return buffer.toString();
    }
}
