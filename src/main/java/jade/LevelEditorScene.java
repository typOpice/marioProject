package jade;


import static org.lwjgl.opengl.GL20.*;

public class LevelEditorScene extends Scene {

    private boolean changingScene = false;
    private float timeChangeScene = 2.0f;

    private String vertexShaderSrc = "#version 330 core\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "layout (location = 1) in vec3 aColor;\n" +
            "\n" +
            "out vec4 fColor;\n" +
            "\n" +
            "void main(){\n" +
            "        fColor = aColor;\n" +
            "        gl_Position = vec4(aPos,1.0);\n" +
            "\n" +
            "}";

    private String fragmentShaderSrc = "#version 330 core\n" +
            "\n" +
            "        in vec4 fColor;\n" +
            "\n" +
            "        out vec4 color;\n" +
            "\n" +
            "        void main(){\n" +
            "        color = fColor;" + "}";

    private int vertexID, fragmentID, shaderID,shaderProgram;




    public LevelEditorScene() {

    }

    @Override
    public void init() {
        // ====================================
        // Compile and Link shader
        // ====================================

        // First load and compile vertex shader

        vertexID = glCreateShader(GL_VERTEX_SHADER);
        // pass shader source to the GPU
        glShaderSource(vertexID, vertexShaderSrc);
        glCompileShader(vertexID);

        //check for error, in compilation
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'default.glsl' v \n\t Vertex shader compilation error: " + glGetShaderInfoLog(vertexID, len));
            assert false : "";

        }


        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        // pass shader source to the GPU
        glShaderSource(fragmentID, fragmentShaderSrc);
        glCompileShader(fragmentID);

        //check for error, in comilation
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'default.glsl' f\n\t Fragment shader compilation error: " + glGetShaderInfoLog(fragmentID, len));
            assert false : "";

        }

        //Link shaders and check for errors
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexID);
        glAttachShader(shaderProgram, fragmentID);
        glLinkProgram(shaderProgram);

        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);

        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'default.glsl' p\n\t Linking of shader compilation error: " + glGetProgramInfoLog(fragmentID, len));
            assert false : "";
        }







    }

    @Override
    public void update(float dt) {


    }


}
