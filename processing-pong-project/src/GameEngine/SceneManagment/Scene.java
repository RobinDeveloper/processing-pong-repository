package GameEngine.SceneManagment;

import GameEngine.Entities.GameObject;
import processing.core.PApplet;

import java.util.ArrayList;

//base interface for scenes
public interface Scene {

    public String getSceneName();
    public void setupScene(PApplet _sketch);
    public void updateScene();
    public void updateScore(int _addPlayerOne, int _addPlayerTwo);

    public ArrayList<GameObject> getSceneObjects();
}
