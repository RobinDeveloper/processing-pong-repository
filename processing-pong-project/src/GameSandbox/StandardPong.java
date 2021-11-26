package GameSandbox;

import GameEngine.Entities.GameObject;
import GameEngine.SceneManagment.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class StandardPong implements Scene {

    private PApplet masterSketch;

    private Ball ball;
    private Player player;
    private Player opponent;

    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    @Override
    public String getSceneName() {
        return "StandardPong";
    }

    @Override
    public void setupScene(PApplet _sketch) {
        masterSketch = _sketch;

        player = new Player(_sketch, new PVector(50,_sketch.height/2), new PVector(25,100), _sketch.color(255), 25, 'w', 's');
        opponent = new Player(_sketch, new PVector(_sketch.width - 50, _sketch.height/2), new PVector(25,100),_sketch.color(255), 25, 'i', 'k');
        ball = new Ball(_sketch, new PVector(_sketch.width/2, _sketch.height/2), 25, _sketch.color(255), new PVector(_sketch.random(-10,10), _sketch.random(-10,10)));

        updateScore(0,0);
    }

    @Override
    public void updateScene() {
        masterSketch.textSize(64);
        masterSketch.text("" + playerOneScore, (masterSketch.width/2 - 50), (75));
        masterSketch.text("" + playerTwoScore, (masterSketch.width/2 + 50), (75));

        for (int i = 0; i < 10; i++) {
            masterSketch.rect(masterSketch.width / 2, 25 + (i * 75), 20, 50);
        }
    }

    @Override
    public void updateScore(int _addPlayerOne, int _addPlayerTwo) {
        playerOneScore += _addPlayerOne;
        playerTwoScore += _addPlayerTwo;
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
