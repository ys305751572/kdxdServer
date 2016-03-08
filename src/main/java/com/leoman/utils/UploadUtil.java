package com.leoman.utils;

import java.util.Calendar;

/**
 * Created by wangbin on 2014/12/6.
 */
public class UploadUtil {

    private static final String TYPE_IMAGES="images";


    public static String getImagesUpladPath(){
        Calendar now = Calendar.getInstance();
        int year=now.get(Calendar.YEAR);
        int month=now.get(Calendar.MONTH)+1;
        String uploadFilePath=TYPE_IMAGES+"/"+year+"/"+month+"/";
        return uploadFilePath;
    }

}
