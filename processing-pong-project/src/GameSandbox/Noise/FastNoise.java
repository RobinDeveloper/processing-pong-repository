package GameSandbox.Noise;

import processing.core.PVector;

//Biggest bundle of bullshit ever barely know if it works.
public class FastNoise {

    private static int seed = 1337;
    private static float frequency = 0.01f;

    public static float getSimplex(float _x, float _y, float _z){
        return singleSimplex(seed, _x * frequency, _y *frequency, _z * frequency);
    }

    private static int FastFloor(float f) { return (f >= 0 ? (int)f : (int)f - 1); }


    private static float F3 = (float)(1.0 / 3.0);
    private static float G3 = (float)(1.0 / 6.0);
    private static float G33 = G3 * 3 - 1;



    private static float singleSimplex(int _seed, float _x, float _y, float _z){

        float t = (_x + _y + _z) * F3;
        int i = FastFloor(_x + t);
        int j = FastFloor(_y + t);
        int k = FastFloor(_z + t);

        t = (i + j + k) * G3;
        float x0 = _x - (i - t);
        float y0 = _y - (j - t);
        float z0 = _z - (k - t);

        int i1, j1, k1;
        int i2, j2, k2;

        if (x0 >= y0)
            if (y0 >= z0) {
                i1 = 1;
                j1 = 0;
                k1 = 0;
                i2 = 1;
                j2 = 1;
                k2 = 0;
            } else if (x0 >= z0) {
                i1 = 1;
                j1 = 0;
                k1 = 0;
                i2 = 1;
                j2 = 0;
                k2 = 1;
            } else // x0 < z0
            {
                i1 = 0;
                j1 = 0;
                k1 = 1;
                i2 = 1;
                j2 = 0;
                k2 = 1;
            }
        else // x0 < y0
        {
            if (y0 < z0)
            {
                i1 = 0; j1 = 0; k1 = 1; i2 = 0; j2 = 1; k2 = 1;
            }
            else if (x0 < z0)
            {
                i1 = 0; j1 = 1; k1 = 0; i2 = 0; j2 = 1; k2 = 1;
            }
            else // x0 >= z0
            {
                i1 = 0; j1 = 1; k1 = 0; i2 = 1; j2 = 1; k2 = 0;
            }
        }

        float x1 = x0 - i1 + G3;
        float y1 = y0 - j1 + G3;
        float z1 = z0 - k1 + G3;
        float x2 = x0 - i2 + F3;
        float y2 = y0 - j2 + F3;
        float z2 = z0 - k2 + F3;
        float x3 = x0 + G33;
        float y3 = y0 + G33;
        float z3 = z0 + G33;

        float n0, n1, n2, n3;

        t = (float)0.6 - x0 * x0 - y0 * y0 - z0 * z0;
        if (t < 0) n0 = 0;
        else
        {
            t *= t;
            n0 = t * t * GradCoord3D(seed, i, j, k, x0, y0, z0);
        }

        t = (float)0.6 - x1 * x1 - y1 * y1 - z1 * z1;
        if (t < 0) n1 = 0;
        else
        {
            t *= t;
            n1 = t * t * GradCoord3D(seed, i + i1, j + j1, k + k1, x1, y1, z1);
        }

        t = (float)0.6 - x2 * x2 - y2 * y2 - z2 * z2;
        if (t < 0) n2 = 0;
        else
        {
            t *= t;
            n2 = t * t * GradCoord3D(seed, i + i2, j + j2, k + k2, x2, y2, z2);
        }

        t = (float)0.6 - x3 * x3 - y3 * y3 - z3 * z3;
        if (t < 0) n3 = 0;
        else
        {
            t *= t;
            n3 = t * t * GradCoord3D(seed, i + 1, j + 1, k + 1, x3, y3, z3);
        }

        return 32 * (n0 + n1 + n2 + n3);
    }

    private static int X_PRIME = 1619;
    private static int Y_PRIME = 31337;
    private static int Z_PRIME = 6971;

    private static float GradCoord3D(int seed, int x, int y, int z, float xd, float yd, float zd)
    {
        int hash = seed;
        hash ^= X_PRIME * x;
        hash ^= Y_PRIME * y;
        hash ^= Z_PRIME * z;

        hash = hash * hash * hash * 60493;
        hash = (hash >> 13) ^ hash;

        PVector g = GRAD_3D[hash & 15];

        return xd * g.x + yd * g.y + zd * g.z;
    }

    private static PVector[] GRAD_3D = {
        new PVector( 1, 1, 0), new PVector(-1, 1, 0), new PVector( 1,-1, 0), new PVector(-1,-1, 0),
                new PVector( 1, 0, 1), new PVector(-1, 0, 1), new PVector( 1, 0,-1), new PVector(-1, 0,-1),
                new PVector( 0, 1, 1), new PVector( 0,-1, 1), new PVector( 0, 1,-1), new PVector( 0,-1,-1),
                new PVector( 1, 1, 0), new PVector( 0,-1, 1), new PVector(-1, 1, 0), new PVector( 0,-1,-1),
    };
}
