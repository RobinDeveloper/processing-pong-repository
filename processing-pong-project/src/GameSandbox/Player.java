package GameSandbox;

import GameEngine.Entities.PrimitiveTypes.Rectangle;
import processing.core.PApplet;
import processing.core.PVector;

public class Player extends Rectangle {

    private float movementSpeed;
    private char keyUp;
    private char keyDown;

    public Player(PApplet _masterSketch, PVector _position, PVector _size, int _colour, float _movementSpeed, char _keyUp, char _keyDown) {
        super(_masterSketch, _position, _size, _colour);
        movementSpeed = _movementSpeed;
        keyUp = _keyUp;
        keyDown = _keyDown;
    }

    @Override
    public void updateObject() {
        handlePlayerMovement();
        handlePlayerConstraints();
    }

    private void handlePlayerMovement(){
        if(masterSketch.keyPressed) {
            if(masterSketch.key == keyUp)
                position.y -= movementSpeed; //fucking processing and their we start left bottom type bullshit
            if(masterSketch.key == keyDown)
                position.y += movementSpeed;
        }
    }

    private void handlePlayerConstraints(){
        if(position.y <= 0 + size.y)
            position.y = 0 + size.y;

        if(position.y >= masterSketch.height - size.y)
            position.y = masterSketch.height - size.y;
    }

}
