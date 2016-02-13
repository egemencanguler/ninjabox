package com.biyiklibaykus.runner.game;

import com.biyiklibaykus.runner.R;
import com.biyiklibaykus.runner.engine.EngineConstants;
import com.biyiklibaykus.runner.engine.GameEngine;
import com.biyiklibaykus.runner.engine.Scene;
import com.biyiklibaykus.runner.objects.TextureButton;
import com.biyiklibaykus.runner.shape.Sprite;


public class GameOverScene extends Scene
{
    private TextureButton mHighScore;
    private Score mScore;

    public GameOverScene(GameEngine engine, int score)
    {
        super(engine);

        int highscore = UserDataManager.get().getScore();
        if(highscore < score)
        {
            UserDataManager.get().setScore(score);
        }

        float unit = EngineConstants.SceneWidth / 60;


        float centerX = EngineConstants.SceneWidth / 2;
        float centerY = EngineConstants.SceneHeight / 2 - unit*2;


        mHighScore = new TextureButton(centerX,centerY + unit * 10,unit*10,unit*5,R.drawable.score);
        mScore = new Score(centerX,centerY + unit * 5, unit*5);


        mScore.setScore(score);
        addGameObject(mScore);
        addGameObject(mHighScore);

    }

    @Override
    public boolean onTouchDown(float x, float y)
    {
        getGameEngine().setScene(new MainMenuScene(getGameEngine()));
        return false;
    }
}
