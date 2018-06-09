package com.example.kamil.myapplication.Managers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.kamil.myapplication.Objects.Text;
import com.example.kamil.myapplication.Structures_and_functions.Functions;
import com.example.kamil.myapplication.Structures_and_functions.Values;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kamil on 05.12.2017.
 */

public class StringsManager {

    private List<Text> strings;

    public StringsManager(){
        strings = new LinkedList<>();
    }

    public void loadStrings(int color, int spikesHeight){

        int topMargin = (Functions.screenSize.y - spikesHeight)/2;

        strings.add(new Text("DON'T TOUCH", 135/3 * Values.scale, color, topMargin + 55 * Values.scale ));
        strings.add(new Text("THE SPIKES", 135/3 * Values.scale, color, topMargin + strings.get(0).getHeight() + 61 * Values.scale));
        strings.add(new Text("TAP", 84/3 * Values.scale, Color.RED, Functions.screenSize.y/2 - 200/3 * Values.scale));
        strings.add(new Text("TO JUMP", 84/3 * Values.scale, Color.RED, Functions.screenSize.y/2 - 190/3 * Values.scale + strings.get(2).getHeight()));
        strings.add(new Text("GAMES PLAYED : 49", 84/3 * Values.scale, color, Functions.screenSize.y - topMargin - 26 * Values.scale));
        strings.add(new Text("BEST SCORE : 45", 84/3 * Values.scale, color, Functions.screenSize.y - topMargin - strings.get(4).getHeight() - 32 * Values.scale));

    }

    public void drawStrings(Canvas canvas){
        for(int i = 0; i < strings.size(); i++){
            strings.get(i).Draw(canvas);
        }
    }
}
