package com.biyiklibaykus.runner.data;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by egemen on 09.09.2015.
 */
public class VertexArray
{
    private static final int BYTES_PER_FLOAT = 4;
    private FloatBuffer mFloatBuffer;

    public VertexArray(float[] vertexData)
    {
        mFloatBuffer = ByteBuffer
                .allocateDirect(vertexData.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(vertexData);
    }

    public void setVertexAttripPointer(int dataOffset, int attributeLocation,
                                       int componentCount, int stride)
    {
        // bound data with shader's data
        // define the variable which shader can reach the value
        mFloatBuffer.position(dataOffset);

        GLES20.glVertexAttribPointer(attributeLocation, componentCount,
                GLES20.GL_FLOAT, false, stride, mFloatBuffer);
        GLES20.glEnableVertexAttribArray(attributeLocation);

        mFloatBuffer.position(0);
    }
}
