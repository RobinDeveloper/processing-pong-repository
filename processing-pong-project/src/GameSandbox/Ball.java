package GameSandbox;

import GameEngine.Entities.PrimitiveTypes.Ellipse;
import GameEngine.PhysicsEngine.HitSide;
import processing.core.PApplet;
import processing.core.PVector;

public class Ball extends Ellipse {

    private PVector speed;



    public Ball(PApplet _masterSketch, PVector _position, float _size, int _colour, PVector _speed) {
        super(_masterSketch, _position, _size, _colour);
        speed = _speed;
    }

    @Override
    public void updateObject() {
        handleMovement();
    }

    public void Reflect(HitSide _direction){
        switch (_direction)
        {
            case LEFT -> {
                handleScore(HitSide.LEFT);
            }
            case RIGHT -> {
                handleScore(HitSide.RIGHT);
            }
            case TOP, BOTTOM -> {
                Reflect(new PVector(1, -1));
            }
        }
    }

    public void Reflect(PVector _direction){
        speed.x *= _direction.x;
        speed.y *= _direction.y;
    }

    private void handleMovement()
    {
        position.add(speed);
    }

    private void handleScore(HitSide _side)
    {
        //implement this or something
        position = new PVector(masterSketch.width/2, masterSketch.height/2);
        speed = new PVector(masterSketch.random(-10,10), masterSketch.random(-10,10));
        //ADD SCORE
    }
}
