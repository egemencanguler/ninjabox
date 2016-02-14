package com.biyiklibaykus.runner.graphic;

/**
 * Created by egemen on 09.09.2015.
 */
public class ShaderSource
{

    public static final String V_POSITION = "vPosition";
    public static final String U_MVP_MATRIX = "uMVPMatrix";

    public static final String simpleVertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    // the matrix must be included as a modifier of gl_Position
                    // Note that the uMVPMatrix factor *must be first* in order
                    // for the matrix multiplication product to be correct.
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    public static final String U_COLOR = "uColor";

    public static final String simpleFragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 uColor;" +
                    "void main() {" +
                    "  gl_FragColor = uColor - gl_FragColor;" +
                    "}";


    public static final String U_TIME = "u_Time";

    public static final String A_POSITION = "a_Position";
    public static final String A_DIRECTION_VECTOR = "a_DirectionVector";
    public static final String A_PARTICLE_START_TIME = "a_ParticleStartTime";


    public static final String particleVertexShader =
            "uniform mat4 uMVPMatrix;"+
            "uniform float u_Time;"+

            "attribute vec3 a_Position;"+
            "attribute vec3 a_DirectionVector;"+
            "attribute float a_ParticleStartTime;"+
                    "varying float v_ElapsedTime;"+

             "void main()"+
             "{" +
                     "v_ElapsedTime = u_Time - a_ParticleStartTime;"+
                     "vec3 currentPosition = a_Position + (a_DirectionVector * v_ElapsedTime);"+
//                     "float gravityFactor = v_ElapsedTime * v_ElapsedTime * 0.98;"+
//                     "currentPosition.y -= gravityFactor;"+
                     "gl_Position = uMVPMatrix * vec4(currentPosition, 1.0);"+
                     "gl_PointSize = 1.0 / v_ElapsedTime;"+
                    "if(v_ElapsedTime < 0.2) gl_PointSize = 5.0;"+
                    "}";

    public static final String particleFragmentShader =
            "precision mediump float;" +
                    "varying float v_ElapsedTime;" +
                    "uniform vec4 uColor;" +
                    "void main() {" +
//                    "if(v_ElapsedTime > 0.2) discard;"+
//                    "uColor.a = 1 - vElapsedTime;"+
                    "vec4 color = uColor;"+
                    "color.w = 0.9;"+
                    "gl_FragColor = color;" +
                    "}";


    public static final String A_TEXTURE_COOR = "a_TextureCoordinates";

    public static final String textureVertexShader =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 a_Position;" +
            "attribute vec2 a_TextureCoordinates;" +
            "varying vec2 v_TextureCoordinates;" +

            "void main()"+
            "{"+
                "v_TextureCoordinates = a_TextureCoordinates;" +
                "gl_Position = uMVPMatrix * a_Position;" +
            "}";


    public static final String U_TEXTURE_UNIT = "u_TextureUnit";

    public static final String textureFragmentShader=
            "precision mediump float;"+
            "uniform sampler2D u_TextureUnit;"+
            "varying vec2 v_TextureCoordinates;"+

            "void main()"+
            "{"+
                "vec4 texel = texture2D(u_TextureUnit, v_TextureCoordinates);"+
                "if(texel.a < 0.2) discard;" +
                "gl_FragColor = texel;"+
            "}";




}
