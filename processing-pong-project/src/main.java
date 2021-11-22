import GameEngine.Engine;
import processing.core.PApplet;

public class main extends PApplet {

    public static void main(String[] args){
        Engine engine = new Engine();
        PApplet.runSketch(new String[]{"Cocaine"}, new Engine());
    }
}
