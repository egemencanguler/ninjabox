package com.biyiklibaykus.runner.effects;

import android.opengl.GLES20;

import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.data.VertexArray;
import com.biyiklibaykus.runner.programs.ParticleShaderProgram;

import java.util.Random;

/**
 * Created by egemen on 13.09.2015.
 */
public class JumpParticleEffect
{

    private ParticleShaderProgram mParticleShaderProgram;
    private VertexArray mVertexArray;

    private int PARTICLE_NUMBER = 500;

    private static int POSITION_COMPONENT = 2; // x,y
    private static int DIRECTION_COMPONENT_COUNT = 2; // x,y
    private static int START_TIME_COMPONENT = 1; // start time

    private static int TOTAL_COMPONENT_COUNT = POSITION_COMPONENT
            + DIRECTION_COMPONENT_COUNT
            + START_TIME_COMPONENT;

    private static final int STRIDE = TOTAL_COMPONENT_COUNT * 4;

    private int VERTEX_BUFFER_LENGTH = PARTICLE_NUMBER * TOTAL_COMPONENT_COUNT;

    private float [] particleVertex;
    private float passedTime = 0;
    private float TIME_LIMIT = 25;
    private int RANGE = 25;
    private float SPEED = 5;

    private Random mRandom;

    private float mSpeedX;
    private float mSpeedY;


    public JumpParticleEffect(float speed, float runnerHeight)
    {
        mRandom = new Random();
        particleVertex = new float[VERTEX_BUFFER_LENGTH];

        mSpeedX = speed;
        mSpeedY = speed;

        Util.log("speed" + speed);



        for(int i = 0; i < PARTICLE_NUMBER; i ++)
        {
            int a = i * TOTAL_COMPONENT_COUNT;


            //POSITION
            particleVertex[a++] = 0 + runnerHeight/2;// dirX * mRandom.nextInt(RANGE); // posx
            particleVertex[a++] = 0; //-dirY * mRandom.nextInt(RANGE); // posy

            //VELOCITY
            float dirx = getRandomSign();
            float diry = -1;
            particleVertex[a++] = (dirx * mRandom.nextFloat()) * getSpeedX() ; // diry
            particleVertex[a++] = diry * getSpeedY(); // dirx

            particleVertex[a++] = 0; // startTime

        }


        mParticleShaderProgram = new ParticleShaderProgram();
        mVertexArray = new VertexArray(particleVertex);
    }

    public void update(float delta)
    {
        passedTime += delta;
    }

    public void draw(float[] mvpMatrix)
    {
        // Add program to OpenGL ES environment
        mParticleShaderProgram.use();

        int dataOffset = 0;
        int aPosition = mParticleShaderProgram.getaPosition();
        GLES20.glEnableVertexAttribArray(aPosition);
        mVertexArray.setVertexAttripPointer(dataOffset, aPosition, POSITION_COMPONENT
                , STRIDE);
        dataOffset += POSITION_COMPONENT;

        int aDirection = mParticleShaderProgram.getaDirectionVector();
        GLES20.glEnableVertexAttribArray(aDirection);
        mVertexArray.setVertexAttripPointer(dataOffset, aDirection, DIRECTION_COMPONENT_COUNT
                , STRIDE);
        dataOffset += DIRECTION_COMPONENT_COUNT;

        int aStartTime = mParticleShaderProgram.getaParticleStartTime();
        GLES20.glEnableVertexAttribArray(aStartTime);
        mVertexArray.setVertexAttripPointer(dataOffset, aStartTime, START_TIME_COMPONENT
                , STRIDE);





        mParticleShaderProgram.setvColor(0.5f, 0.5f, 0.5f);
        mParticleShaderProgram.setUniforms(mvpMatrix,passedTime);


        // Draw the particle
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, PARTICLE_NUMBER);


        // Disable vertex array
        GLES20.glDisableVertexAttribArray(aPosition);
    }

    private float getRandomSign()
    {
        if(mRandom.nextBoolean())
        {
            return -1;
        }

        return 1;
    }

    private float getSpeedX()
    {
        float random = mRandom.nextFloat();
        if(mSpeedX > 0)
        {
            random += mRandom.nextInt((int)mSpeedX);
        }
        return mSpeedX + random;
    }

    private float getSpeedY()
    {
        float random = mRandom.nextFloat();
        if(mSpeedX > 0)
        {
            random += mRandom.nextInt((int)mSpeedY);
        }

        return mSpeedY + random;
    }

    public void reset()
    {
        passedTime = 0;
    }
}
