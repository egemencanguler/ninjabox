package com.biyiklibaykus.runner.engine;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.SystemClock;


import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.engine.GameEngine;
import com.biyiklibaykus.runner.shape.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;



import static android.opengl.GLES20.*;

/**
 * Created by egemen on 31.08.2015.
 */

public class GameRenderer implements GLSurfaceView.Renderer
{
    private static final String TAG = "SimpleRenderer";
    private boolean engineStart = false;

    private Context mContext;

    public GameRenderer(Context context, GameEngine gameEngine)
    {
        mContext = context;
        mGameEngine = gameEngine;
        mGameLoop = new GameLoop(mGameEngine);
    }

    private GameEngine mGameEngine;
    private GameLoop mGameLoop;



    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        // set clear color to white
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        // Set the OpenGL viewport to fill the entire surface.
        glViewport(0, 0, width, height);
        mGameEngine.initialize(width, height);
//        if(!engineStart)
//        {
//            mGameEngine.initialize(width, height);
//            engineStart = true;
//        }


    }







    @Override
    public void onDrawFrame(GL10 gl)
    {
        // Clear the rendering surface.
        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(1.0f,1.0f,1.0f,1.0f);
        glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GLES20.GL_BLEND);

//        glEnable(GL_BLEND);

        mGameLoop.loop();


    }


}
