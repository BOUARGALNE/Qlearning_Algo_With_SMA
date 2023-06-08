package com.Bouargalne.mas;

public class QLUtils {
    public static final double ALPHA=0.1;
    public static final double GAMMA=0.9;
    public static final double EPSILON=0.3;
    public static final double MAX_EPOCHS=20000;
    public static final int GRID_SIZE=4;
    public static final int ACTIONS_SIZE=4;

    public static final int[][] ACTIONS={{-1,0},{1,0},{0,-1},{0,1}};
    public static final int[][] GRID={
        {0, -1, 0,0},
        {0, 0, 0, 0},
        {0, 0, -1,0},
        {-1, 0, 0,1},

    };

    public static final int NUMBER_OF_AGENTS=5;
}
