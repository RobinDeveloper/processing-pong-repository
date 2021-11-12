package Pong;

import processing.core.PApplet;
import processing.core.PVector;

public class PongGame extends PApplet {

    private Ball ball;
    private Player playerOne;
    private Player playerTwo;

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

        ball.updateBallPhysics();
        ball.drawBall();

        playerOne.updatePlayer();
        playerTwo.updatePlayer();

        playerOne.drawPlayer();
        playerTwo.drawPlayer();
    }

    public Player getPlayerOne(){
        return playerOne;
    }

    public Player getPlayerTwo()    {
        return playerTwo;
    }
}
