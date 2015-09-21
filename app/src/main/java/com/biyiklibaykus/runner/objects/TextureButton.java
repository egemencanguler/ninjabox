package com.biyiklibaykus.runner.objects;

import com.biyiklibaykus.runner.components.Vector2;
import com.biyiklibaykus.runner.shape.Rectangular;
import com.biyiklibaykus.runner.shape.Sprite;

/**
 * Created by egemen on 19.09.2015.
 */
public class TextureButton extends GameObject
{
    private interface OnClick
    {
        void onClick();
    }

    private Sprite mSprite;

    public TextureButton(float posX, float posY, float width, float height, int btnTextureId)
    {
        super(posX, posY);
        mSprite = new Sprite(width,height,btnTextureId);
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
        mSprite.draw(mvpMatrix);

    }

    public boolean contain(float x, float y)
    {
        Vector2 pos = getPos();
        float height = mSprite.getHeight();
        float width = mSprite.getWidth();

        return pos.x - width/2 < x && x < pos.x + width/2 &&
                pos.y -height/2 < y && y < pos.y + height/2;
    }
}
