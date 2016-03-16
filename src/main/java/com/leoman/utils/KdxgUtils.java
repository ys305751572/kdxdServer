package com.leoman.utils;

import java.util.Random;

/**
 * Created by Administrator on 2016/3/16.
 */
public class KdxgUtils {

    private static final double PROBABILITY = 0.25;

    public static boolean isGetByprobability() {
        if(Math.random() < PROBABILITY){
            return true;
        }else {
            return false;
        }
    }
}
