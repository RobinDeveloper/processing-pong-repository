package GameSandbox;

import GameEngine.Engine;
import GameEngine.Entities.PrimitiveTypes.Ellipse;
import GameEngine.PhysicsEngine.Hitside;
import GameEngine.SceneManagment.Scene;
import GameSandbox.Noise.FlowFieldGeneration;
import processing.core.PApplet;
import processing.core.PVector;

public class Ball extends Ellipse {

    private PVector speed;

    private FlowFieldGeneration flowFieldGeneration;
    private boolean blindPong = false;
    private boolean goInvis = false;


    public Ball(PApplet _masterSketch, PVector _position, float _size, int _colour, PVector _speed) {
        super(_masterSketch, _position, _size, _colour);
        speed = _speed;
        flowFieldGeneration = new FlowFieldGeneration(masterSketch);
    }

    @Override
    public void updateObject() {
        handleMovement();
    }

    @Override
    public void drawObject() {
        if(blindPong && goInvis)
            return;

        masterSketch.fill(colour);
        masterSketch.ellipse(position.x, position.y, size.x, size.y);
    }

    @Override
    public void wallHit(Hitside _hitSide) {
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
        speed = new PVector((speed.x * -1) + masterSketch.random(-5,5) , speed.y + masterSketch.random(-2,2));
    }

    private void handleMovement()
    {
        //PVector[][][] result = flowFieldGeneration.CalculateFlowField(); //TODO implement this
        position.add(speed);
    }

    //Mostly responsible for handling scoring. Not to happy that its so dependent
    private void handleScore(Hitside _hitSide)
    {
        position = new PVector(masterSketch.width/2, masterSketch.height/2);
        speed = new PVector(masterSketch.random(-10,10), masterSketch.random(-10,10));
        Scene activeScene;
        Engine engine;
        if(masterSketch.getClass().equals(Engine.class))
        {
            engine = (Engine)masterSketch;
            if(_hitSide == Hitside.Left) {
                engine.setRightScore(engine.getRightScore() + 1);
                engine.loadRandomScene();
                activeScene = engine.getActiveScene();
                activeScene.updateScore(engine.getLeftScore(), engine.getRightScore());
            }
            if(_hitSide == Hitside.Right) {
                engine.setLeftScore(engine.getLeftScore() + 1);
                engine.loadRandomScene();
                activeScene = engine.getActiveScene();
                activeScene.updateScore(engine.getLeftScore(), engine.getRightScore());
            }
        }

    }

    public boolean getInvis(){
        return goInvis;
    }

    public void setInvis(boolean _setAs){
        goInvis = _setAs;
    }

    public boolean getBlindPong(){
        return blindPong;
    }

    public void setBlindPong(boolean _setAs){
        blindPong = _setAs;
    }

    public PVector getSpeed()
    {
        return speed;

    }
}
