package com.biyiklibaykus.runner.game;

import android.opengl.Matrix;

import com.biyiklibaykus.runner.R;
import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.components.Vector2;
import com.biyiklibaykus.runner.data.MVPMatrix;
import com.biyiklibaykus.runner.engine.GameEngine;
import com.biyiklibaykus.runner.engine.Scene;
import com.biyiklibaykus.runner.objects.GameObject;
import com.biyiklibaykus.runner.objects.TextureButton;

/**
 * Created by egemen on 19.09.2015.
 */
public class MainMenuScene extends Scene
{

    private TextureButton mButtonCenter;

    public MainMenuScene(GameEngine engine)
    {
        super(engine);
    }

    @Override
    public void initialize()
    {
        super.initialize();

        mButtonCenter = new TextureButton(getScreenWidth()/2,getScreenHeight()/2,300,300,R.drawable.play_button);
        addGameObject(mButtonCenter);

    }



    @Override
    public boolean onTouchDown(float x, float y)
    {



        if(mButtonCenter.contain(x,y))
        {
            getGameEngine().setScene(new RunnerScene(getGameEngine()));
        }

        return false;
    }
}
