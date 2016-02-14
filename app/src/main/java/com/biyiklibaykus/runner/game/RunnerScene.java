package com.biyiklibaykus.runner.game;



import com.biyiklibaykus.runner.R;
import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.components.Vector2;
import com.biyiklibaykus.runner.data.MVPMatrix;
import com.biyiklibaykus.runner.engine.EngineConstants;
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

        Dimensions dimensions = new Dimensions(EngineConstants.SceneWidth,EngineConstants.SceneHeight);




        mRunner = new Runner(EngineConstants.SceneWidth / 2, EngineConstants.SceneHeight / 2, dimensions);
//        mRunner.setLayer(20);
        addGameObject(mRunner);









        mBlockGenerator = new BlockGenerator(dimensions,mRunner,this);
        mCoinGenerator = new CoinGenerator(this,mRunner,dimensions,mBlockGenerator);

        camPos = getCameraPos();
        camPos.x = EngineConstants.SceneWidth / 2;
        camPos.y = EngineConstants.SceneHeight / 2;

        Directions.setGravityDir(Directions.DIR_DOWN);
        Util.log("INIT");

        mBackground = new Background(dimensions);
        mBackground.setLayer(10);
        addGameObject(mBackground);



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
        if(x < EngineConstants.SceneWidth / 2)
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
