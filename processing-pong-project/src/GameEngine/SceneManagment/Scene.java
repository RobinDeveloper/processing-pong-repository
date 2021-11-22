package GameEngine.SceneManagment;

import GameEngine.Entities.GameObject;
import processing.core.PApplet;

import java.util.ArrayList;

public interface Scene {

    public String getSceneName();
    public void setupScene(PApplet _sketch);
    public void updateScene();
    public ArrayList<GameObject> getSceneObjects();
}
