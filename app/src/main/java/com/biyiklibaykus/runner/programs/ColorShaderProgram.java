package com.biyiklibaykus.runner.programs;

import android.opengl.GLES20;
import android.util.Log;

import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.graphic.ShaderHelper;
import com.biyiklibaykus.runner.graphic.ShaderSource;

/**
 * Created by egemen on 09.09.2015.
 */
public class ColorShaderProgram
{
    private static ColorShaderProgram sColorShaderProgram;
    private int mProgram;

    private int vPosition;
    private int uColor;
    private int uMVPMatrix;

    public ColorShaderProgram()
    {

        if (sColorShaderProgram == null)
        {
            mProgram = ShaderHelper.buildProgram(ShaderSource.simpleVertexShaderCode,ShaderSource.simpleFragmentShaderCode);
            vPosition = GLES20.glGetAttribLocation(mProgram, ShaderSource.V_POSITION);
            uColor = GLES20.glGetUniformLocation(mProgram, ShaderSource.U_COLOR);
            uMVPMatrix = GLES20.glGetUniformLocation(mProgram,ShaderSource.U_MVP_MATRIX);
            sColorShaderProgram = this;
        }else
        {

            mProgram = sColorShaderProgram.mProgram;
            vPosition = sColorShaderProgram.vPosition;
            uColor = sColorShaderProgram.uColor;
            uMVPMatrix = sColorShaderProgram.uMVPMatrix;
        }

    }


    public void use()
    {
        GLES20.glUseProgram(mProgram);
    }
    public void setvColor(float red, float green, float blue)
    {
        // Set color for drawing the triangle
        GLES20.glUniform4f(uColor, red, green, blue, 1.0f);
    }

    public int getvPosition() {
        return vPosition;
    }

    public void setuMVPMatrix(float[] mvpMatrix)
    {
        // Pass the projection and view transformation to the shader
        GLES20.glUniformMatrix4fv(uMVPMatrix, 1, false, mvpMatrix, 0);
    }


}
