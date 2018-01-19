package com.youshibi.app.util;

import android.content.SharedPreferences;

import com.youshibi.app.AppContext;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Aoba Suzukaze on 2017-10-19/
 * ====
 * SharedPreferencesUtil.
 */

public class SharedPreferencesUtil {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static SharedPreferences.Editor getEditor() {
        return editor;
    }

    public static void init() {
        sharedPreferences = AppContext.context()
                .getSharedPreferences("data", MODE_PRIVATE);
        editor = AppContext.context()
                .getSharedPreferences("data", MODE_PRIVATE).edit();
    }
}
