package jade;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene {

    private boolean changingScene = false;
    private float timeChangeScene = 2.0f;

    public LevelEditorScene() {
        System.out.println("Inside LevelEditorScene");
    }

    @Override
    public void update(float dt) {


        if(!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)){
            changingScene = true;
        }

        if(changingScene && timeChangeScene > 0.0f){
            timeChangeScene -= dt;
            Window.get().r -= dt * 5.0f;
            Window.get().g -= dt * 5.0f;
            Window.get().b -= dt * 5.0f;

        } else if (changingScene) {
            Window.changeScene(1);
        }
    }


}
