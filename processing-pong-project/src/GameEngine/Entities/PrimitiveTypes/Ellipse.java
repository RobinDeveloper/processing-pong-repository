package GameEngine.Entities.PrimitiveTypes;

import GameEngine.Entities.GameObject;
import processing.core.PApplet;
import processing.core.PVector;

public abstract class Ellipse extends GameObject {

    public Ellipse(PApplet _masterSketch, PVector _position, float _size, int _colour) {
        super(_masterSketch, _position, _size, _colour);
    }

    @Override
    public void drawObject() {
        masterSketch.fill(colour);
        masterSketch.ellipse(position.x, position.y, size.x, size.y);
    }
}
