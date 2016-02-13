package com.biyiklibaykus.runner.objects;

import android.util.Log;

import com.biyiklibaykus.runner.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by egemen on 10.09.2015.
 */
public class GameObjectArray
{
    //TODO get rid of this complicated add remove implementation.
    // implement a simple find remove and find add algorithm
    private int SIZE = 1000;

    private GameObject[] mCurrentObjects = new GameObject[SIZE];
    private GameObject[] mRemovedObjects = new GameObject[SIZE];
    private GameObject[] mNewObjects = new GameObject[SIZE];


    // store last available objects index
    int mCurrentLast = -1;
    int mNewLast = -1;
    int mRemovedLast = -1;


    public void add(GameObject gameObject)
    {
        mNewLast ++;
        mNewObjects[mNewLast] = gameObject;
    }

    public void remove(GameObject gameObject)
    {

        if(gameObject.isRemoved())
        {
            Util.log("Already removed!");
            return;
        }
        if(gameObject.getIndex() == -1)
        {
            Util.error("CHECK REMOVE");
            return;
        }

        gameObject.setRemoved(true);
        mRemovedLast ++;
        mRemovedObjects[mRemovedLast] = gameObject;
    }

    public void handleRemove()
    {
        for(int i = 0; i <= mRemovedLast; i++)
        {

            int index = mRemovedObjects[i].getIndex();
//            Util.log("Handle Remove: " + index );
            // move last object to removed object slot
            mCurrentObjects[index] = mCurrentObjects[mCurrentLast];
            mCurrentObjects[index].setIndex(index);
            // remove last object
            mCurrentObjects[mCurrentLast] = null;
            mCurrentLast --;

            // remove from removed list
            mRemovedObjects[i] = null;
        }

        mRemovedLast = -1;
    }

    public void handleAdd()
    {
        for(int i = 0; i <= mNewLast; i++)
        {
            mCurrentLast ++;
            mCurrentObjects[mCurrentLast] = mNewObjects[i];
            mCurrentObjects[mCurrentLast].initialize();
            mNewObjects[i].setIndex(mCurrentLast);
            mNewObjects[i] = null;
        }

        mNewLast = -1;

    }

    public GameObject get(int index)
    {
        return mCurrentObjects[index];
    }

    public int size()
    {
        return mCurrentLast + 1;
    }






}
