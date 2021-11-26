package GameSandbox;

import GameEngine.Engine;
import GameEngine.Entities.PrimitiveTypes.Ellipse;
import GameEngine.PhysicsEngine.Hitside;
import GameEngine.SceneManagment.Scene;
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

    @Override
    public void wallHit(Hitside _hitSide) {
        System.out.println("Hit Wall");
        switch (_hitSide)
        {
            case Left, Right -> {
                handleScore(_hitSide);
            }
            case Top, Bottom -> {
                speed = new PVector(speed.x, speed.y * -1);
            }
        }
    }

    @Override
    public void rectangleHit() {
        System.out.println("Hit Player");
        speed = new PVector(speed.x * -1, speed.y);
    }

    private void handleMovement()
    {
        position.add(speed);
    }

    private void handleScore(Hitside _hitSide)
    {
        position = new PVector(masterSketch.width/2, masterSketch.height/2);
        speed = new PVector(masterSketch.random(-10,10), masterSketch.random(-10,10));
        //ADD SCORE
        Scene activeScene;
        Engine engine;
        if(masterSketch.getClass().equals(Engine.class))
        {
            engine = (Engine)masterSketch;
            activeScene = engine.getActiveScene();
            if(_hitSide == Hitside.Left)
                activeScene.updateScore(0,1);
            if(_hitSide == Hitside.Right)
                activeScene.updateScore(1,0);
        }

    }
}
