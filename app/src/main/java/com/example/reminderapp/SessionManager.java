package com.example.reminderapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "LoginSession";

    private static final String KEY_LOGIN = "isLoggedIn";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SessionManager(Context context){

        preferences =
                context.getSharedPreferences(
                        PREF_NAME,
                        Context.MODE_PRIVATE
                );

        editor = preferences.edit();
    }

    public void setLogin(boolean value){

        editor.putBoolean(KEY_LOGIN, value);
        editor.apply();
    }

    public boolean isLoggedIn(){

        return preferences.getBoolean(
                KEY_LOGIN,
                false
        );
    }

    public void logout(){

        editor.clear();
        editor.apply();
    }
}