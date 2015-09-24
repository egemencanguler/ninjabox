package com.biyiklibaykus.runner.game;

import android.content.Context;
import android.content.SharedPreferences;

import com.biyiklibaykus.runner.Util;


public class UserDataManager
{
    private static UserDataManager sUserManager;

    private static final String PREF_NAME = "usermanager";
    private static final String SCORE = "score";

    public static UserDataManager get()
    {
        if(sUserManager == null)
        {
            sUserManager = new UserDataManager(Util.getAppContext());
        }
        return sUserManager;
    }

    //CLASS
    private SharedPreferences mSharedPreferences;

    public UserDataManager(Context appContext)
    {
        mSharedPreferences = appContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }


    public int getScore()
    {
        int score = mSharedPreferences.getInt(SCORE, 0);
        return score;
    }

    public void setScore(int score)
    {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(SCORE,score);
        editor.apply();
    }






}
