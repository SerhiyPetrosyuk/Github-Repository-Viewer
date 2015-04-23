package com.mlsdev.serhiy.data.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Serhiy Petrosyuk on 21.04.15.
 */

public class PrefManager {

    private static SharedPreferences sSharedPreferences;
    private static final String sPrefsName = "github_prefs";

    private static final String USER_JSON = "user_json_instance";

    private PrefManager() {
    }

    public static void init(Context context){
        sSharedPreferences = context.getSharedPreferences(sPrefsName, Context.MODE_PRIVATE);
    }

    public static void saveUserJsonInstance(String json){
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(USER_JSON, json).commit();
    }

    public static String getUserJsonInstance(){
        return sSharedPreferences.getString(USER_JSON, "");
    }
}
