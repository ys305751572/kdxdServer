package com.leoman.controller.editor;

import java.beans.PropertyEditorSupport;

/**
 * Created by wangbin on 2015/7/7.
 */
public class CustomStringEditor extends PropertyEditorSupport {


    @Override
    public void setAsText(String text) {
        if (text == null) {
            setValue(null);
        } else {
            String value = text;
            value = value.replace("<","&lt").replace(">","&gt");
            setValue(value);
        }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : "";
    }
}
