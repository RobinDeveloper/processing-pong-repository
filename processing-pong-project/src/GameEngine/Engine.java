package GameEngine;

import GameEngine.SceneManagment.Scene;
import GameEngine.SceneManagment.SceneManager;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Engine extends PApplet {

    private PVector gameSize = new PVector(1080, 720);

    private SceneManager sceneManager;

    @Override
    public void settings() {
        size((int)gameSize.x, (int)gameSize.y);
    }

    @Override
    public void setup() {
        initialiseSceneManager();
    }

    @Override
    public void draw() {

    }

    private void initialiseSceneManager(){
        ArrayList<Scene> gameScenes = new ArrayList<>();

        sceneManager = new SceneManager(gameScenes);
        sceneManager.loadScene("StandardPong");
    }

}
