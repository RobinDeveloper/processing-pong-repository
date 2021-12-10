package GameSandbox;

import GameEngine.Entities.GameObject;
import GameEngine.SceneManagment.Scene;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class FourPlayerPong implements Scene {

    private PApplet masterSketch;

    private Ball ball;
    private Player[] teamOne = new Player[2];
    private Player[] teamTwo = new Player[2];

    private int teamOneScore = 0;
    private int teamTwoScore = 0;

    @Override
    public String getSceneName() {
        return "FourPlayerPong";
    }

    @Override
    public void setupScene(PApplet _sketch) {
        masterSketch = _sketch;

        teamOne[0] = new Player(_sketch, new PVector(50,_sketch.height/2), new PVector(25,100), new PVector(0, masterSketch.height / 2), _sketch.color(255), 25, 'w', 's');
        teamOne[1] = new Player(_sketch, new PVector(50,_sketch.height/2), new PVector(25,100), new PVector(masterSketch.height / 2, masterSketch.height), _sketch.color(255), 25, 'r', 'f');
        teamTwo[0] = new Player(_sketch, new PVector(_sketch.width - 50, _sketch.height/2), new PVector(25,100), new PVector(0, masterSketch.height / 2), _sketch.color(255), 25, 'i', 'k');
        teamTwo[0] = new Player(_sketch, new PVector(_sketch.width - 50, _sketch.height/2), new PVector(25,100), new PVector(masterSketch.height / 2, masterSketch.height), _sketch.color(255), 25, 'l', 'p');

        ball = new Ball(_sketch, new PVector(_sketch.width/2, _sketch.height/2), 25, _sketch.color(255), new PVector(_sketch.random(-10,10), _sketch.random(-10,10)));

        updateScore(0,0);
    }

    @Override
    public void updateScene() {

        masterSketch.textSize(64);
        masterSketch.text("" + teamOneScore, (masterSketch.width/2 - 50), (75));
        masterSketch.text("" + teamTwoScore, (masterSketch.width/2 + 50), (75));

        for (int i = 0; i < 10; i++) {
            masterSketch.rect(masterSketch.width / 2, 25 + (i * 75), 20, 50);
        }
    }

    @Override
    public void updateScore(int _addPlayerOne, int _addPlayerTwo) {
        teamOneScore += _addPlayerOne;
        teamTwoScore += _addPlayerTwo;
    }

    @Override
    public ArrayList<GameObject> getSceneObjects() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        gameObjects.add(ball);
        gameObjects.add(teamOne[0]);
        gameObjects.add(teamOne[1]);
        gameObjects.add(teamTwo[0]);
        gameObjects.add(teamTwo[1]);

        return gameObjects;
    }
}
