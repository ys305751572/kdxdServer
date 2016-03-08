package com.leoman.utils.gson;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

/**
 * Created by wangbin on 2015/3/13.
 */
public class ImageInstanceCreator implements InstanceCreator<Object> {

    @Override
    public Object createInstance(Type type) {
        return new Object();
    }
}
