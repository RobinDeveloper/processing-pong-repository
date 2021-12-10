package GameSandbox;

import GameEngine.Entities.PrimitiveTypes.Rectangle;
import GameEngine.PhysicsEngine.Hitside;
import processing.core.PApplet;
import processing.core.PVector;

public class Player extends Rectangle {

    private float movementSpeed;
    private char keyUp;
    private char keyDown;

    private PVector sizeContraints;

    public Player(PApplet _masterSketch, PVector _position, PVector _size, PVector _sizeConstraints, int _colour, float _movementSpeed, char _keyUp, char _keyDown) {
        super(_masterSketch, _position, _size, _colour);
        movementSpeed = _movementSpeed;
        sizeContraints = _sizeConstraints;
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
        if(position.y <= sizeContraints.x + (size.y / 2))
            position.y = sizeContraints.x + (size.y / 2);

        if(position.y >= sizeContraints.y - (size.y / 2))
            position.y = sizeContraints.y - (size.y / 2);
    }
}
