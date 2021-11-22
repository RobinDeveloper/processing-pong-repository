package GameEngine.SceneManagment;

import processing.core.PApplet;

public interface Scene {

    public String getSceneName();
    public void setupScene(PApplet _sketch);
    public void updateScene();
    public void drawScene();
}
