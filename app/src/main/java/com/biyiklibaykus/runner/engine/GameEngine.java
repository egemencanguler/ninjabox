package com.biyiklibaykus.runner.engine;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.data.MVPMatrix;
import com.biyiklibaykus.runner.shape.Triangle;

/**
 * Created by egemen on 10.09.2015.
 */
public class GameEngine
{
    private float mScreenWidth;
    private float mScreenHeight;


    private Context mContext;
    private MVPMatrix mMVPMatrix;


    private boolean mInit = false;
    private Scene mCurrentScene;
    private EventHandler mEventHandler;

    public GameEngine(Context context)
    {
        mContext = context;
    }

    public void setScene(Scene scene)
    {

        mCurrentScene = scene;
        if(mInit)
        {
            mCurrentScene.initialize();
        }
    }

    public void initialize(float screenWidth, float screenHeight)
    {

        mMVPMatrix = new MVPMatrix();
        mEventHandler = new EventHandler();

        mScreenWidth = screenWidth;
        mScreenHeight = screenHeight;
        mMVPMatrix.initialize(screenWidth,screenHeight);
        mCurrentScene.initialize();
        mInit = true;
    }

    public void update(float deltaTime)
    {
        //TODO this is definitly not supposed to be like this.
        //remove while. even consider removing event handler
        while(!mEventHandler.empty())
        {
            MotionEvent event = mEventHandler.poll();
            float x = mMVPMatrix.screenToCameraX(event.getX());
            float y = mMVPMatrix.screenToCameraY(event.getY());

            mCurrentScene.onTouchDown(x, y);
        }
        mCurrentScene.update(deltaTime);
    }


    public void draw()
    {
        mCurrentScene.draw(mMVPMatrix);
    }

    public float getScreenWidth() {
        return mScreenWidth;
    }

    public float getScreenHeight() {
        return mScreenHeight;
    }

    public MVPMatrix getMVPMatrix() {
        return mMVPMatrix;
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            mEventHandler.add(event);
        }

        return true;
    }
}
