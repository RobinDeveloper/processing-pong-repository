package Pong;

import processing.core.PVector;

import static java.lang.Math.*;

enum HitSide{
    LEFT,
    RIGHT
}

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
        if(sketch.keyPressed)
        {
            if((sketch.key == 'w' || sketch.key == 's') && speed.x <= 0)
                return;

            if(sketch.key == 'i' || sketch.key == 'k' && speed.x >= 0)
                return;
        }
        sketch.fill(255);
        sketch.ellipse(position.x, position.y, radius, radius);
    }

    private void updateMovement(){
        position.add(speed);
    }

    private void handleWallCollisions(){
        if(position.x <= 0)
            handleScore(HitSide.LEFT);
        if(position.x >= sketch.width)
            handleScore(HitSide.RIGHT);

        if(position.y >= sketch.height || position.y <= 0)
            speed = new PVector(speed.x, speed.y * -1);
    }

    private void handlePaddleCollision(){
        Player playerOne = sketch.getPlayerOne();
        Player playerTwo = sketch.getPlayerTwo();

        if(paddleIntersection(playerOne.getPaddle()) || paddleIntersection(playerTwo.getPaddle()))
            speed = new PVector(speed.x * -1, speed.y);
    }

    private void handleScore(HitSide _side) {
        if (_side == HitSide.LEFT) {
            sketch.updateScore(1, 0);
            position = new PVector(sketch.width/2, sketch.height/2);
            speed = new PVector(10, sketch.random(-10f,10f));
        }

        if (_side == HitSide.RIGHT) {
            sketch.updateScore(0, 1);
            position = new PVector(sketch.width/2, sketch.height/2);
            speed = new PVector(-10, sketch.random(-10f,10f));
        }
    }

    private boolean paddleIntersection(Paddle paddle){
        PVector circleDistance = new PVector();

        circleDistance.x = abs(position.x - paddle.getPosition().x);
        circleDistance.y = abs(position.y - paddle.getPosition().y);

        if (circleDistance.x > (paddle.getSize().x / 2 + radius)) { return false; }
        if (circleDistance.y > (paddle.getSize().y / 2 + radius)) { return false; }

        if (circleDistance.x <= (paddle.getSize().x / 2)) { return true; }
        if (circleDistance.y <= (paddle.getSize().y / 2)) { return true; }

        double cornerDistance_sq;
        cornerDistance_sq = Math.pow((circleDistance.x - (paddle.getSize().x / 2)), 2) +
                Math.pow((circleDistance.y - (paddle.getSize().x / 2) ), 2);

        return (cornerDistance_sq <= (Math.pow(radius, 2)));
    }
}
