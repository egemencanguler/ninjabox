package com.biyiklibaykus.runner;

import com.biyiklibaykus.runner.engine.GameEngine;
import com.biyiklibaykus.runner.engine.Scene;
import com.biyiklibaykus.runner.engine.GameActivity;
import com.biyiklibaykus.runner.game.MainMenuScene;
import com.biyiklibaykus.runner.game.RunnerScene;

public class MainActivity extends GameActivity
{


    @Override
    public Scene getStartScene(GameEngine engine) {
        return new MainMenuScene(engine);
    }
}
