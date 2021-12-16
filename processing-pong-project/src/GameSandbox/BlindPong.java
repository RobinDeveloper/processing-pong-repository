package GameSandbox;

import GameEngine.Engine;
import GameEngine.Entities.GameObject;
import GameEngine.SceneManagment.Scene;
import GameSandbox.Noise.FlowFieldGeneration;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

//This version of pong has it that when u move the ball will go invisible
public class BlindPong implements Scene {

    private PApplet masterSketch;

    private Ball ball;
    private Player player;
    private Player opponent;

    private int playerOneScore = 0;
    private int playerTwoScore = 0;


    @Override
    public String getSceneName() {
        return "BlindPong";
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
        ball = new Ball(_sketch, new PVector(_sketch.width/2, _sketch.height/2), 25, _sketch.color(255), new PVector(_sketch.random(-10,10), _sketch.random(-2,2)));

        ball.setBlindPong(true);

        updateScore(0,0);
    }

    @Override
    public void updateScene() {

        masterSketch.textSize(64);
        masterSketch.text("" + playerOneScore, (masterSketch.width/2 - 50), (75));
        masterSketch.text("" + playerTwoScore, (masterSketch.width/2 + 50), (75));
        masterSketch.text(getSceneName(), 50, masterSketch.height - 50);

        handleBallVisability();

        for (int i = 0; i < 10; i++) {
            masterSketch.rect(masterSketch.width / 2, 25 + (i * 75), 20, 50);
        }
    }

    @Override
    public void updateScore(int _addPlayerOne, int _addPlayerTwo) {
        playerOneScore = _addPlayerOne;
        playerTwoScore = _addPlayerTwo;
    }

    @Override
    public ArrayList<GameObject> getSceneObjects() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        gameObjects.add(ball);
        gameObjects.add(player);
        gameObjects.add(opponent);

        return gameObjects;
    }

    //Only difference with standardPong it checks if the speed is going left or right and then which player is moving to determine if it needs to be invisible.
    private void handleBallVisability()
    {
        if(ball.getSpeed().x <= 0)
            ball.setInvis(opponent.getMoving());

        if(ball.getSpeed().x >= 0)
            ball.setInvis(player.getMoving());
    }
}
