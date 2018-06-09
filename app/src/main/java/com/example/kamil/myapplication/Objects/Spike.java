package com.example.kamil.myapplication.Objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.kamil.myapplication.Intefaces.BaseObject;
import com.example.kamil.myapplication.Enums.Directions;
import com.example.kamil.myapplication.Structures_and_functions.Functions;
import com.example.kamil.myapplication.Structures_and_functions.Values;

/**
 * Created by Kamil on 14.11.2017.
 */

public class Spike implements BaseObject {

    private Rect hitBox;

    private Point first;
    private Point second;
    private Point third;

    private int x = Functions.screenSize.x;
    private int margin;

    private Directions myDirection;
    private boolean hidden = true;

    private int speedOfAniamtion = 7;


    public Spike(int posX, int posY, Directions direction){

        int multipler = 3;
        margin = posX;

        if (direction == Directions.Left){
            first = new Point(posX, posY + Values.centerOfTriangle);
            second = new Point(posX - Values.heightOfTriangle, posY);
            third = new Point(posX - Values.heightOfTriangle, posY + Values.bottomWidthOfTriangle);
            hitBox = new Rect(posX, posY + 14 * multipler, 21 * multipler + posX, posY + Values.heightOfTriangle);
        }
        else if (direction == Directions.Right){
            first = new Point(x - posX, posY + Values.centerOfTriangle);
            second = new Point(x + Values.heightOfTriangle - posX, posY);
            third = new Point(x + Values.heightOfTriangle - posX, posY + Values.bottomWidthOfTriangle);
            hitBox = new Rect(x - Values.heightOfTriangle - posX,posY + 14 * multipler, x, posY + Values.heightOfTriangle);
        }
        else if (direction == Directions.Top){
            first = new Point(posX + Values.centerOfTriangle, posY + Values.heightOfTriangle);
            second = new Point(posX, posY);
            third = new Point(posX + Values.bottomWidthOfTriangle, posY);
            hitBox = new Rect(posX + 14 * multipler, posY, posX + Values.heightOfTriangle, posY + Values.heightOfTriangle);
            hidden = false;
        }
        else if (direction == Directions.Bottom){
            first = new Point(posX + Values.centerOfTriangle, posY - Values.heightOfTriangle);
            second = new Point(posX, posY);
            third = new Point(posX + Values.bottomWidthOfTriangle, posY);
            hitBox = new Rect(posX + 14 * multipler, posY - Values.heightOfTriangle, posX + Values.heightOfTriangle, posY);
            hidden = false;
        }

        myDirection = direction;
    }

    @Override
    public void Draw(Canvas canvas, int color) {

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(first.x, first.y);
        path.lineTo(second.x, second.y);
        path.lineTo(third.x, third.y);
        path.lineTo(first.x, first.y);
        path.close();
        canvas.drawPath(path, paint);
        //canvas.drawRect(hitBox, new Paint());

    }

    @Override
    public void Update() {
        if (!hidden){
            for (int i = 0; i < speedOfAniamtion; i++){
                if(first.x > x - Values.heightOfTriangle - margin && myDirection == Directions.Right){
                    first.x -= 1;
                    second.x -= 1;
                    third.x -= 1;
                } else if (first.x < Values.heightOfTriangle + margin && myDirection == Directions.Left){
                    first.x += 1;
                    second.x += 1;
                    third.x += 1;
                }
            }
        } else{
            for (int i = 0; i < speedOfAniamtion; i++){
                if(first.x <= x - margin && myDirection == Directions.Right){
                    first.x += 1;
                    second.x += 1;
                    third.x += 1;
                } else if (first.x > margin && myDirection == Directions.Left){
                    first.x -= 1;
                    second.x -= 1;
                    third.x -= 1;
                }
            }
        }

    }

    public boolean checkCollision(Rect player){
        if(!hidden && Rect.intersects(player, hitBox))
            return true;
        return  false;
    }

    public void setHiddennes(boolean hidden){
        this.hidden = hidden;
    }
    public boolean getHiddennes(){
        return this.hidden;
    }
}
