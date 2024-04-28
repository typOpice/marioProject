package jade;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import util.Time;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private int width,height;
    private String title;
    private long glfwWindow;
    public float r, g ,b , a;

    private static Window window = null;

    private static Scene currentScene;

    private Window(){
        this.width = 400;
        this.height = 400;
        this.title = "mario";

        this.r = 1;
        this.g = 1;
        this.b = 1;
        this.a = 1;
    }

    public static Window get(){
        if(Window.window == null){
            Window.window = new Window();
        }

        return Window.window;
    }

    public static void changeScene(int newScene){
        switch(newScene){
            case 0:
                currentScene = new LevelEditorScene();
                currentScene.init();
                break;
            case 1:
                currentScene = new LevelScene();
                currentScene.init();
                break;
            default:
                assert false : "Unknown scene " + newScene;
                break;
        }
    }

    public void  run(){

        System.out.println("Hello LWGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the memory
        glfwFreeCallbacks(this.glfwWindow);
        glfwDestroyWindow(this.glfwWindow);

        // Terminate GFLW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();


    }

    public void init(){


        //setup an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // init GLFW
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GFLW");
        }

        //configure
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);




        //create the window
        glfwWindow = glfwCreateWindow(this.width, this.height,this.title, NULL, NULL);

        if(glfwWindow == NULL){
            throw new IllegalStateException("Failed to create new GLFW window");
        }

        glfwSetCursorPosCallback(this.glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(this.glfwWindow,MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(this.glfwWindow, MouseListener::mouseScrollCallback);
//        glfwSetJoystickCallback()

        glfwSetKeyCallback(this.glfwWindow, KeyListener::keyCallback);


        //Making the Opengl Context Current
        glfwMakeContextCurrent(glfwWindow);
        //enable V-Sync
        glfwSwapInterval(1);
        //Enable window visible
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();


        Window.changeScene(0);

    }

    public void loop(){
        float beginTime = Time.getTime();
        float endTime = Time.getTime();
        float dt = -1.0f;


        while(!glfwWindowShouldClose(glfwWindow)){
            //poll event
            glfwPollEvents();

            glClearColor(this.r,this.g,this.g,this.a);
            glClear(GL_COLOR_BUFFER_BIT);

            //Lag of 2 frames will be added
            if(dt > 0.0f){
                currentScene.update(dt);
            }


            glfwSwapBuffers(glfwWindow);

            //Setup for Delta Time
            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;

        }
    }
}
