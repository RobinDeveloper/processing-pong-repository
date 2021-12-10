package GameEngine.PhysicsEngine;

import GameEngine.Entities.GameObject;
import GameEngine.Entities.PrimitiveTypes.Ellipse;
import GameEngine.Entities.PrimitiveTypes.Rectangle;
import GameSandbox.Ball;
import GameSandbox.Player;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static java.lang.Math.*;


public class PhysicsEngine {

    private ArrayList<Ellipse> ellipses;
    private ArrayList<Rectangle> rectangles;

    public PhysicsEngine()
    {
        ellipses = new ArrayList<Ellipse>();
        rectangles = new ArrayList<Rectangle>();
    }

    public PhysicsEngine(GameObject _gameObject)
    {
        ellipses = new ArrayList<Ellipse>();
        rectangles = new ArrayList<Rectangle>();

        if(_gameObject.getClass().equals(Ellipse.class))
            ellipses.add((Ellipse) _gameObject);

        if(_gameObject.getClass().equals(Rectangle.class))
            rectangles.add((Rectangle) _gameObject);
    }

    public PhysicsEngine(ArrayList<GameObject> _gameObjects)
    {
        ellipses = new ArrayList<Ellipse>();
        rectangles = new ArrayList<Rectangle>();

        for (int i = 0; i < _gameObjects.size(); i++) {
            if(_gameObjects.get(i).getClass().equals(Ball.class))
                ellipses.add((Ellipse) _gameObjects.get(i));

            if(_gameObjects.get(i).getClass().equals(Player.class))
                rectangles.add((Rectangle) _gameObjects.get(i));
        }
    }

    public void addGameObject(GameObject _gameObject)
    {
        if(_gameObject.getClass().equals(Ellipse.class))
            ellipses.add((Ellipse) _gameObject);

        if(_gameObject.getClass().equals(Rectangle.class))
            rectangles.add((Rectangle) _gameObject);
    }

    public void updatePhysics(){
        for (int i = 0; i < ellipses.size(); i++) {
            checkEllipseRectangleIntersection(ellipses.get(i));
            checkEllipseWallCollision(ellipses.get(i));
        }
    }

    private void checkEllipseRectangleIntersection(Ellipse _ellipse)
    {
        for (int i = 0; i < rectangles.size(); i++) {
            if(rectEllipseIntersection(_ellipse, rectangles.get(i)))
                _ellipse.rectangleHit();
        }
    }

    // Add ellipse radius too calculations and also use dot product to find direction because rn it hits from the back too
    private boolean rectEllipseIntersection(Ellipse _ellipse, Rectangle _rectangle){
        PVector circleDistance = new PVector();

        circleDistance.x = abs(_ellipse.getPosition().x - _rectangle.getPosition().x);
        circleDistance.y = abs(_ellipse.getPosition().y - _rectangle.getPosition().y);

        if (circleDistance.x > ((_rectangle.getSize().x) / 2)) { return false; }
        if (circleDistance.y > ((_rectangle.getSize().y) / 2)) { return false; }

        if (circleDistance.x <= ((_rectangle.getSize().x) / 2)) { return true; }
        if (circleDistance.y <= ((_rectangle.getSize().y)  / 2)) { return true; }

        double cornerDistance_sq;
        cornerDistance_sq = Math.pow((circleDistance.x - (_rectangle.getSize().x / 2)), 2) +
                Math.pow((circleDistance.y - (_rectangle.getSize().x / 2) ), 2);

        return (cornerDistance_sq <= (Math.pow(_ellipse.getSize().x, 2)));
    }

    private boolean rectRectCollision(Rectangle _rectangle, Rectangle _collisionRectangle){
        return (_rectangle.getPosition().y - (_rectangle.getSize().y / 2)) <=
                (_collisionRectangle.getPosition().y - (_collisionRectangle.getSize().y / 2)) ||
                (_collisionRectangle.getPosition().y - (_collisionRectangle.getSize().y / 2)) <=
                        (_rectangle.getPosition().y - (_rectangle.getSize().y / 2));
    }

    private void checkEllipseWallCollision(Ellipse _ellipse){
        PApplet sketch = _ellipse.getMasterSketch();

        if(_ellipse.getPosition().x <= 0)
            _ellipse.wallHit(Hitside.Left);

        if(_ellipse.getPosition().x >= sketch.width)
            _ellipse.wallHit(Hitside.Right);

        if(_ellipse.getPosition().y <= 0)
            _ellipse.wallHit(Hitside.Bottom);

        if(_ellipse.getPosition().y >= sketch.height)
            _ellipse.wallHit(Hitside.Top);
    }
}
