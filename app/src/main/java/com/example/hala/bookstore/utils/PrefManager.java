package com.example.hala.bookstore.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class PrefManager {

    private static final String KEY_TOKEN = "keyToken";

    public static void saveToken(Context context, String accessToken) {
        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(KEY_TOKEN, accessToken);
        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(KEY_TOKEN, null);
    }

    public static void deleteToken(Context context) {
        SharedPreferences.Editor edit =
                PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.remove(KEY_TOKEN);
        //edit.clear();
        edit.apply();
    }

}
