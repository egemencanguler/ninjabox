package com.biyiklibaykus.runner;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by egemen on 30.07.2015.
 */
public abstract class Util
{
    private static final String TAG = "Util";

    private static Context sAppContext;
    public static final boolean DEBUG_ON = true;


    public static void setup(Context appContext)
    {
        sAppContext = appContext;

    }


    public static Context getAppContext()
    {
        return sAppContext;
    }



    public static void makeLongToast(String text)
    {
        Toast.makeText(sAppContext,text,Toast.LENGTH_LONG).show();
    }

    public  void makeShortToast(String text)
    {
        Toast.makeText(sAppContext,text,Toast.LENGTH_SHORT).show();
    }

    public boolean isNetworkAvailable()
    {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) sAppContext.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void log(String message)
    {
        if(DEBUG_ON)
        {
            Log.d(TAG, message);
        }
    }

    public static void error(String message)
    {
        if(DEBUG_ON)
        {
            Log.e(TAG, message);
        }

    }

    public static void log(String TAG, String message)
    {
        if(DEBUG_ON)
        {
            Log.d(TAG, message);
        }
    }


    public static void printMatrix(float[] matrix)
    {
        String out = "Matrix\n";

        for(int i = 0; i < 16; i ++)
        {
            out = out + " " +matrix[i];
            if((i + 1) % 4 == 0 )
            {
                out = out + "\n";
            }
        }
        Util.log(out);

    }

}
