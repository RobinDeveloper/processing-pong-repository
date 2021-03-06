package GameSandbox;

import GameEngine.Engine;
import GameEngine.Entities.GameObject;
import GameEngine.SceneManagment.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

//Every goal adds an extra ball to the game (gets out of hand very fast)
public class ScoreBallPong implements Scene {
    private PApplet masterSketch;

    private ArrayList<Ball> balls;
    private Player player;
    private Player opponent;

    private int playerOneScore = 0;
    private int playerTwoScore = 0;


    @Override
    public String getSceneName() {
        return "ScoreBallPong";
    }

    @Override
    public void setupScene(PApplet _sketch) {
        masterSketch = _sketch;

        Engine engine;

        engine = (Engine) masterSketch;
        playerOneScore = engine.getLeftScore();
        playerTwoScore = engine.getRightScore();

        player = new Player(_sketch, new PVector(50,_sketch.height/2), new PVector(25,100), new PVector(0, masterSketch.height), _sketch.color(255), 25, 'w', 's');
        opponent = new Player(_sketch, new PVector(_sketch.width - 50, _sketch.height/2), new PVector(25,100),new PVector(0, masterSketch.height),_sketch.color(255), 25, 'i', 'k');
        balls = new ArrayList<Ball>();

        for (int i = 0; i < playerOneScore + playerTwoScore; i++) {
            balls.add(new Ball(_sketch, new PVector(_sketch.width/2, _sketch.height/2), 25, _sketch.color(255), new PVector(_sketch.random(-10,10), _sketch.random(-2,2))));
        }

        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).setBlindPong(false);
        }


        updateScore(0,0);
    }

    @Override
    public void updateScene() {

        masterSketch.textSize(64);
        masterSketch.text("" + playerOneScore, (masterSketch.width/2 - 50), (75));
        masterSketch.text("" + playerTwoScore, (masterSketch.width/2 + 50), (75));
        masterSketch.text(getSceneName(), 50, masterSketch.height - 50);


        for (int i = 0; i < 10; i++) {
            masterSketch.rect(masterSketch.width / 2, 25 + (i * 75), 20, 50);
        }
    }

    //ADD HELLA BALLS BABY EACH GOAL.
    @Override
    public void updateScore(int _addPlayerOne, int _addPlayerTwo) {
        playerOneScore = _addPlayerOne;
        playerTwoScore = _addPlayerTwo;
        balls.add(new Ball(masterSketch, new PVector(masterSketch.width/2, masterSketch.height/2), 25, masterSketch.color(255), new PVector(masterSketch.random(-10,10), masterSketch.random(-2,2))));
        ((Engine)masterSketch).updateEngine(); //this is very yikes
    }

    @Override
    public ArrayList<GameObject> getSceneObjects() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        for (int i = 0; i < balls.size(); i++) {
            gameObjects.add(balls.get(i));
        }
        gameObjects.add(player);
        gameObjects.add(opponent);

        return gameObjects;
    }
}
