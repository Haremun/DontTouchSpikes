package com.example.kamil.myapplication.Managers;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;

import com.example.kamil.myapplication.DataBase.SQLiteFunctions;


/**
 * Created by Kamil on 19.11.2017.
 */

public class ScreenManager{

    Color color;

    private int colorOfBackgroud = -1;
    private int colorOfSpikes = -1;

    private int newColorOfBackground;
    private  int newColorOfSpikes;

    private int oldBackground;
    private int oldSpikes;

    private boolean colorChanged;
    private float ratio = 1f;

    private Context context;

    private int id;

    public ScreenManager(Context context){

        this.id = 1;
        this.context = context;
        this.colorChanged = true;

    }

    public int getColorOfBackground(){
        return colorOfBackgroud;
    }
    public int getColorOfSpikes(){
        return colorOfSpikes;
    }

    public void ChangeColors(){
        if(id <= 21){
            Cursor cursor = SQLiteFunctions.LoadOneRowById(context, id);
            id++;
            if(colorOfBackgroud != -1){
                newColorOfBackground = Color.parseColor("#" + cursor.getString(1));
                newColorOfSpikes = Color.parseColor( "#" + cursor.getString(2));

                oldBackground = colorOfBackgroud;
                oldSpikes = colorOfSpikes;

                colorChanged = false;
                ratio = 1f;
            } else {
                colorOfBackgroud = Color.parseColor("#" + cursor.getString(1));
                colorOfSpikes = Color.parseColor( "#" + cursor.getString(2));
            }
        }

    }

    public void Update(){

        if (!colorChanged){

            colorOfBackgroud = blendColors(oldBackground, newColorOfBackground, ratio);
            colorOfSpikes = blendColors(oldSpikes, newColorOfSpikes, ratio);

            ratio -= 0.1f;

            if (ratio <= 0){
                colorChanged = true;

            }

        }
    }

    public int blendColors(int color1, int color2, float ratio){
        final float inverseRation = 1f - ratio;
        float r = (Color.red(color1) * ratio) + (Color.red(color2) * inverseRation);
        float g = (Color.green(color1) * ratio) + (Color.green(color2) * inverseRation);
        float b = (Color.blue(color1) * ratio) + (Color.blue(color2) * inverseRation);
        return Color.rgb((int) r, (int) g, (int) b);
    }
}
