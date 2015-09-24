package com.biyiklibaykus.runner.game;

import android.content.Context;
import android.content.SharedPreferences;

import com.biyiklibaykus.soron.Util;
import com.biyiklibaykus.soron.game.db.QuestionDB;


public class UserManager
{
    private static UserManager sUserManager;

    private static final String PREF_NAME = "gamemanager";
    private static final String DB_VERSION = "dbversion";
    private static final String NEW_VERSION_AVAILABLE = "versioncontrol";


    private static final String SCORE = "score";
    private static final String WIN_NUMBER = "winnumber";

    private static final String SOUNDON = "soundon";



    public static UserManager get()
    {
        if(sUserManager == null)
        {
            sUserManager = new UserManager(Util.get().getAppContext());
        }
        return sUserManager;
    }



    //CLASS

    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private QuestionDB mQuestionDB;

    public UserManager(Context appContext)
    {
        mContext = appContext;
        mSharedPreferences = appContext.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        mQuestionDB = new QuestionDB(appContext);

    }


    public int getDBVersion()
    {
        int version = mSharedPreferences.getInt(DB_VERSION, -1);
        return version;
    }
    public void setDBVersion(int version)
    {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(DB_VERSION,version);
        editor.apply();
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

    public int getWinNumber()
    {
        int winNumber = mSharedPreferences.getInt(WIN_NUMBER,0);
        return winNumber;
    }

    public void setWinNumber(int number)
    {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(WIN_NUMBER,number);
        editor.apply();

    }

    public boolean getSoundOn()
    {
        return  mSharedPreferences.getBoolean(SOUNDON,true);

    }

    public void setSoundOn(boolean soundOn)
    {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(SOUNDON, soundOn);
        editor.apply();

    }


    public boolean getNewVersionAvailable()
    {
        return  mSharedPreferences.getBoolean(NEW_VERSION_AVAILABLE,true);

    }

    public void setNewVersionAvailable(boolean soundOn)
    {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(NEW_VERSION_AVAILABLE, soundOn);
        editor.apply();

    }




}
