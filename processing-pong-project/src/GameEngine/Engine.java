package GameEngine;

import GameEngine.PhysicsEngine.PhysicsEngine;
import GameEngine.RenderingEngine.RenderingEngine;
import GameEngine.SceneManagment.Scene;
import GameEngine.SceneManagment.SceneManager;
import GameSandbox.BlindPong;
import GameSandbox.FourPlayerPong;
import GameSandbox.ScoreBallPong;
import GameSandbox.StandardPong;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

import java.util.ArrayList;

public class Engine extends PApplet {

    private PVector gameSize = new PVector(1080, 720);

    private SceneManager sceneManager;
    private RenderingEngine renderingEngine;
    private PhysicsEngine physicsEngine;

    private PFont font;

    //HATE THIS BUT NO TIME SORRY
    private int leftScore = 0;
    private int rightScore = 0;

    @Override
    public void settings() {
        size((int)gameSize.x, (int)gameSize.y);
    }

    @Override
    public void setup() {
        font = createFont("../Fonts/bit5x3.ttf", 64);
        textFont(font);

        initialiseSceneManager();
        initialiseRenderEngine();
        initialisePhysicEngine();
    }

    @Override
    public void draw() {
        background(0);
        renderingEngine.renderObjects();
        physicsEngine.updatePhysics();
        sceneManager.getActiveScene().updateScene();
    }

    private void initialiseSceneManager(){
        ArrayList<Scene> gameScenes = new ArrayList<>();
        gameScenes.add(new StandardPong());
        gameScenes.add(new ScoreBallPong());
        gameScenes.add(new FourPlayerPong());
        gameScenes.add(new BlindPong());

        sceneManager = new SceneManager(this, gameScenes);
        sceneManager.loadScene("StandardPong");
    }

    private void initialiseRenderEngine(){
        renderingEngine = new RenderingEngine(sceneManager.getActiveScene().getSceneObjects());
    }

    private void initialisePhysicEngine(){
        physicsEngine = new PhysicsEngine(sceneManager.getActiveScene().getSceneObjects());
    }

    public void updateEngine(){
        physicsEngine = new PhysicsEngine(sceneManager.getActiveScene().getSceneObjects());
        renderingEngine = new RenderingEngine(sceneManager.getActiveScene().getSceneObjects());
    }

    public Scene getActiveScene(){
        return sceneManager.getActiveScene();
    }

    public void loadRandomScene()
    {
        int rand = (int)random(0, sceneManager.getScenes().size());

        sceneManager.loadScene(sceneManager.getScenes().get(rand).getSceneName());
        updateEngine();
    }

    public int getLeftScore(){
        return leftScore;
    }

    public void setLeftScore(int _leftScore){
        leftScore = _leftScore;
    }

    public int getRightScore()
    {
        return rightScore;
    }

    public void setRightScore(int _rightScore){
        rightScore = _rightScore;
    }
}
