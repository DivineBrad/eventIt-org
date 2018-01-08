package com.example.bradj.eventitorg.Utilities;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by ajibd on 1/4/2018.
 */

public class LoginUtil {
    private static LoginUtil instance;
    public static final String PREFS_NAME = "default_preferences";

    public synchronized static LoginUtil getInstance() {
        if (instance == null) {
            instance = new LoginUtil();
        }
        return instance;
    }

    private LoginUtil() {
    }


    public boolean isLoggedIn(@NonNull Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getBoolean("LOGGED", false);
    }

    public void setLoggedIn(@NonNull Context context, boolean value) {
        if(!value)
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().remove("user id");
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putBoolean("LOGGED", value).apply();
    }

    public void createUserSession(@NonNull Context context, long id){
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putLong("user id", id).apply();
        this.setLoggedIn(context, true);
    }

}
