package com.biyiklibaykus.runner.game;

import com.biyiklibaykus.runner.R;
import com.biyiklibaykus.runner.engine.GameEngine;
import com.biyiklibaykus.runner.engine.Scene;
import com.biyiklibaykus.runner.shape.Sprite;

/**
 * Created by egemen on 23.09.2015.
 */
public class GameOverScene extends Scene
{
    private Sprite mSprite;
    private Score mScore;

    public GameOverScene(GameEngine engine, int score)
    {
        super(engine);

        mScore = new Score(getScreenWidth()/2, getScreenHeight()/2,getScreenHeight() / 5);
        addGameObject(mScore);
        mScore.setScore(score);

    }

    @Override
    public boolean onTouchDown(float x, float y)
    {
        getGameEngine().setScene(new MainMenuScene(getGameEngine()));
        return false;
    }
}
