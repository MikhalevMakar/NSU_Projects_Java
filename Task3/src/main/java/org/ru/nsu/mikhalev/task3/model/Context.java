package org.ru.nsu.mikhalev.task3.model;

public class Context {
        private static final int SCALE = 2;
        private static final int WIDTH = 252;
        private static final int HEIGHT = 420;
        private static final int OFFSET_TABLE_X = 400;
        private static final int OFFSET_TABLE_Y = 20;
        private static final int RATIO_SHAPE_VAlUE = 10;
        private static final int OFFSET_SHARED = HEIGHT / RATIO_SHAPE_VAlUE;
        private static final int RATE_VALUE = RATIO_SHAPE_VAlUE * SCALE;
        private static final String LOGO_TETRIS = "../Task3/src/main/resources/LogoTetris.png";
        private static final int NUMBER_CUBES_ROW = 12;

        static public int getSCALE() { return SCALE; }
        static public int getWIDTH() { return WIDTH; }
        static public int getHEIGHT() { return HEIGHT; }
        static public int getOFFSET_TABLE_X() { return OFFSET_TABLE_X; }
        static public int getOFFSET_TABLE_Y() { return OFFSET_TABLE_Y; }
        static public int getOFFSET_SHARED() { return OFFSET_SHARED; }
        static public int getRATE_VALUE() { return RATE_VALUE; }
        static public String getLOGO_TETRIS() { return LOGO_TETRIS; }
        static public int getREAL_HEIGHT() { return SCALE*HEIGHT; }
        static public int getREAL_WIDTH() { return SCALE*WIDTH; }
        static public int getNUMBER_CUBES_ROW() { return NUMBER_CUBES_ROW; }
}