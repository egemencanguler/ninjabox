package com.biyiklibaykus.runner.shape;

import android.opengl.GLES20;

import com.biyiklibaykus.runner.data.VertexArray;
import com.biyiklibaykus.runner.programs.ColorShaderProgram;

/**
 * Created by egemen on 10.09.2015.
 */
public class Rectangular
{
    static final int COORDS_PER_VERTEX = 3;
    static final int STRIDE = COORDS_PER_VERTEX * 4;
    private final int vertexCount;

    private ColorShaderProgram mProgram;
    private VertexArray mVertexArray;
    private float[] mColor;

    public Rectangular(float width, float height)
    {
        mColor = new float[3];
        setColor(0,0,1);
        float wh = width / 2;
        float hh = height / 2;

        float vertexCoords[] =
                {
                        // triangle 1
                        -wh, hh, 0.0f, // top left
                        wh, hh,0.0f, // top right
                        -wh,-hh,0.0f, // bottom left
                        wh,-hh,0.0f  // bottom right
                };
        vertexCount = vertexCoords.length / COORDS_PER_VERTEX;

        mVertexArray = new VertexArray(vertexCoords);
        mProgram = new ColorShaderProgram();
    }

    public void draw(float[] mvpMatrix)
    {
        // Add program to OpenGL ES environment
        mProgram.use();

        // get handle to vertex shader's vPosition member
        int vPosition = mProgram.getvPosition();



        mVertexArray.setVertexAttripPointer(0, vPosition, COORDS_PER_VERTEX, STRIDE);

        mProgram.setvColor(mColor[0], mColor[1], mColor[2]);
        mProgram.setuMVPMatrix(mvpMatrix);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, vertexCount);


        // Disable vertex array
        GLES20.glDisableVertexAttribArray(vPosition);
    }

    public void setColor(float r, float g, float b)
    {
        mColor[0] = r;
        mColor[1] = g;
        mColor[2] = b;
    }
}
