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
        if(sketch.keyPressed) {
            if(sketch.key == keyUp)
                position.y -= movementSpeed; //fucking processing and their we start left bottom type bullshit
            if(sketch.key == keyDown)
                position.y += movementSpeed;
        }
    }

    public void drawPlayer(){
        paddle.drawPaddle();
    }
}
