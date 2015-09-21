package com.biyiklibaykus.runner.game;

import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.components.Vector2;

/**
 * Created by egemen on 18.09.2015.
 */
public abstract class Directions
{

    public static Vector2 sGravityDir = new Vector2(0,0);

    public static void setGravityDir(int dir)
    {
        setVectorDir(sGravityDir,dir);
    }

    public static int getGravityDir()
    {
        return getVectorDir(sGravityDir);
    }


    public static final int DIR_UP = 0;
    public static final int DIR_DOWN = 1;
    public static final int DIR_LEFT = 2;
    public static final int DIR_RIGHT = 3;

    public static int getVectorDir(Vector2 v)
    {
        if(v.x == 0 && v.y == 1)
        {
            return DIR_UP;
        }else if(v.x == 0 && v.y == -1)
        {
            return DIR_DOWN;
        }else if(v.y == 0 && v.x == 1)
        {
            return DIR_RIGHT;
        }else if (v.y == 0 && v.x == -1)
        {
            return DIR_LEFT;
        }else
        {
            Util.log("Check Vector");
            return -1;
        }
    }

    public static void setVectorDir(Vector2 v, int dir)
    {
        switch (dir)
        {
            case DIR_UP:
                v.x = 0;
                v.y = 1;
                break;
            case DIR_DOWN:
                v.x = 0;
                v.y = -1;
                break;
            case DIR_LEFT:
                v.x = -1;
                v.y = 0;
                break;
            case DIR_RIGHT:
                v.x = 1;
                v.y = 0;
                break;
        }
    }

    public static int opposite(int dir)
    {
        switch (dir)
        {
            case DIR_UP:
                return DIR_DOWN;
            case DIR_DOWN:
                return DIR_UP;
            case DIR_LEFT:
                return DIR_RIGHT;
            case DIR_RIGHT:
                return DIR_LEFT;
        }
        return -1;
    }
}
