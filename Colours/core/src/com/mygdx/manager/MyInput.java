package com.mygdx.manager;


public class MyInput {
    public static int x;
    public static int y;
    public static boolean down;
    public static boolean pdown;

    public static boolean[] keys;
    public static boolean[] pkeys;

    public static final int NUM_KEYS=8;
    public static final int UP=0;
    public static final int LEFT=1;
    public static final int DOWN=2;
    public static final int RIGHT=3;
    public static final int ENTER=4;
    public static final int ESCAPE=5;
    public static final int SPACE=6;
    public static final int SHIFT=7;

    static{
        keys=new boolean[NUM_KEYS];
        pkeys=new boolean[NUM_KEYS];

    }

    public static void update(){
        pdown=down;
        for(int i=0;i<NUM_KEYS;i++){
            pkeys[i]=keys[i];
        }
    }

    public static boolean isDown() { return down; }
    public static boolean isPressed() { return down && !pdown; }
    public static boolean isReleased() { return !down && pdown; }
    public static void setKey(int i,boolean b){ keys[i]=b;}
    public static boolean isPressed(int i){ return keys[i] && !pkeys[i]; }
    public static boolean isDown(int i){ return keys[i]; }

}
