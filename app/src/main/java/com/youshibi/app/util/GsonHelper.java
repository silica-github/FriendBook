package com.youshibi.app.util;

import com.google.gson.GsonBuilder;

/**
 * Created by KAZUMI on 2017-10-09.
 * ====
 * 带 null 处理的 Gson 解析.
 */

public class GsonHelper {

    public static Object fromJson(String s, Class clazz) {
        return new GsonBuilder().registerTypeAdapter(String.class, new StringConverter()).create().fromJson(s, clazz);
    }
}
