package GameSandbox;

import GameEngine.Entities.PrimitiveTypes.Ellipse;
import processing.core.PApplet;
import processing.core.PVector;

public class Ball extends Ellipse {

    private PVector speed;

    enum HitSide{
        LEFT,
        RIGHT
    }

    public Ball(PApplet _masterSketch, PVector _position, float _size, int _colour, PVector _speed) {
        super(_masterSketch, _position, _size, _colour);
        speed = _speed;
    }

    @Override
    public void updateObject() {
        handleMovement();

        if(position.x <= 0)
            handleScore(HitSide.LEFT);
        if(position.x >= masterSketch.width)
            handleScore(HitSide.RIGHT);
    }

    private void handleMovement()
    {
        position.add(speed);
    }

    private void handleScore(HitSide _side)
    {
        //implement this or something
    }
}
