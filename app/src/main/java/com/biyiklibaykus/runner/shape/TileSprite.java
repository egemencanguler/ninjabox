package com.biyiklibaykus.runner.shape;

import android.opengl.GLES20;

import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.data.VertexArray;
import com.biyiklibaykus.runner.graphic.TextureHelper;
import com.biyiklibaykus.runner.programs.TextureShaderProgram;

/**
 * Created by egemen on 23.09.2015.
 */
public class TileSprite
{
    private float mWidth;
    private float mHeight;

    static final int POSITION_COMPONENT_COUNT = 2;
    static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT
            + TEXTURE_COORDINATES_COMPONENT_COUNT) * 4;

    private final int VERTEX_COUNT = 4;

    private TextureShaderProgram mProgram;
    private VertexArray mVertexArray;
    private int mTextureId;

    private int mRowNumber;
    private int mColumnNumber;
    private float mSize;

    public TileSprite(float size, int rowNumber, int columnNumber , int drawableId)
    {
        mSize = size;

        mRowNumber = rowNumber;
        mColumnNumber = columnNumber;

        setVertexArray(0,0);

        mProgram = new TextureShaderProgram();
        mTextureId = TextureHelper.loadTexture(drawableId);
    }

    private  void setVertexArray(int rowPos, int columnPos)
    {
        float sh = mSize/2;

        float tw = 1f / mColumnNumber; // tile width. between 0 - 1
        float th = 1f / mRowNumber; // tile height. between 0 - 1

        float tileStartX = tw * columnPos;
        float tileEndX = tileStartX + tw;

        float tileStartY = th * rowPos;
        float tileEndY = tileStartY + th;

       // Util.log("Tile vetex: " + tileStartX + " " + tileStartY + " " + tileEndX + " " + tileEndY);
        float vertexCoords[] =
                {
                        -sh, sh, tileStartX, tileStartY, // top left
                        sh, sh,  tileEndX,   tileStartY, // top right
                        -sh,-sh, tileStartX, tileEndY, // bottom left
                        sh,-sh,  tileEndX,   tileEndY // bottom right
                };

        mVertexArray = new VertexArray(vertexCoords);

    }

    public void setTile(int rowPos, int columnPos)
    {

        setVertexArray(rowPos,columnPos);
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
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, VERTEX_COUNT);


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
