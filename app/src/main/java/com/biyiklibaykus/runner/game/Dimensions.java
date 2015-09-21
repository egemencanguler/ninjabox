package com.biyiklibaykus.runner.game;

/**
 * Created by egemen on 17.09.2015.
 */
public class Dimensions
{
    public static final float ASPECT_RATIO = 4/3;
    public static int VERTICAL_GRID_NUMBER = 30;
    private static final float JUMP_TIME = 0.5f;


    private int mRowNumber;
    private int mColumnNumber;

    private float mScreenWidth;
    private float mScreenHeight;

    private float mAreaWidth;
    private float mAreaHeight;

    private float mAreaCenterX;
    private float mAreaCenterY;

    private float mAreaBoundLeftX;
    private float mAreaBoundRightX;
    private float mAreaBoundUpY;
    private float mAreaBoundDownY;


    private float mReferenceSquareWidth;

    private float mRunnerSize;
    private float mGravity;
    private float mRunnerSpeed;
    private float mRunnerJumpSpeed;





    public Dimensions(float screenWidth, float screenHeight)
    {

        mScreenWidth = screenWidth;
        mScreenHeight = screenHeight;

        mReferenceSquareWidth = mScreenHeight / VERTICAL_GRID_NUMBER;

        //AREA BASIS
        mAreaHeight = mScreenHeight - 2 * mReferenceSquareWidth;
        mAreaWidth = mScreenHeight * ASPECT_RATIO - 2 * mReferenceSquareWidth;

        mAreaCenterX = mScreenWidth / 2;
        mAreaCenterY = mScreenHeight / 2;

        //AREA BOUNDS COOR
        mAreaBoundUpY = mAreaCenterY + mAreaHeight / 2;
        mAreaBoundDownY = mAreaCenterY - mAreaHeight / 2;

        mAreaBoundLeftX = mAreaCenterX - mAreaWidth / 2;
        mAreaBoundRightX = mAreaCenterX + mAreaWidth / 2;

        mColumnNumber = (int)(mAreaWidth / mReferenceSquareWidth);
        mRowNumber = (int)(mAreaHeight / mReferenceSquareWidth);

        mRunnerSize = mReferenceSquareWidth;

        mRunnerJumpSpeed = (mAreaHeight / 3) / JUMP_TIME;
        mGravity = mRunnerJumpSpeed / JUMP_TIME;

        mRunnerSpeed = mAreaHeight / 5f;



    }



    public float getScreenWidth() {
        return mScreenWidth;
    }

    public float getScreenHeight() {
        return mScreenHeight;
    }

    public float getAreaWidth() {
        return mAreaWidth;
    }

    public float getAreaHeight() {
        return mAreaHeight;
    }

    public float getAreaCenterX() {
        return mAreaCenterX;
    }

    public float getAreaCenterY() {
        return mAreaCenterY;
    }

    public float getAreaBoundLeftX() {
        return mAreaBoundLeftX;
    }

    public float getAreaBoundRightX() {
        return mAreaBoundRightX;
    }

    public float getAreaBoundUpY() {
        return mAreaBoundUpY;
    }

    public float getAreaBoundDownY() {
        return mAreaBoundDownY;
    }

    public float getRunnerSize() {
        return mRunnerSize;
    }

    public float getReferenceSquareWidth() {
        return mReferenceSquareWidth;
    }

    public int getRowNumber() {
        return mRowNumber;
    }

    public int getColumnNumber() {
        return mColumnNumber;
    }

    public float getGravity() {
        return mGravity;
    }

    public float getRunnerSpeed() {
        return mRunnerSpeed;
    }

    public float getRunnerJumpSpeed() {
        return mRunnerJumpSpeed;
    }
}
