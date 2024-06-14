package jade;


import org.lwjgl.BufferUtils;
import renderer.Shader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class LevelEditorScene extends Scene {

    private boolean changingScene = false;
    private float timeChangeScene = 2.0f;
    private Shader defaultShader;



    private int vertexID, fragmentID, shaderID,shaderProgram;

    private float[] vertexArray = {
            // position      // color
            0.5f,-0.5f,0.0f, 1.0f,0.0f,0.0f,1.0f, // Bottom right 0
            -0.5f,0.5f,0.0f, 0.0f,1.0f,0.0f,1.0f, // Top Left    1
            0.5f,0.5f,0.0f,  0.0f,0.0f,1.0f,1.0f, // Top Right   2
            -0.5f,-0.5f,0.0f, 1.0f,1.0f,0.0f,1.0f,// Bottom left 3
    };

    // IMPORTANT: COUNTER-clockwise order
    public int[] elementArray = {

        2, 1, 0,
        0, 1, 3,
    };

    private int vaoID, vboID, eboID;

    public LevelEditorScene() {
        Shader test = new Shader("assets/shaders/default.glsl");
    }

    @Override
    public void init() {



        // ============================================================
        // Generate VAO, VBO, and EBO buffer objects, and send to GPU
        // ============================================================

        defaultShader = new Shader("assets/shaders/default.glsl");
        defaultShader.compile();


    }

    @Override
    public void update(float dt) {
       defaultShader.use();
       glBindVertexArray(vaoID);
       glBindVertexArray(vboID);
       glBindVertexArray(0);
       defaultShader.detach();
    }


}
