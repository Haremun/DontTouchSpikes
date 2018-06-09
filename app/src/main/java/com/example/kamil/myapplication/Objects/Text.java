package com.example.kamil.myapplication.Objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.kamil.myapplication.Structures_and_functions.Functions;

/**
 * Created by Kamil on 05.12.2017.
 */

public class Text {

    private String text;
    private Rect border;
    private Paint paint;
    private Point position;

    public Text(String text, int textSize, int color, int posY){
        this.text = text;


        paint = Functions.GetPaintToDrawText(textSize, color);
        border = Functions.GetRectangleFromText(text, paint);

        this.position =  new Point((Functions.screenSize.x - border.width())/2, posY);
    }

    public int getHeight(){
        return border.height();
    }

    public void Draw(Canvas canvas){
        canvas.drawText(text, position.x, position.y, paint);
    }
}
