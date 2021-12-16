package GameEngine.RenderingEngine;

import GameEngine.Entities.GameObject;
import GameEngine.SceneManagment.Scene;

import java.util.ArrayList;

public class RenderingEngine {

    private ArrayList<GameObject> gameObjects;

    public RenderingEngine()
    {
        gameObjects = new ArrayList<GameObject>();
    }

    public RenderingEngine(GameObject _gameObject)
    {
        gameObjects = new ArrayList<GameObject>();
        gameObjects.add(_gameObject);
    }

    public RenderingEngine(ArrayList<GameObject> _gameObjects)
    {
        gameObjects = _gameObjects;
    }

    public void addGameObject(GameObject _gameObject)
    {
        gameObjects.add(_gameObject);
    }

    //Simply renders and updates each object within the active scene
    public void renderObjects(){
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).updateObject();
            gameObjects.get(i).drawObject();
        }
    }

}
