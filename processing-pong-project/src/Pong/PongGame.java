package Pong;

import processing.core.PApplet;
import processing.core.PVector;

public class PongGame extends PApplet {

    private Ball ball;
    private Player playerOne;
    private Player playerTwo;

    private int playerOneScore;
    private int playerTwoScore;

    @Override
    public void settings() {
        size(1080, 720);
    }

    @Override
    public void setup() {
        background(0);

        ball = new Ball(this, new PVector(width/2, height/2), 25);
        playerOne = new Player(this, new PVector(50, height/2), new PVector(25,100), 25, 'w', 's');
        playerTwo = new Player(this, new PVector(width - 50, height/2), new PVector(25,100), 25, 'i', 'k');
    }

    @Override
    public void draw() {
        background(0);

        drawScore();

        ball.updateBallPhysics();
        ball.drawBall();

        playerOne.updatePlayer();
        playerTwo.updatePlayer();

        playerOne.drawPlayer();
        playerTwo.drawPlayer();
    }

    private void drawScore(){
        fill(255);
        textSize(64);
        text("" + playerOneScore, width/2 - 100, 100);
        text( "" + playerTwoScore, width/2 + 100, 100);
    }

    public Player getPlayerOne(){
        return playerOne;
    }

    public Player getPlayerTwo()    {
        return playerTwo;
    }

    public void updateScore(int _scorePlayerOne, int _scorePlayerTwo){
        playerOneScore += _scorePlayerOne;
        playerTwoScore += _scorePlayerTwo;
    }
}
