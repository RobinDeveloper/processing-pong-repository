package Pong;

import processing.core.PApplet;
import processing.core.PVector;

public class Player{

    private PongGame sketch;

    private int movementSpeed;
    private char keyUp;
    private char keyDown;

    private PVector position;
    private PVector size;

    private Paddle paddle;

    public Player(PongGame _sketch, PVector _initializedPosition, PVector _initializedSize,int _movementSpeed, char _keyUp, char _keyDown) {
        sketch = _sketch;

        position = _initializedPosition;
        size = _initializedSize;

        movementSpeed = _movementSpeed;
        keyUp = _keyUp;
        keyDown = _keyDown;

        paddle = new Paddle(sketch, position, size);
    }

    public void updatePlayer(){
        handlePlayerMovement();
        handlePlayerConstraints();
    }

    private void handlePlayerMovement(){
        if(sketch.keyPressed) {
            if(sketch.key == keyUp)
                position.y -= movementSpeed; //fucking processing and their we start left bottom type bullshit
            if(sketch.key == keyDown)
                position.y += movementSpeed;
        }
    }

    private void handlePlayerConstraints(){
        if(position.y <= 0 + size.y)
            position.y = 0 + size.y;

        if(position.y >= sketch.height - size.y)
            position.y = sketch.height - size.y;
    }

    public void drawPlayer(){
        paddle.drawPaddle();
    }

    public Paddle getPaddle(){
        return paddle;
    }
}
