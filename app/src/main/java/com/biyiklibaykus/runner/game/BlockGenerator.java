package com.biyiklibaykus.runner.game;

import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.components.Vector2;
import com.biyiklibaykus.runner.engine.Scene;

import static com.biyiklibaykus.runner.game.Directions.*;


import java.util.Random;

/**
 * Created by egemen on 11.09.2015.
 */
public class BlockGenerator
{
    private Runner mRunner;
    private Dimensions mDimensions;
    private Scene mScene;
    private Random mRandom;




    private float timer = 0;

    private Vector2[] mBlockPosUp;
    private Vector2[] mBlockPosDown;
    private Vector2[] mBlockPosLeft;
    private Vector2[] mBlockPosRight;

    private float mSpeed;
    private float mGap;
    private int mMaxBlockSize;
    private float mInterval;


    private Vector2 mDirUp = new Vector2(0,1);
    private Vector2 mDirDown = new Vector2(0,-1);
    private Vector2 mDirLeft = new Vector2(-1,0);
    private Vector2 mDirRight = new Vector2(1,0);



    public BlockGenerator(Dimensions dimensions, Runner runner,Scene scene)
    {
        mDimensions = dimensions;
        mRunner = runner;
        mScene = scene;
        mRandom = new Random();

        generateBlockPos();

        mSpeed = dimensions.getReferenceSquareWidth() * 5;
        mGap = dimensions.getReferenceSquareWidth();
        mMaxBlockSize = 5;
        mInterval = 3;
    }

    private void generateBlockPos()
    {
        float size = mDimensions.getReferenceSquareWidth();
        float sizeHalf = size / 2;
        int column = mDimensions.getColumnNumber();
        int row = mDimensions.getRowNumber();

        mBlockPosUp = new Vector2[column];
        mBlockPosDown = new Vector2[column];
        mBlockPosLeft = new Vector2[row];
        mBlockPosRight = new Vector2[row];


        float upBound = mDimensions.getAreaBoundUpY() - sizeHalf;
        float downBound = mDimensions.getAreaBoundDownY() + sizeHalf;
        float rightBound = mDimensions.getAreaBoundRightX() - sizeHalf;
        float leftBound = mDimensions.getAreaBoundLeftX() + sizeHalf;


        for(int i  = 0; i < column; i ++)
        {
            mBlockPosUp[i] = new Vector2(leftBound + i * size , upBound);
            mBlockPosDown[i] = new Vector2( leftBound + i * size , downBound);
        }

        for(int i = 0; i < row; i ++)
        {
            mBlockPosLeft[i] = new Vector2(leftBound, downBound + i * size);
            mBlockPosRight[i] = new Vector2(rightBound, downBound + i * size);
        }


    }

    public void debug(Vector2[] posArray)
    {
        float size = mDimensions.getReferenceSquareWidth();
        for (int i = 0; i < posArray.length; i ++ )
        {
            mScene.addGameObject(new Block(mRunner,posArray[i],0,size,size,mDimensions));
        }

    }

    public void generateBlock(float delta)
    {
        timer += delta;
        if(timer >= mInterval)
        {
            timer = 0;
            mScene.addGameObject(getRandomBlock());
        }


    }

    private Block getRandomBlock()
    {

        Vector2 pos;
        pos = new Vector2(mDimensions.getAreaCenterX(),mDimensions.getAreaCenterY());
        int dir = getGravityDir();

        float width = randomWidth();
        float height = randomHeight();

        switch (dir)
        {
            case DIR_DOWN:
                pos = getRandomPos(mBlockPosUp);
                pos.y = pos.y +  height / 2 + mGap;
                break;
            case DIR_UP:
                pos = getRandomPos(mBlockPosDown);
                pos.y = pos.y -  height / 2 - mGap;
                break;
            case DIR_RIGHT:
                pos = getRandomPos(mBlockPosLeft);
                pos.x = pos.x -  width / 2 - mGap;
                break;
            case DIR_LEFT:
                pos = getRandomPos(mBlockPosRight);
                pos.x = pos.x + width / 2 + mGap;
                break;
        }

        float speed = getRandomSpeed();
        Block b = new Block(mRunner, pos, speed ,width, height,mDimensions);
        return b;
    }



    private Vector2 getRandomPos(Vector2[] posArray)
    {
        int i = mRandom.nextInt(posArray.length);
        return posArray[i].copy();
    }
    private float getRandomSpeed()
    {
        return mSpeed * (mRandom.nextInt(2) + 1);
    }




    private float randomWidth()
    {

        return mDimensions.getReferenceSquareWidth() * (mRandom.nextInt(4) + 1);
    }
    private float randomHeight()
    {
        return mDimensions.getReferenceSquareWidth() * (mRandom.nextInt(4) + 1);
    }

    private int mLevel = 0;
    public void levelUp()
    {
        mLevel ++;
        if(mLevel < 10)
        {
            mInterval -= 0.1f;
        }else if(mLevel < 20)
        {
            mInterval = 3;
            int increase = (mLevel - 10) / 2;
            mMaxBlockSize += increase;
        }else if(mLevel < 30)
        {
            int increase = (mLevel - 20) / 2;
            mMaxBlockSize -= increase;
            mInterval -= 0.2f;
        }else if(mLevel < 40)
        {
            mMaxBlockSize ++;
        }else if(mLevel < 50)
        {
            mInterval -= 0.1f;
        }




    }



















}
