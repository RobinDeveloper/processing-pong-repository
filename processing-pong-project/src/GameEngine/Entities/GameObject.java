package GameEngine.Entities;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class GameObject {

    protected PApplet masterSketch;
    protected PVector position;
    protected PVector size;
    protected int colour;

    public GameObject(PApplet _masterSketch, PVector _position, PVector _size, int _colour)
    {
        masterSketch = _masterSketch;
        position = _position;
        size = _size;
        colour = _colour;
    }

    public GameObject(PApplet _masterSketch, PVector _position, float _size, int _colour)
    {
        masterSketch = _masterSketch;
        position = _position;
        size = new PVector(_size, _size);
    }

    public abstract void updateObject();
    public abstract void drawObject();

    public PVector getPosition(){
        return position;
    }

    public PVector getSize(){
        return size;
    }

    public PApplet getMasterSketch(){
        return masterSketch;
    }
}
