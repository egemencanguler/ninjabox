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
import com.biyiklibaykus.runner.shape.Rectangular;

/**
 * Created by egemen on 19.09.2015.
 */
public class MainMenuScene extends Scene
{

    private TextureButton mButtonCenter;
    private TextureButton mHighScore;
    private Score mScore;

    public MainMenuScene(GameEngine engine)
    {
        super(engine);
    }

    @Override
    public void initialize()
    {
        super.initialize();
//
//        float unit = getScreenWidth() / 60;
//
//
        float unit = 800/60;
        float centerX = 800 / 2;
        float centerY = 600 / 2 - 5 * unit;


        mHighScore = new TextureButton(centerX,centerY + unit * 10,unit*20,unit*5,R.drawable.highscore);
        mScore = new Score(centerX,centerY + unit * 5, unit*5);
        mButtonCenter = new TextureButton(centerX,centerY - unit*6,unit * 15,unit * 5,R.drawable.play);


        mScore.setScore(UserDataManager.get().getScore());



        addGameObject(mButtonCenter);
        addGameObject(mScore);
        addGameObject(mHighScore);

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
