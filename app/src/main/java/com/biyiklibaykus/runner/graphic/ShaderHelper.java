package com.biyiklibaykus.runner.graphic;


import com.biyiklibaykus.runner.Util;

import static android.opengl.GLES20.*;

/**
 * Created by egemen on 31.08.2015.
 */
public class ShaderHelper
{


    private static final String TAG = "ShaderHelper";

    public static int compileVertexShader(String shaderCode)
    {
        return compileShader(GL_VERTEX_SHADER, shaderCode);
    }
    public static int compileFragmentShader(String shaderCode)
    {
        return compileShader(GL_FRAGMENT_SHADER, shaderCode);
    }
    private static int compileShader(int type, String shaderCode)
    {
        final int shaderObjectId = glCreateShader(type);

        if(shaderObjectId == 0)
        {
            Util.log("Could not create new shader");
            return 0;
        }

        glShaderSource(shaderObjectId, shaderCode);
        glCompileShader(shaderObjectId);

        int[] compileStatus = new int[1];
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);

        Util.log("Results of compiling source: \n" + shaderCode + "\n"+ glGetShaderInfoLog(shaderObjectId));

        if(compileStatus[0] == 0)
        {
            // if compilation is failed, delete shader object
            glDeleteShader(shaderObjectId);
            Util.log("Compilation of shader failed");
        }

        return shaderObjectId;
    }

    public static int linkProgram(int vertexShaderId, int fragmentShaderId)
    {
        final int programObjectId = glCreateProgram();
        if(programObjectId == 0)
        {
            Util.log("Could not create new program");
            return 0;
        }

        glAttachShader(programObjectId,vertexShaderId);
        glAttachShader(programObjectId, fragmentShaderId);

        glLinkProgram(programObjectId);

        final int linkStatus[] = new int[1];
        glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0);

        Util.log("Results of linking program:\n" + glGetProgramInfoLog(programObjectId));

        if(linkStatus[0] == 0)
        {
            // if linking failed delete the program object
            glDeleteProgram(programObjectId);
            Util.log("Linking of program failed");
            return 0;
        }

        return programObjectId;
    }

    public static boolean validateProgram(int programObjectId) {
        glValidateProgram(programObjectId);
        final int[] validateStatus = new int[1];
        glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0);
        Util.log("Results of validating program: " + validateStatus[0]
                + "\nLog:" + glGetProgramInfoLog(programObjectId));
        return validateStatus[0] != 0;
    }

    public static int buildProgram(String vertexShaderSource, String fragmentShaderSource)
    {

        // Compile the shaders.
        int vertexShader = compileVertexShader(vertexShaderSource);
        int fragmentShader = compileFragmentShader(fragmentShaderSource);

        // link shader as a single program
        int program = ShaderHelper.linkProgram(vertexShader,fragmentShader);

        if(Util.DEBUG_ON)
        {
            // check is program valid
            ShaderHelper.validateProgram(program);
        }

        return program;
    }


}
