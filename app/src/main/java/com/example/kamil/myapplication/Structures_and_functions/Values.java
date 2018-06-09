package com.example.kamil.myapplication.Structures_and_functions;

/**
 * Created by Kamil on 01.12.2017.
 */

public class Values {

    private static int numberOfSpikesOnTheWall = 11;
    private static int numberOfSpikesOnTheTopWall = 7;

    public static int scale = 3;

    public static int bottomWidthOfTriangle = 36 * scale;
    public static int heightOfTriangle = 21 * scale;
    public static int centerOfTriangle = bottomWidthOfTriangle /2;

    public static int gap = 7 * scale;

    public static int spikesHeight = numberOfSpikesOnTheWall * (bottomWidthOfTriangle + gap)  + gap; //One gap on each side
    public static int spikesWidth = numberOfSpikesOnTheTopWall * (bottomWidthOfTriangle + gap) + 3 * gap; //Two gaps on each side

    public static int topMargin = (Functions.screenSize.y - spikesHeight)/2;
    public static int leftMargin = (Functions.screenSize.x - spikesWidth)/2;
}
