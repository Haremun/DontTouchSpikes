package com.example.kamil.myapplication;

import android.graphics.Rect;

/**
 * Created by Kamil on 21.11.2017.
 */

public class CollisionDetection {


    public static boolean checkCol(Rect rect1, Rect rect2){
        if(rect1.left < rect2.right && rect1.bottom > rect2.top && rect1.top < rect2.bottom)
            return true;

        return false;
    }
}
