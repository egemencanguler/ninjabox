package com.biyiklibaykus.runner.game;

import com.biyiklibaykus.runner.R;
import com.biyiklibaykus.runner.collision.BoxCollider;
import com.biyiklibaykus.runner.objects.GameObject;
import com.biyiklibaykus.runner.shape.Sprite;

/**
 * Created by egemen on 21.09.2015.
 */
public class Coin extends GameObject
{
    public interface Callback
    {
        void onCollision(Coin coin);
    }

    private Sprite mCoinSprite;
    private BoxCollider mBoxCollider;
    private Runner mRunner;
    private Callback mCallback;


    public Coin(Runner runner, Callback onCollision, float posX, float posY, float width, float height)
    {
        super(posX, posY);
        mCallback = onCollision;
        mRunner = runner;
        mCoinSprite = new Sprite(width,height, R.drawable.coin);
        mBoxCollider = new BoxCollider(posX,posY,mCoinSprite.getWidth(),mCoinSprite.getHeight());
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void update(float delta)
    {

        mBoxCollider.setPos(getPos().x,getPos().y);
        if(mRunner.isCollide(mBoxCollider))
        {
            mCallback.onCollision(this);
        }

    }



    @Override
    public void draw(float[] mvpMatrix) {
        mCoinSprite.draw(mvpMatrix);
    }
}
