package com.biyiklibaykus.runner.game;

import com.biyiklibaykus.runner.objects.GameObject;
import com.biyiklibaykus.runner.shape.Rectangular;

/**
 * Created by egemen on 16.09.2015.
 */
public class Background extends GameObject
{


    private Rectangular mRectangular;


    public Background(Dimensions dimensions)
    {
        super(dimensions.getAreaCenterX(), dimensions.getAreaCenterY());
        mRectangular = new Rectangular(dimensions.getAreaWidth(),dimensions.getAreaHeight());
        mRectangular.setColor(0, 1, 0);
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float[] mvpMatrix) {
        mRectangular.draw(mvpMatrix);
    }

    public void setColor(float r,float g, float b)
    {
        mRectangular.setColor(r,g,b);
    }
}
