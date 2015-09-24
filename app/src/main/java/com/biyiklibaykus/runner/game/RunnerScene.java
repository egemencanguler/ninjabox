package com.biyiklibaykus.runner.game;



import com.biyiklibaykus.runner.R;
import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.components.Vector2;
import com.biyiklibaykus.runner.data.MVPMatrix;
import com.biyiklibaykus.runner.engine.GameEngine;
import com.biyiklibaykus.runner.engine.Scene;
import com.biyiklibaykus.runner.programs.TextureShaderProgram;
import com.biyiklibaykus.runner.shape.Rectangular;
import com.biyiklibaykus.runner.shape.Sprite;

/**
 * Created by egemen on 11.09.2015.
 */
public class RunnerScene extends Scene
{
    public RunnerScene(GameEngine engine) {
        super(engine);
    }

    private Vector2 camPos;
    private Runner mRunner;
    private Background mBackground;
    private BlockGenerator mBlockGenerator;
    private CoinGenerator mCoinGenerator;




    @Override
    public void initialize() {
        super.initialize();

        Dimensions dimensions = new Dimensions(getScreenWidth(),getScreenHeight());

        mBackground = new Background(dimensions);
        addGameObject(mBackground);

        mRunner = new Runner(getScreenWidth() / 2, getScreenHeight() / 2, dimensions);
        addGameObject(mRunner);



        mBlockGenerator = new BlockGenerator(dimensions,mRunner,this);
        mCoinGenerator = new CoinGenerator(this,mRunner,dimensions,mBlockGenerator);

        camPos = getCameraPos();
        camPos.x = getScreenWidth() / 2;
        camPos.y = getScreenHeight() / 2;

        Directions.setGravityDir(Directions.DIR_DOWN);
        Util.log("INIT");



    }

    @Override
    public void update(float delta)
    {
        super.update(delta);
        mBlockGenerator.generateBlock(delta);

    }



    boolean mControl = true;
    public boolean onTouchDown(float x, float y)
    {
        if(x < getScreenWidth() / 2)
        {
            mRunner.jump();
        }else
        {
            mRunner.changeDir();
        }



        return false;
    }

    public void gameOver()
    {
        getGameEngine().setScene(new GameOverScene(getGameEngine(),mBlockGenerator.getLevel()));

    }


}
