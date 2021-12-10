package GameEngine.Entities.PrimitiveTypes;

import GameEngine.Entities.GameObject;
import GameEngine.PhysicsEngine.Hitside;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public abstract class Rectangle extends GameObject {

    public Rectangle(PApplet _masterSketch, PVector _position, PVector _size, int _colour) {
        super(_masterSketch, _position, _size, _colour);
    }

    @Override
    public void drawObject() {
        masterSketch.fill(colour);
        masterSketch.rectMode(PConstants.CENTER);
        masterSketch.rect(position.x, position.y, size.x, size.y);
    }
}
