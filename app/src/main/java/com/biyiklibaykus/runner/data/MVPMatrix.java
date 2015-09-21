package com.biyiklibaykus.runner.data;

import android.graphics.Camera;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

import com.biyiklibaykus.runner.Util;
import com.biyiklibaykus.runner.components.Transform;
import com.biyiklibaykus.runner.components.Vector2;
import com.biyiklibaykus.runner.objects.GameObject;

/**
 * Created by egemen on 09.09.2015.
 */
public class MVPMatrix
{
    private final float[] mMVPMatrix = new float[16];

    private final float[] mProjectionMatrix = new float[16];

    private float[] mModelMatrix = new float[16];

    private float[] mTrRoMatrix = new float[16];
    private float[] mTranslationMatrix = new float[16];
    private float[] mRotationMatrix = new float[16];
    private float[] mScaleMatrix = new float[16];

    private GameObject mGameObject;
    private Vector2 mCameraPos;


    public void initialize(float width, float height)
    {

//        Matrix.frustumM(m,offset,left, right, bottom,top,near,far);
        //-width
        Matrix.orthoM(mProjectionMatrix, 0, 0, width, 0, height, -3, 100);
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

        Matrix.setIdentityM(mRotationMatrix, 0);
        Matrix.setIdentityM(mScaleMatrix, 0);
        Matrix.setIdentityM(mTranslationMatrix, 0);

        // Translation
        Matrix.translateM(mTranslationMatrix, 0, t.pos.x - mCameraPos.x, t.pos.y - mCameraPos.y, 0);

        // Rotation
        Matrix.setRotateM(mRotationMatrix, 0, t.rot, 0, 0, -1.0f);

        //Scale
        Matrix.scaleM(mScaleMatrix, 0, t.scale.x, t.scale.y, 1);


        //myModelMatrix = myTranslationMatrix * myRotationMatrix * myScaleMatrix;
        Matrix.multiplyMM(mTrRoMatrix, 0, mTranslationMatrix, 0, mRotationMatrix, 0);
        Matrix.multiplyMM(mModelMatrix, 0, mTrRoMatrix, 0, mScaleMatrix, 0);


        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mModelMatrix, 0);

    }

    public float[] getMVPMatrix() {
        return mMVPMatrix;
    }


}
