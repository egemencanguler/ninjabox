package com.biyiklibaykus.runner.game;

import android.util.Log;

import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.components.Vector2;
import com.biyiklibaykus.runner.engine.Scene;

import java.util.Random;

/**
 * Created by egemen on 21.09.2015.
 */
public class CoinGenerator implements Coin.Callback
{

    private Runner mRunner;
    private Scene mScene;
    private Dimensions mDimensions;
    private Coin mCoin;
    private BlockGenerator mBlockGenerator;

    private Vector2[] mCoinPos;

    public CoinGenerator(Scene scene, Runner runner, Dimensions dimensions, BlockGenerator blockGenerator)
    {
        super();
        mBlockGenerator = blockGenerator;
        mScene = scene;
        mRunner = runner;
        mDimensions = dimensions;
        mCoin = new Coin(mRunner,this
                ,scene.getCameraWidth()/2,scene.getCameraHeight()/2
                ,mDimensions.getReferenceSquareWidth()
        ,mDimensions.getReferenceSquareWidth());

        mScene.addGameObject(mCoin);

        int row = mDimensions.getRowNumber();
        int column = mDimensions.getColumnNumber();
        float size = mDimensions.getReferenceSquareWidth();
        int length = row * column;
        Util.log("Length:" + length);
        mCoinPos = new Vector2[length];

        int a = 0;
        for(int i = 0; i < column; i ++)
        {
            for(int j = 0; j < row; j++)
            {
                float x = mDimensions.getAreaBoundLeftX() + size/2 + size * i;
                float y = mDimensions.getAreaBoundDownY() + size/2 + size * j;
                mCoinPos[a++] = new Vector2(x,y);
            }
        }


    }

    public void onUpdate()
    {

    }

    @Override
    public void onCollision(Coin coin)
    {
        Vector2 r = randomPos();
        mCoin.setPos(r.x,r.y);
        mBlockGenerator.levelUp();


    }

    private Random mRandom = new Random();
    private Vector2 randomPos()
    {
        int i = mRandom.nextInt(mCoinPos.length);
        Util.log("Random i: " + i + ", " + mCoinPos[i].x +", " + mCoinPos[i].y);
        return mCoinPos[i];
    }
}
