package com.biyiklibaykus.runner.game;


import com.biyiklibaykus.runner.R;
import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.collision.BoxCollider;
import com.biyiklibaykus.runner.components.Vector2;
import com.biyiklibaykus.runner.effects.JumpParticleEffect;
import com.biyiklibaykus.runner.objects.GameObject;
import com.biyiklibaykus.runner.shape.Rectangular;
import com.biyiklibaykus.runner.shape.Sprite;

import static com.biyiklibaykus.runner.game.Directions.*;

/**
 * Created by egemen on 10.09.2015.
 */
public class Runner extends GameObject
{

    private Vector2 mVelocityDir;

    private float mJump;
    private float mSpeed;
    private float mGravity;

    private float mJumpSpeed = 0;

    private boolean mGround = false;

    private float mSize;
    private Sprite mNinjaSprite;
    private BoxCollider mBoxCollider;

    private float mBorderRight;
    private float mBorderLeft;
    private float mBorderUp;
    private float mBorderDown;

    private JumpParticleEffect mJumpParticleEffect;




    public Runner(float posX, float posY, Dimensions dimensions)
    {
        super(posX, posY);


        mSize = dimensions.getRunnerSize();

        mJump = dimensions.getRunnerJumpSpeed();
        mSpeed = dimensions.getRunnerSpeed();
        mGravity = dimensions.getGravity();



        mBoxCollider = new BoxCollider(posX,posY,mSize,mSize);

        mVelocityDir = new Vector2(0,0);

        setVectorDir(mVelocityDir, DIR_LEFT);

        setBorders(dimensions.getAreaBoundLeftX(),dimensions.getAreaBoundRightX()
        ,dimensions.getAreaBoundUpY(),dimensions.getAreaBoundDownY());

        mNinjaSprite = new Sprite(mSize,mSize,R.drawable.ninja);
        mJumpParticleEffect = new JumpParticleEffect(mJump,mSize/2);
    }



    private void setBorders(float left, float right, float up, float down)
    {
        mBorderLeft = left + mSize / 2;
        mBorderRight = right - mSize / 2 ;
        mBorderUp = up - mSize / 2;
        mBorderDown = down + mSize / 2;
    }

    @Override
    public void initialize()
    {



    }

    @Override
    public void update(float delta)
    {
        mJumpParticleEffect.update(delta);
        updateRotation();
        // if object go out of the screen
        // return dir else return -1
        int whereDir = outofScreen();
        if(whereDir != -1)
        {
//            Util.log("Out of screen " + whereDir);
            mJumpSpeed = 0;
            fixOutScreen(whereDir);
        }
        move(delta);
        mBoxCollider.setPos(getPos().x, getPos().y);
    }

    private int outofScreen()
    {
        Vector2 pos = getPos();
        if( pos.x < mBorderLeft)
        {
            pos.x = mBorderLeft;
            return DIR_LEFT;

        }else if(pos.x > mBorderRight )
        {
            pos.x = mBorderRight;
            return DIR_RIGHT;

        }else if(pos.y < mBorderDown)
        {
            pos.y = mBorderDown;
            return DIR_DOWN;

        }else if(pos.y > mBorderUp)
        {
            pos.y = mBorderUp;
            return DIR_UP;

        }else
        {
            return -1;
        }

    }

    private void fixOutScreen(int whereDir)
    {
        int velDir = getVectorDir(mVelocityDir);
        int gravityDir = getVectorDir(sGravityDir);

        if(whereDir == velDir)
        {
            // go out of bound because of velocity
            updateDirs();
//            Util.log("SIDE");
        }else if(whereDir == gravityDir)
        {
            // go out of bound because of gravity
            mGround = true;
            mJumpSpeed = 0;
//            Util.log("GROUND");

        }else if(whereDir == opposite(gravityDir))
        {
            mGround = true;
            mJumpSpeed = 0;
            updateDirs();
        }



    }

