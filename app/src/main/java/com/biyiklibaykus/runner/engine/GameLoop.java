package com.biyiklibaykus.runner.engine;

import android.opengl.GLES20;
import android.os.SystemClock;



public class GameLoop
{
    private GameEngine mGameEngine;
    private boolean start = false;

    public GameLoop(GameEngine engine)
    {
        mGameEngine = engine;
    }



    private final long WISHED_DT_MS = 16;
    private final float WISHED_DT_S = 0.0166666666f;

    private long previousTimeMS = 0;
    private long currentTime = 0;
    private long deltaTimeMS = 0;

    private long extraTimeMS = 0;

    public void loop()
    {
        if(!start)
        {
            previousTimeMS = SystemClock.uptimeMillis();
            start = true;
        }

        currentTime = SystemClock.uptimeMillis();
        deltaTimeMS = currentTime - previousTimeMS;
        previousTimeMS = currentTime;

        extraTimeMS += deltaTimeMS;

        while (extraTimeMS >= WISHED_DT_MS)
        {
            mGameEngine.update(WISHED_DT_S);
            extraTimeMS -= WISHED_DT_MS;
        }



        mGameEngine.draw();

        //TODO add interpolation


    }




}
