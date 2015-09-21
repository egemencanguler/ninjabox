package com.biyiklibaykus.runner.collision;

import com.biyiklibaykus.runner.components.Vector2;

/**
 * Created by egemen on 11.09.2015.
 */
public class BoxCollider
{
    private float x,y;
    private float width;
    private float height;

    public BoxCollider(float x, float y , float width, float height)
    {

        this.height = height;
        this.width = width;
        setPos(x,y);
    }

    public boolean isCollide(BoxCollider other)
    {
        if (this.x < other.x + other.width &&
                this.x + this.width > other.x &&
                this.y < other.y + other.height &&
                this.height + this.y > other.y)
        {
            return true;
        }

        return false;
    }

    public void setPos(float x, float y)
    {
        x = x - width/2;
        y = y - height/2;
        this.x = x;
        this.y = y;
    }
}
