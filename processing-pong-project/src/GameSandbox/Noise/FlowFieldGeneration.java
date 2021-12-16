package GameSandbox.Noise;

import processing.core.PApplet;
import processing.core.PVector;

//Again can't get it too work properly. need to spend some more time on it.
public class FlowFieldGeneration {

    private PVector grid = new PVector(100,100,1);
    private PVector offset = new PVector(0,0,0);
    private PVector offsetSpeed = new PVector(140,120,0);
    private PVector[][][] flowFieldDirection;
    private PApplet masterSketch;
    private float increment = 4;
    private FastNoise fastNoise;

    public FlowFieldGeneration(PApplet _masterSketch)
    {
        masterSketch = _masterSketch;
        grid = new PVector(masterSketch.width, masterSketch.height, 1);
        flowFieldDirection = new PVector[(int)grid.x][(int)grid.y][(int)grid.z];
    }

    public PVector[][][] CalculateFlowField()
    {
        float time = masterSketch.frameCount;
        offset.add(offsetSpeed.mult(time));

        float xOffset = 0;
        for (int x = 0; x < grid.x; x++)
        {
            float yOffset = 0;
            for (int y = 0; y < grid.y; y++)
            {
                float zOffset = 0;
                for (int z = 0; z < grid.z; z++)
                {
                    float noise = FastNoise.getSimplex(xOffset + offset.x, yOffset + offset.y, zOffset + offset.z) + 1;
                    PVector noiseDirection = new PVector(masterSketch.cos(noise * masterSketch.PI),masterSketch.sin(noise * masterSketch.PI), masterSketch.cos(noise * masterSketch.PI));
                    flowFieldDirection[x][y][z] = noiseDirection.normalize();


                    zOffset += increment;
                }
                yOffset += increment;
            }
            xOffset += increment;
        }

        return flowFieldDirection;
    }
}
