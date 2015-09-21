package com.biyiklibaykus.runner.shape;

import android.opengl.GLES20;

import com.biyiklibaykus.runner.data.VertexArray;
import com.biyiklibaykus.runner.programs.ColorShaderProgram;

public class Triangle
{

    static final int COORDS_PER_VERTEX = 3;
    static final int STRIDE = COORDS_PER_VERTEX * 4;
    private final int vertexCount;

    private ColorShaderProgram mProgram;
    private VertexArray mVertexArray;
    private float[] mColor = {0f,0f,0f};

    public Triangle(float width)
    {
        float triangleCoords[] =
                {
                                          0.0f,       width, 0.0f, // top
                         -(width * 1.732f) / 2, - width / 2, 0.0f, // bottom left
                          (width * 1.732f) / 2, - width / 2, 0.0f  // bottom right
                };
        vertexCount = triangleCoords.length / COORDS_PER_VERTEX;

        mVertexArray = new VertexArray(triangleCoords);
        mProgram = new ColorShaderProgram();
    }

    public void draw(float[] mvpMatrix)
    {
        // Add program to OpenGL ES environment
        mProgram.use();

        // get handle to vertex shader's vPosition member
        int vPosition = mProgram.getvPosition();

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(vPosition);

        mVertexArray.setVertexAttripPointer(0, vPosition, COORDS_PER_VERTEX, STRIDE);

        mProgram.setvColor(mColor[0], mColor[1], mColor[2]);
        mProgram.setuMVPMatrix(mvpMatrix);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(vPosition);
    }

    public void setColor(float r, float g, float b)
    {
       mColor[0] = r;
       mColor[0] = g;
       mColor[0] = b;
    }
}
