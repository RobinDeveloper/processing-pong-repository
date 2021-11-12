import Pong.PongGame;
import processing.core.PApplet;

public class main extends PApplet {

    public static void main(String[] args){

        PApplet pong = new PongGame();
        PApplet.runSketch(new String[]{"Pong"}, pong);
    }
}
