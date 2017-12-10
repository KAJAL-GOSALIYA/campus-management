package com.android.collegemanagementsystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Ativ on 28-Jun-16.
 */
public class SaveSharedPreference
{
    static final String PREF_USER_NAME= "username";
    static final String PREF_USER_ID="id";
    static final String PREF_FULL_NAME="fullname";


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static void setUserId(Context ctx, String id)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID,id);
        editor.commit();
    }

    public static void setFullName(Context ctx, String fullname)
    {
        SharedPreferences.Editor editor= getSharedPreferences(ctx).edit();
        editor.putString(PREF_FULL_NAME,fullname);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getUserId(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "");
    }

    public static String getFullName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_FULL_NAME,"");
    }


    public static void clearUserName(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }
}
