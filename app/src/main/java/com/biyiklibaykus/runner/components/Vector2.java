package com.biyiklibaykus.runner.components;

import android.util.FloatMath;

/**
 * Created by egemen on 29.09.2014.
 */
public final class Vector2
{
    private static final String TAG = "Vector2";

    public static float distance2(float x, float y, float x2, float y2)
    {
        float dx = x - x2;
        float dy = y - y2;
        return (dx * dx) + (dy * dy);
    }

    public static float distance(float x, float y, float x2, float y2)
    {
        return FloatMath.sqrt(distance2(x,y,x2,y2));
    }

    public float x;
    public float y;

    public static final Vector2 ZERO = new Vector2(0, 0);

    public Vector2() {
        super();
    }

    public Vector2(float xValue, float yValue) {
        set(xValue, yValue);
    }

    public Vector2(Vector2 other) {
        set(other);
    }

    public final void add(Vector2 other) {
        x += other.x;
        y += other.y;
    }

    public final void add(float otherX, float otherY) {
        x += otherX;
        y += otherY;
    }

    public final void subtract(Vector2 other) {
        x -= other.x;
        y -= other.y;
    }

    public final void multiply(float magnitude) {
        x *= magnitude;
        y *= magnitude;
    }

    public final void multiply(Vector2 other) {
        x *= other.x;
        y *= other.y;
    }

    public final void divide(float magnitude) {
        if (magnitude != 0.0f) {
            x /= magnitude;
            y /= magnitude;
        }
    }

    public final void set(Vector2 other) {
        x = other.x;
        y = other.y;
    }

    public final void set(float xValue, float yValue) {
        x = xValue;
        y = yValue;
    }

    public final float dot(Vector2 other) {
        return (x * other.x) + (y * other.y);
    }

    public final float length() {
        return (float) Math.sqrt(length2());
    }

    public final float length2() {
        return (x * x) + (y * y);
    }


    //DISTANCE
    public final float distance(float ox, float oy)
    {
        return FloatMath.sqrt(distance2(ox,oy));
    }
    public final float distance(Vector2 other)
    {
        return distance(other.x,other.y);
    }
    public final float distance2(Vector2 other) {
        return distance2(other.x,other.y);
    }
    public final float distance2(float ox, float oy) {
        float dx = x - ox;
        float dy = y - oy;
        return (dx * dx) + (dy * dy);
    }
    //DISTANCE END

    public final float normalize() {
        final float magnitude = length();

        // TODO: I'm choosing safety over speed here.
        if (magnitude != 0.0f) {
            x /= magnitude;
            y /= magnitude;
        }

        return magnitude;
    }

    public final void zero() {
        set(0.0f, 0.0f);
    }

    public final void flipHorizontal(float aboutWidth) {
        x = (aboutWidth - x);
    }

    public final void flipVertical(float aboutHeight) {
        y = (aboutHeight - y);
    }

    public Vector2 copy()
    {
        return new Vector2(x, y);
    }

    @Override
    public String toString() {
        return new String("X: " + x + "Y: " + y);
    }

    public Vector2 getDir(Vector2 vec)
    {
        Vector2 dir = new Vector2(vec.x - x , vec.y - y);
        dir.normalize();
        return dir;
    }

    public double getAngle()
    {
        float tanx = y / x;
        double angle = Math.toDegrees(Math.atan(tanx));
        return angle;
    }

    public void rotate(float angle)
    {

        float rx = (float) ( (this.x * Math.cos(angle)) - (this.y * Math.sin(angle)) );
        float ry = (float) ( (this.x * Math.sin(angle)) + (this.y * Math.cos(angle)) );
        x = rx;
        y = ry;

    }

    public boolean compare(Vector2 other)
    {
        if(other.x == x && other.y == y) return true;
        return false;
    }



}
