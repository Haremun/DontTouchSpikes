package com.example.kamil.myapplication.Managers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

import com.example.kamil.myapplication.Objects.Circle;

import java.util.LinkedList;

/**
 * Created by Kamil on 21.11.2017.
 */

public class CirclesManager {

    LinkedList<Circle> circles;
    Rect playerRect;
    int temp = 0;

    public CirclesManager(Rect rectangle){
        circles = new LinkedList<>();
        this.playerRect = rectangle;
    }

    public void Update(float forceY, PointF position){

        if (forceY < 0 && temp % 3 == 0){
            circles.add(new Circle(position, playerRect));
        }

        for(Circle circle: circles){
            circle.Update();
        }

        deleteCircles();
        temp++;
    }

    public void Draw(Canvas canvas){

        for (Circle circle: circles){
            circle.Draw(canvas, Color.RED);
        }

    }

    private void deleteCircles(){
        for(int i = 0; i < circles.size(); i++){
            if (!circles.get(i).getVisible()){
                circles.remove(i);
            }
        }
    }
}