    private void updateDirs()
    {

        int gravityDir = getVectorDir(sGravityDir);
        int vDir = getVectorDir(mVelocityDir);

        setVectorDir(sGravityDir,vDir);

        if(gravityDir == DIR_UP)
        {// Floor up
            if(vDir == DIR_RIGHT)
            {
//                setVectorDir(mGravityDir,DIR_RIGHT);
                setVectorDir(mVelocityDir,DIR_DOWN);
            }else if(vDir == DIR_LEFT)
            {
//                setVectorDir(mGravityDir,DIR_LEFT);
                setVectorDir(mVelocityDir,DIR_DOWN);

            }else
            {
                Util.log("Check update dir");
            }

        }else if(gravityDir == DIR_DOWN)
        {// Floor down
            if(vDir == DIR_RIGHT)
            {
//                setVectorDir(mGravityDir,DIR_RIGHT);
                setVectorDir(mVelocityDir,DIR_UP);

            }else if(vDir == DIR_LEFT)
            {
//                setVectorDir(mGravityDir,DIR_LEFT);
                setVectorDir(mVelocityDir,DIR_UP);

            }else
            {
                Util.log("Check update dir");
            }

        }else if(gravityDir == DIR_LEFT)
        {// Floor left
            if(vDir == DIR_UP)
            {
//                setVectorDir(mGravityDir,DIR_UP);
                setVectorDir(mVelocityDir,DIR_RIGHT);

            }else if(vDir == DIR_DOWN)
            {
//                setVectorDir(mGravityDir,DIR_DOWN);
                setVectorDir(mVelocityDir,DIR_RIGHT);

            }else
            {
                Util.log("Check update dir");
            }

        }else if(gravityDir == DIR_RIGHT)
        {// Floor right

            if(vDir == DIR_UP)
            {
//                setVectorDir(mGravityDir,DIR_UP);
                setVectorDir(mVelocityDir,DIR_LEFT);

            }else if(vDir == DIR_DOWN)
            {
//                setVectorDir(mGravityDir,DIR_DOWN);
                setVectorDir(mVelocityDir,DIR_LEFT);

            }else
            {
                Util.log("Check update dir");
            }
        }else
        {
            Util.log("Check update dir;");
        }
    }



    private void move(float delta)
    {

        if(!mGround)
        {
            mJumpSpeed -= mGravity * delta;
        }

        Vector2 pos = getPos();
        pos.x += mVelocityDir.x * mSpeed * delta;
        pos.y += mVelocityDir.y * mSpeed * delta;

        pos.x += -sGravityDir.x * mJumpSpeed * delta;
        pos.y += -sGravityDir.y * mJumpSpeed * delta;



    }

    public void changeDir()
    {

        int vDir = getVectorDir(mVelocityDir);
        if(vDir == DIR_UP)
        {
            setVectorDir(mVelocityDir,DIR_DOWN);
        }else if(vDir == DIR_DOWN)
        {
            setVectorDir(mVelocityDir,DIR_UP);
        }else if(vDir == DIR_LEFT)
        {
            setVectorDir(mVelocityDir,DIR_RIGHT);
        }else if(vDir == DIR_RIGHT)
        {
            setVectorDir(mVelocityDir,DIR_LEFT);
        }

    }

    public void jump()
    {
        mJumpParticleEffect.reset();
        mJumpSpeed = mJump;
        mGround = false;
    }





    @Override
    public void draw(float[] mvpMatrix)
    {
        mNinjaSprite.draw(mvpMatrix);
        mJumpParticleEffect.draw(mvpMatrix);
    }

    public boolean isCollide(BoxCollider boxCollider)
    {
        if(boxCollider.isCollide(mBoxCollider))
        {
//            mRectangular.setColor(1,0,0);
            return true;
        }else
        {
//            mRectangular.setColor(0,0,1);
            return false ;
        }

    }

    private void updateRotation()
    {

        switch (opposite(getGravityDir()))
        {
            case DIR_UP:
                mTransform.rot = 0; // up
                return;
            case DIR_DOWN:
                mTransform.rot = 180; // down
                return;
            case DIR_LEFT:
                mTransform.rot = 270; // left
                return;
            case DIR_RIGHT:
                mTransform.rot = 90; // righ
                return;
        }
    }
}
