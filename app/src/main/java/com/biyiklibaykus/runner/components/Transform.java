package com.biyiklibaykus.runner.components;

/**
 * Created by egemen on 10.09.2015.
 */
public class Transform
{
    public Vector2 pos;
    public float rot;
    public Vector2 scale;

    public Transform() {
        pos = new Vector2(0,0);
        rot = 0;
        scale = new Vector2(1,1);
    }
}
