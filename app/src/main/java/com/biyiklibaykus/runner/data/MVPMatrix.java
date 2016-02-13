package com.biyiklibaykus.runner.data;

import android.graphics.Camera;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.components.Transform;
import com.biyiklibaykus.runner.components.Vector2;
import com.biyiklibaykus.runner.engine.GameLoop;
import com.biyiklibaykus.runner.objects.GameObject;

/**
 * Created by egemen on 09.09.2015.
 */
public class MVPMatrix
{
    private final String TAG = "MVPMatrix";
    private final float[] mMVPMatrix = new float[16];

    private final float[] mProjectionMatrix = new float[16];

    private float[] mModelMatrix = new float[16];

    private float[] mTrRoMatrix = new float[16];
    private float[] mTranslationMatrix = new float[16];
    private float[] mRotationMatrix = new float[16];
    private float[] mScaleMatrix = new float[16];

    private GameObject mGameObject;
    private Vector2 mCameraPos;
    private float mCamWidth;
    private float mCamHeight;
    private float mScreenWidth;
    private float mScreenHeight;


    public void initialize(float width, float height)
    {
        //TODO where this function is called and why.
        // remove this and attain the resolution(width and height) by hand which must be fixed in this case
        // or better make a more meaningful function
        // write a camera class and move the related functions and variables inside it

//        Matrix.frustumM(m,offset,left, right, bottom,top,near,far);
        //-width
        mScreenWidth = width;
        mScreenHeight = height;
        mCamWidth = 800;
        mCamHeight = 600;
        Matrix.orthoM(mProjectionMatrix, 0, 0, mCamWidth, 0, mCamHeight, 3, 100);
        mCameraPos = new Vector2(0,0);

    }

    public void setCurrentGameObject(GameObject gameObject)
    {
        mGameObject = gameObject;
        updateMVP();
    }



    private void updateMVP()
    {
        Transform t = mGameObject.mTransform;

        Matrix.setIdentityM(mTranslationMatrix, 0);
        Matrix.setIdentityM(mScaleMatrix, 0);
        Matrix.setIdentityM(mTrRoMatrix, 0);
        Matrix.setIdentityM(mMVPMatrix, 0);
        Matrix.setIdentityM(mRotationMatrix, 0);





        // Translation
        float ratio = GameLoop.ratio;

        float x = mGameObject.mTransform.pos.x * (ratio) + mGameObject.mTransform.prevPos.x * (1-ratio);
        float y = mGameObject.mTransform.pos.y * (ratio) + mGameObject.mTransform.prevPos.y * (1-ratio);
        Util.log("x" + mGameObject.mTransform.pos.x + ", " + mGameObject.mTransform.prevPos.x);

//        Util.log("Layer: " + t.layer);

        Matrix.translateM(mTranslationMatrix, 0, x - mCameraPos.x, y - mCameraPos.y, -t.layer);

        // Rotation

        // angle (x,y,z) axis
        Matrix.setRotateM(mRotationMatrix,0, t.rot, 0 , 0, -1.0f);

        //Scale
        Matrix.scaleM(mScaleMatrix, 0, t.scale.x, t.scale.y, 1);


        //myModelMatrix = myTranslationMatrix * myRotationMatrix * myScaleMatrix;
        Matrix.multiplyMM(mTrRoMatrix, 0, mTranslationMatrix, 0, mRotationMatrix, 0);
        Matrix.multiplyMM(mModelMatrix, 0, mTrRoMatrix, 0, mScaleMatrix, 0);


        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mModelMatrix, 0);

    }


    public float screenToCameraX(float x)
    {
        x = x*mCamWidth/mScreenWidth;
        return x;

    }
    public float screenToCameraY(float y)
    {
        y = mScreenHeight - y;
        y = y*mCamHeight/mScreenHeight;
        return y;
    }

//    public float cameraToWorldX(float x)
//    {
//        return 300;
//
//    }
//    public float cameraToWorldY(float y)
//    {
//        return 400;
//    }


    public float getCamHeight() {
        return mCamHeight;
    }

    public float getCamWidth() {
        return mCamWidth;
    }

    public float[] getMVPMatrix() {
        return mMVPMatrix;
    }


}
