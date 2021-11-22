package GameEngine.SceneManagment;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {

    private PApplet masterSketch;

    private Scene activeScene;
    private ArrayList<Scene> scenes;

    public SceneManager()
    {
        scenes = new ArrayList<Scene>();
    }

    public SceneManager(Scene _scene)
    {
        scenes = new ArrayList<Scene>();
        scenes.add(_scene);
    }

    public SceneManager(ArrayList<Scene> _scenes)
    {
        scenes = _scenes;
    }

    public void addScene(Scene _scene){
        scenes.add(_scene);
    }

    public void loadScene(String _sceneName)
    {
        for (int i = 0; i < scenes.size(); i++) {
            if(scenes.get(i).getSceneName() == _sceneName)
            {
                activeScene = scenes.get(i);
                activeScene.setupScene(masterSketch);
            }
        }
    }

    public Scene getActiveScene()
    {
        return activeScene;
    }

}
