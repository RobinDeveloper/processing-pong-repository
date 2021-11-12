package Pong;

import processing.core.PApplet;
import processing.core.PVector;

public class Ball {

    private PongGame sketch;

    private PVector position;
    private PVector speed = new PVector(-10,-10);
    private int radius;

    public Ball(PongGame _sketch, PVector _position, int _radius)
    {
        sketch = _sketch;
        position = _position;
        radius = _radius;
    }

    public void updateBallPhysics(){
        updateMovement();
        handleWallCollisions();
        handlePaddleCollision();
    }

    public void drawBall(){
        sketch.fill(255);
        sketch.ellipse(position.x, position.y, radius, radius);
    }

    private void updateMovement(){
        position.add(speed);
    }

    private void handleWallCollisions(){
        if(position.x <= 0 || position.x >= sketch.width)
            speed = new PVector(speed.x * -1,speed.y);

        if(position.y >= sketch.height || position.y <= 0)
            speed = new PVector(speed.x, speed.y * -1);
    }

    private void handlePaddleCollision(){
        Player playerOne = sketch.getPlayerOne();
        Player playerTwo = sketch.getPlayerTwo();

        if(position.x <= playerOne.)
    }
}
