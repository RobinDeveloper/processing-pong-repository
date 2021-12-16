package GameEngine.Entities;

import processing.core.PApplet;
import processing.core.PVector;

//Base class for all gameObject in a scene
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
        colour = _colour;
    }

    //Base functions to call without knowing child type
    public abstract void updateObject();
    public abstract void drawObject();

    //Standerd get functions for member variables
    public PVector getPosition(){
        return position;
    }

    public PVector getSize(){
        return size;
    }

    public int getColour() { return colour; }

    public PApplet getMasterSketch(){
        return masterSketch;
    }

}
