package GameEngine.Entities.PrimitiveTypes;

import GameEngine.Entities.GameObject;
import GameEngine.PhysicsEngine.Hitside;
import processing.core.PApplet;
import processing.core.PVector;

// Base class for anything ellipsoid
public abstract class Ellipse extends GameObject {

    public Ellipse(PApplet _masterSketch, PVector _position, float _size, int _colour) {
        super(_masterSketch, _position, _size, _colour);
    }

    //base functions which can be called in bulk without knowing child type
    public abstract void drawObject();

    public abstract void wallHit(Hitside _hitSide);
    public abstract void rectangleHit();
}
