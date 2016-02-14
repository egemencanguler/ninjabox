package com.biyiklibaykus.runner.game;

import com.biyiklibaykus.runner.R;
import com.biyiklibaykus.runner.objects.GameObject;
import com.biyiklibaykus.runner.shape.Rectangular;
import com.biyiklibaykus.runner.shape.Sprite;

/**
 * Created by egemen on 16.09.2015.
 */
public class Background extends GameObject
{


    private Sprite mBackgroundImageHowto;
    private Sprite mBackgroundImage;
    private float mTimePassedSecond = 0;
    boolean howto = true;


    public Background(Dimensions dimensions)
    {
        super(dimensions.getAreaCenterX(), dimensions.getAreaCenterY());
        mBackgroundImage = new Sprite(dimensions.getAreaWidth(),dimensions.getAreaHeight(), R.drawable.background);
        mBackgroundImageHowto = new Sprite(dimensions.getAreaWidth(),dimensions.getAreaHeight(), R.drawable.backgroundhowto);

    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void update(float delta)
    {
        if(howto)
        {
            mTimePassedSecond += delta;
            if (mTimePassedSecond > 8) howto = false;
        }


    }

    @Override
    public void draw(float[] mvpMatrix)
    {


        if(howto)
        {
            mBackgroundImageHowto.draw(mvpMatrix);
        }else
        {
            mBackgroundImage.draw(mvpMatrix);
        }

    }

    public void setColor(float r,float g, float b)
    {
//        mRectangular.setColor(r,g,b);
    }
}
