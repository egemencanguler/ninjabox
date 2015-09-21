package com.biyiklibaykus.runner.objects;

import com.biyiklibaykus.runner.components.Transform;
import com.biyiklibaykus.runner.components.Vector2;
import com.biyiklibaykus.runner.engine.Scene;

/**
 * Created by egemen on 10.09.2015.
 */
public abstract class GameObject
{
    private Scene mScene;

    public Transform mTransform;
    private boolean mRemoved = false;

    public GameObject(float posX, float posY)
    {
        mTransform = new Transform();
        mTransform.pos.x = posX;
        mTransform.pos.y = posY;
    }


    public abstract void initialize();
    public abstract void update(float delta);

    public abstract void draw(float[] mvpMatrix);

    public Vector2 getPos()
    {
        return mTransform.pos;
    }

    public void setPos(float x, float y)
    {
        mTransform.pos.x = x;
        mTransform.pos.y = y;
    }

    public void setScene(Scene scene) {
        mScene = scene;
    }

    public Scene getScene() {
        return mScene;
    }

    private int mIndex = -1;

    public void setIndex(int index) {
        mIndex = index;
    }

    public int getIndex() {
        return mIndex;
    }

    public void remove()
    {
        mScene.removeGameObject(this);
    }

    public void setRemoved(boolean removed) {
        mRemoved = removed;
    }

    public boolean isRemoved() {
        return mRemoved;
    }
}

