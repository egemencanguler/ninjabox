package com.biyiklibaykus.runner.engine;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.engine.GameEngine;
import com.biyiklibaykus.runner.engine.GameRenderer;
import com.biyiklibaykus.runner.engine.Scene;

public abstract class GameActivity extends AppCompatActivity
{

    private GLSurfaceView mGLSurfaceView;
    private GLSurfaceView.Renderer mRenderer;
    private boolean mRendererSet = false;
    private GameEngine mGameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Util setup
        Util.setup(getApplicationContext());

        mGLSurfaceView = new GLSurfaceView(this);
        mGameEngine = new GameEngine(this);
        mGameEngine.setScene(getStartScene(mGameEngine));
        mRenderer = new GameRenderer(this, mGameEngine);

        if(supportsEs2())
        {
            // Request an OpenGL ES 2.0 compatible context
            mGLSurfaceView.setEGLContextClientVersion(2);
            // Assign renderer
            mGLSurfaceView.setRenderer(mRenderer);
            mRendererSet = true;

            mGLSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);



        }else
        {
            Util.makeLongToast("This device does not support OpenGl ES 2..0");
        }

        setContentView(mGLSurfaceView);
    }


    private boolean supportsEs2()
    {
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 =
                configurationInfo.reqGlEsVersion >= 0x20000
                        || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                        && (Build.FINGERPRINT.startsWith("generic")
                        || Build.FINGERPRINT.startsWith("unknown")
                        || Build.MODEL.contains("google_sdk")
                        || Build.MODEL.contains("Emulator")
                        || Build.MODEL.contains("Android SDK built for x86")));

        return supportsEs2;

    }

    public abstract Scene getStartScene(GameEngine engine);

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mGameEngine.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
