package com.biyiklibaykus.runner.programs;

import android.opengl.GLES20;

import com.biyiklibaykus.runner.graphic.ShaderHelper;
import com.biyiklibaykus.runner.graphic.ShaderSource;

/**
 * Created by egemen on 13.09.2015.
 */
public class ParticleShaderProgram
{
    private int mProgram;

    private int uColor;

    private int uMVPMatrix;
    private int uTime;

    private int aPosition;
    private int aDirectionVector;
    private int aParticleStartTime;


    public ParticleShaderProgram()
    {

        mProgram = ShaderHelper
                .buildProgram(ShaderSource.particleVertexShader
                        , ShaderSource.particleFragmentShader);

        // setup vertex shader var
        uMVPMatrix = GLES20.glGetUniformLocation(mProgram, ShaderSource.U_MVP_MATRIX);
        uTime = GLES20.glGetUniformLocation(mProgram, ShaderSource.U_TIME);

        aPosition = GLES20.glGetAttribLocation(mProgram, ShaderSource.A_POSITION);
        aDirectionVector = GLES20.glGetAttribLocation(mProgram, ShaderSource.A_DIRECTION_VECTOR);
        aParticleStartTime = GLES20.glGetAttribLocation(mProgram,ShaderSource.A_PARTICLE_START_TIME);

        // setup fragmenr shader var
        uColor = GLES20.glGetUniformLocation(mProgram, ShaderSource.U_COLOR);

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

    public void setUniforms(float[] matrix, float currentTime)
    {
        GLES20.glUniformMatrix4fv(uMVPMatrix, 1, false, matrix, 0);
        GLES20.glUniform1f(uTime, currentTime);
    }



    public int getaPosition() {
        return aPosition;
    }

    public int getaDirectionVector() {
        return aDirectionVector;
    }

    public int getaParticleStartTime() {
        return aParticleStartTime;
    }
}
