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

    public void RenderObjects(){
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).drawObject();
        }
    }

}
