package com.biyiklibaykus.runner.components;

/**
 * Created by egemen on 10.09.2015.
 */
public class Transform
{
    //TODO prev pos look again
    //TODo rewrite this class remove public members its too hard to make changes
    //TODO add copy compare functions
    public Vector2 prevPos;
    public Vector2 pos;
    public float rot;
    public Vector2 scale;
    public int layer;

    public Transform() {
        pos = new Vector2(0,0);
        prevPos = new Vector2(0,0);
        rot = 0;
        scale = new Vector2(1,1);
        layer = 5;
    }


}
