package com.biyiklibaykus.runner.game;

import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.collision.BoxCollider;
import com.biyiklibaykus.runner.components.Vector2;
import com.biyiklibaykus.runner.objects.GameObject;
import com.biyiklibaykus.runner.shape.Rectangular;
import com.biyiklibaykus.runner.shape.Triangle;

import java.util.Random;

/**
 * Created by egemen on 11.09.2015.
 */
public class Block extends GameObject
{
    private Rectangular mRectangular;
    private BoxCollider mBoxCollider;

    private float mSpeed;
    private Runner mRunner;


    private float mWidth;
    private float mHeight;

    private Dimensions mDimensions;

    private boolean mRemovable = false;

    public Block(Runner runner, Vector2 pos, float speed, float width, float height, Dimensions dimensions)
    {
        super(pos.x, pos.y);

        mWidth = width;
        mHeight = height;

        mSpeed = speed;
        mRunner = runner;

        mRectangular = new Rectangular(width,height);
        mBoxCollider = new BoxCollider(pos.x,pos.y,width,height);

        mDimensions = dimensions;
    }


    @Override
    public void initialize()
    {


    }

    @Override
    public void update(float delta)
    {
        move(delta);

        boolean out = outOfBound();
        /*
            without removable var  block was remove immediatly after created.
             Because it is created outside the game area
        */
        if(!out)
        {
            mRemovable = true;
        }

        if(out && mRemovable)
        {
            remove();
            return;
        }
        mBoxCollider.setPos(getPos().x, getPos().y);

        if(mRunner.isCollide(mBoxCollider))
        {
            mRectangular.setColor(1,0,0);
            ((RunnerScene)getScene()).gameOver();
        }
    }

    private boolean outOfBound()
    {
        float x = getPos().x;
        float y = getPos().y;

        return x < mDimensions.getAreaBoundLeftX() - mWidth / 2 ||
                x > mDimensions.getAreaBoundRightX() + mWidth / 2 ||
                y < mDimensions.getAreaBoundDownY() - mHeight / 2 ||
                y > mDimensions.getAreaBoundUpY() + mHeight / 2;
    }

    private void move(float delta)
    {

        getPos().x += mSpeed * Directions.sGravityDir.x * delta;
        getPos().y += mSpeed * Directions.sGravityDir.y * delta;
    }


    @Override
    public void draw(float[] mvpMatrix)
    {
        mRectangular.draw(mvpMatrix);
    }




}
