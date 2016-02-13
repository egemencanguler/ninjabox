package com.biyiklibaykus.runner.engine;

import android.view.MotionEvent;

import com.biyiklibaykus.runner.components.Vector2;
import com.biyiklibaykus.runner.data.MVPMatrix;
import com.biyiklibaykus.runner.game.Runner;
import com.biyiklibaykus.runner.objects.GameObject;
import com.biyiklibaykus.runner.objects.GameObjectArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by egemen on 10.09.2015.
 */
public abstract class Scene
{


    private GameObjectArray mGameObjectArray;
    private GameEngine mGameEngine;
    private MVPMatrix mMVPMatrix;
    private Vector2 mCameraPos;


    public Scene(GameEngine engine)
    {
        mGameObjectArray = new GameObjectArray();
        mGameEngine = engine;
        mMVPMatrix = engine.getMVPMatrix();
        mCameraPos = new Vector2(0,0);
    }

    public MVPMatrix getMVPMatrix() {
        return mMVPMatrix;
    }

    public  void initialize()
    {

    }

    public void addGameObject(GameObject object)
    {
        mGameObjectArray.add(object);
        object.setScene(this);
    }
    public void removeGameObject(GameObject object)
    {
        mGameObjectArray.remove(object);
    }


    public void update(float delta)
    {
        mGameObjectArray.handleRemove();
        mGameObjectArray.handleAdd();
        for(int i = 0; i < mGameObjectArray.size(); i ++)
        {
            GameObject o = mGameObjectArray.get(i);
            o.mTransform.prevPos.x = o.mTransform.pos.x;
            o.mTransform.prevPos.y = o.mTransform.pos.y;
            mGameObjectArray.get(i).update(delta);
            //TODO remove

        }


    }

    public void draw(MVPMatrix matrix)
    {

        for(int i = 0; i < mGameObjectArray.size(); i ++)
        {
            GameObject o = mGameObjectArray.get(i);
            matrix.setCurrentGameObject(o);
            o.draw(matrix.getMVPMatrix());
        }
    }

    public float getCameraWidth()
    {
        return mMVPMatrix.getCamWidth();
    }

    public float getCameraHeight()
    {
        return mMVPMatrix.getCamHeight();
    }




    public void setCamPosX(float x)
    {
        mCameraPos.x = x;
    }

    public void setCamPosY(float y)
    {
        mCameraPos.y = y;
    }

    public Vector2 getCameraPos() {
        return mCameraPos;
    }

    public GameEngine getGameEngine() {
        return mGameEngine;
    }

    public abstract boolean onTouchDown(float x, float y);
}
