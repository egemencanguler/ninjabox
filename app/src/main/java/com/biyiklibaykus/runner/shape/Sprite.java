package com.biyiklibaykus.runner.shape;

import android.opengl.GLES20;

import com.biyiklibaykus.runner.data.VertexArray;
import com.biyiklibaykus.runner.graphic.TextureHelper;
import com.biyiklibaykus.runner.programs.ColorShaderProgram;
import com.biyiklibaykus.runner.programs.TextureShaderProgram;

/**
 * Created by egemen on 18.09.2015.
 */
public class Sprite
{
    private float mWidth;
    private float mHeight;

    static final int POSITION_COMPONENT_COUNT = 2;
    static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT
            + TEXTURE_COORDINATES_COMPONENT_COUNT) * 4;

    private final int vertexCount;

    private TextureShaderProgram mProgram;
    private VertexArray mVertexArray;
    private int mTextureId;

    public Sprite(float width, float height, int drawableId)
    {
        mWidth = width;
        mHeight = height;

        float wh = width / 2;
        float hh = height / 2;

        float vertexCoords[] =
                {
                        -wh, hh,  0, 0, // top left
                         wh, hh,  1, 0, // top right
                        -wh,-hh,  0, 1, // bottom left
                         wh,-hh,  1, 1 // bottom right
                };



        vertexCount = 4;
        mVertexArray = new VertexArray(vertexCoords);

        mProgram = new TextureShaderProgram();

        mTextureId = TextureHelper.loadTexture(drawableId);
    }

    public void draw(float[] mvpMatrix)
    {

        // Add program to OpenGL ES environment
        mProgram.use();

        // get handle to vertex shader's aPosition member
        int aPosition = mProgram.getaPosition();
        mVertexArray.setVertexAttripPointer(0, aPosition, POSITION_COMPONENT_COUNT, STRIDE);

        int aTextureCoor = mProgram.getaTextureCoordinates();
//        mVertexArray.setVertexAttripPointer(0, aTextureCoor, POSITION_COMPONENT_COUNT, STRIDE);
        mVertexArray.setVertexAttripPointer(POSITION_COMPONENT_COUNT
                ,aTextureCoor,TEXTURE_COORDINATES_COMPONENT_COUNT,STRIDE);


        mProgram.setTexture(mTextureId);
        mProgram.setuMVPMatrix(mvpMatrix);


        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, vertexCount);


        // Disable vertex array
        GLES20.glDisableVertexAttribArray(aPosition);
    }

    public float getWidth() {
        return mWidth;
    }

    public float getHeight() {
        return mHeight;
    }
}
