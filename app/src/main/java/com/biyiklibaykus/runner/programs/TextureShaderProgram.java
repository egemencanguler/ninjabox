package com.biyiklibaykus.runner.programs;

import android.opengl.GLES20;

import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.graphic.ShaderHelper;
import com.biyiklibaykus.runner.graphic.ShaderSource;

/**
 * Created by egemen on 18.09.2015.
 */
public class TextureShaderProgram
{
    private static TextureShaderProgram sTextureShaderProgram;
    private int mProgram;

    // Uniform locations
    private int uMVPMatrix;
    private int uTextureUnit;

    // Attribute locations
    private int aPosition;
    private int aTextureCoordinates;

    public TextureShaderProgram()
    {

        if (sTextureShaderProgram == null)
        {
            mProgram = ShaderHelper.buildProgram(ShaderSource.textureVertexShader
                    , ShaderSource.textureFragmentShader);

            uMVPMatrix = GLES20.glGetUniformLocation(mProgram, ShaderSource.U_MVP_MATRIX);
            uTextureUnit = GLES20.glGetUniformLocation(mProgram, ShaderSource.U_TEXTURE_UNIT);

            aPosition = GLES20.glGetAttribLocation(mProgram, ShaderSource.A_POSITION);
            aTextureCoordinates = GLES20.glGetAttribLocation(mProgram,ShaderSource.A_TEXTURE_COOR);
            Util.log("TextureShaderProgram", "Var: "+ aPosition + " " + aTextureCoordinates);

            sTextureShaderProgram = this;
        }else
        {

            mProgram = sTextureShaderProgram.mProgram;
            uMVPMatrix = sTextureShaderProgram.uMVPMatrix;
            uTextureUnit = sTextureShaderProgram.uTextureUnit;
            aPosition = sTextureShaderProgram.aPosition;
            aTextureCoordinates = sTextureShaderProgram.aTextureCoordinates;
        }

    }


    public void use()
    {
        GLES20.glUseProgram(mProgram);
    }

    public void setTexture(int textureId)
    {
        // Set the active texture unit to texture unit 0.
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

        // Bind the texture to this unit.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);

        // Tell the texture uniform sampler to use this texture in the shader by
        // telling it to read from texture unit 0.
        GLES20.glUniform1i(uTextureUnit, 0);
    }

    public void setuMVPMatrix(float[] mvpMatrix)
    {
        // Pass the projection and view transformation to the shader
        GLES20.glUniformMatrix4fv(uMVPMatrix, 1, false, mvpMatrix, 0);
    }

    public int getaPosition() {
        return aPosition;
    }

    public int getaTextureCoordinates() {
        return aTextureCoordinates;
    }
}
