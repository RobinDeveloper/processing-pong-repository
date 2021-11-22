package GameSandbox;

import GameEngine.Entities.GameObject;
import GameEngine.SceneManagment.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class StandardPong implements Scene {

    private Ball ball;
    private Player player;
    private Player opponent;

    @Override
    public String getSceneName() {
        return "StandardPong";
    }

    @Override
    public void setupScene(PApplet _sketch) {
        player = new Player(_sketch, new PVector(50,_sketch.height/2), new PVector(25,100), _sketch.color(255), 25, 'w', 's');
        opponent = new Player(_sketch, new PVector(_sketch.width - 50, _sketch.height/2), new PVector(25,100),_sketch.color(255), 25, 'i', 'k');
        ball = new Ball(_sketch, new PVector(_sketch.width/2, _sketch.height/2), 25, _sketch.color(255), new PVector(_sketch.random(-25,25), _sketch.random(-25,25)));
    }

    @Override
    public void updateScene() {

    }

    @Override
    public ArrayList<GameObject> getSceneObjects() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        gameObjects.add(ball);
        gameObjects.add(player);
        gameObjects.add(opponent);

        return gameObjects;
    }
}
