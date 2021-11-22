package GameEngine.PhysicsEngine;

import GameEngine.Entities.GameObject;
import GameEngine.Entities.PrimitiveTypes.Ellipse;
import GameEngine.Entities.PrimitiveTypes.Rectangle;
import processing.core.PVector;

import java.util.ArrayList;

import static java.lang.Math.*;

public class PhysicsEngine {

    private final ArrayList<GameObject> gameObjects;

    public PhysicsEngine()
    {
        gameObjects = new ArrayList<GameObject>();
    }

    public PhysicsEngine(GameObject _gameObject)
    {
        gameObjects = new ArrayList<GameObject>();
        gameObjects.add(_gameObject);
    }

    public PhysicsEngine(ArrayList<GameObject> _gameObjects)
    {
        gameObjects = _gameObjects;
    }

    public void addGameObject(GameObject _gameObject)
    {
        gameObjects.add(_gameObject);
    }

    // Add ellipse radius too calculations
    private boolean rectEllipseIntersection(Ellipse _ellipse, Rectangle _rectangle){
        PVector circleDistance = new PVector();

        circleDistance.x = abs(_ellipse.getPosition().x - _rectangle.getPosition().x);
        circleDistance.y = abs(_ellipse.getPosition().y - _rectangle.getPosition().y);

        if (circleDistance.x > (_rectangle.getSize().x / 2 + _ellipse.getSize().x)) { return false; }
        if (circleDistance.y > (_rectangle.getSize().y / 2 + _ellipse.getSize().y)) { return false; }

        if (circleDistance.x <= (_rectangle.getSize().x / 2)) { return true; }
        if (circleDistance.y <= (_rectangle.getSize().y / 2)) { return true; }

        double cornerDistance_sq;
        cornerDistance_sq = Math.pow((circleDistance.x - (_rectangle.getSize().x / 2)), 2) +
                Math.pow((circleDistance.y - (_rectangle.getSize().x / 2) ), 2);

        return (cornerDistance_sq <= (Math.pow(_ellipse.getSize().x, 2)));
    }

    //rects get drawn center not leftTop
    private boolean rectIntersection(Rectangle _rectOne, Rectangle _rectTwo)
    {
        return false;
    }

    private boolean rectWallIntersection(Rectangle _rect){
        return _rect.getPosition().y <= 0 + _rect.getSize().y || _rect.getPosition().y >= 720 - _rect.getSize().y;
    }

    private boolean ellipseWallIntersection(Ellipse _ellipse){
        return _ellipse.getPosition().x <= 0 + _ellipse.getSize().x ||
                _ellipse.getPosition().x >= 1080 - _ellipse.getSize().x ||
                _ellipse.getPosition().y >= 720 - _ellipse.getSize().x ||
                _ellipse.getPosition().y <= 0 + _ellipse.getSize().x;
    }
}
