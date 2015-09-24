package com.biyiklibaykus.runner.game;

import com.biyiklibaykus.runner.R;
import com.biyiklibaykus.runner.objects.GameObject;
import com.biyiklibaykus.runner.shape.TileSprite;

/**
 * Created by egemen on 23.09.2015.
 */
public class Score extends GameObject
{
    private TileSprite mNumberSprite;
    private Number[] mNumbers;

    public Score(Dimensions dimensions)
    {
        this(dimensions.getAreaCenterX(),dimensions.getAreaCenterY(),dimensions.getRunnerSize());
//        super(dimensions.getAreaCenterX(), dimensions.getAreaCenterY());
//        float size = dimensions.getRunnerSize();
//
//        mNumberSprite = new TileSprite(size,1,10,R.drawable.numbers);
//
//
//        float startX = getPos().x - (size * 1.5f);
//        float posY = getPos().y;
//        mNumbers = new Number[4];
//        for(int i = 0; i < 4; i ++)
//        {
//            mNumbers[3 - i] = new Number(startX + i * size, posY);
//            mNumbers[3 - i].number = i;
//
//        }
    }

    public Score(float posX, float posY, float size)
    {

        super(posX, posY);

        mNumberSprite = new TileSprite(size,1,10,R.drawable.numbers);


        float startX = posX - (size * 1.5f);

        mNumbers = new Number[4];
        for(int i = 0; i < 4; i ++)
        {
            mNumbers[3 - i] = new Number(startX + i * size, posY);
            mNumbers[3 - i].number = i;
        }

    }

    @Override
    public void initialize() {
        getScene().addGameObject(mNumbers[0]);
        getScene().addGameObject(mNumbers[1]);
        getScene().addGameObject(mNumbers[2]);
        getScene().addGameObject(mNumbers[3]);

    }

    @Override
    public void update(float delta)
    {


    }

    @Override
    public void draw(float[] mvpMatrix)
    {
        mNumberSprite.draw(mvpMatrix);


    }

    public void setScore(int score)
    {
        int n1 = score % 10;
        score = score / 10;
        int n2 = score % 10;
        score = score / 10;
        int n3 = score % 10;
        int n4 = score / 10;

        mNumbers[0].number = n1;
        mNumbers[1].number = n2;
        mNumbers[2].number = n3;
        mNumbers[3].number = n4;
    }

    private class Number extends GameObject
    {

        int number;
        public Number(float posX, float posY)
        {
            super(posX, posY);
        }

        @Override
        public void initialize() {

        }

        @Override
        public void update(float delta) {


        }

        @Override
        public void draw(float[] mvpMatrix)
        {
            mNumberSprite.setTile(0,number);
            mNumberSprite.draw(mvpMatrix);

        }
    }


}
