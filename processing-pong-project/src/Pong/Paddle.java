package Pong;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Paddle {

    private PApplet sketch;

    private PVector position;
    private PVector size;

    public Paddle(PApplet _sketch, PVector _position, PVector _size){
        sketch = _sketch;
        position = _position;
        size = _size;
    }

    public void drawPaddle(){
        sketch.fill(255);
        sketch.rectMode(PConstants.CENTER);
        sketch.rect(position.x, position.y, size.x, size.y);
    }

    public PVector getSize(){
        return size;
    }

    public PVector getPosition(){
        return position;
    }
}