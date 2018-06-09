package com.example.kamil.myapplication.Objects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

import com.example.kamil.myapplication.Intefaces.BaseObject;

/**
 * Created by Kamil on 20.11.2017.
 */

public class Circle implements BaseObject {

    private PointF position;
    private int radius;
    private int alpha;
    private boolean visible;

    public boolean getVisible(){ return this.visible; }

    public Circle(PointF position, Rect rectangle){

        this.position = new PointF(position.x + rectangle.width()/2, position.y + rectangle.height()/2);
        this.radius = 30;
        this.alpha = 255;
        this.visible = true;

    }

    @Override
    public void Draw(Canvas canvas, int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAlpha(alpha);
        canvas.drawCircle(position.x, position.y, radius, paint);
    }

    @Override
    public void Update() {
        radius -= 2;
        alpha -= 255/15;

        if (radius <= 0){
            visible = false;
        }

    }
}
