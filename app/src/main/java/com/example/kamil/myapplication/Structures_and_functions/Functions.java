package com.example.kamil.myapplication.Structures_and_functions;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Kamil on 13.11.2017.
 */

public final class Functions {

    public static Point screenSize = new Point();

    public static int Licznik = 0;

    public static Bitmap RotateBitmap(Bitmap source) {
        Matrix matrix = new Matrix();
        matrix.postScale(-1, 1, source.getWidth()/2, source.getHeight()/2);
        //matrix.postRotate(90);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public static Bitmap RotateBitmapDead(Bitmap source) {
        Matrix matrix = new Matrix();
        //matrix.postScale(-1, 1, source.getWidth()/2, source.getHeight()/2);
        matrix.postRotate(90);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public static Paint GetPaintToDrawText(int textSize, int textColor){

        Paint paint = new Paint();
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTypeface(Textures.mainFont);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);

        return paint;
    }

    public static Rect GetRectangleFromText(String text, Paint paint){
        Rect rectangle = new Rect();
        paint.getTextBounds(text, 0, text.length(), rectangle);
        return rectangle;

    }

}
