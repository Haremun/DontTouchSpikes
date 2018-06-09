package com.example.kamil.myapplication.Managers;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.kamil.myapplication.Intefaces.BaseObject;
import com.example.kamil.myapplication.Enums.Directions;
import com.example.kamil.myapplication.Objects.Spike;
import com.example.kamil.myapplication.Structures_and_functions.Functions;
import com.example.kamil.myapplication.Structures_and_functions.Values;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Kamil on 14.11.2017.
 */

public class SpikesManager implements BaseObject {

    private LinkedList<Spike> spikesLeft;
    private LinkedList<Spike> spikesRight;
    private LinkedList<Spike> spikesTop;
    private LinkedList<Spike> spikesBottom;
    Random rand;

    //private int spikesHeight;
    //private int spikesWidth;
    //private int leftMargin;
    //private int topMargin;

    private Rect player;
    private boolean collided;

    private int spikesCount = 2;
    private int spikesId = 0;

    private boolean checkCollision = true;

    /*public int getspikesHeight(){
        return this.spikesHeight;
    }*/
    //public int getSpikesWidth() { return this.spikesWidth; }

    Directions direction;

    public SpikesManager(Rect player){
        direction = Directions.Right;

        spikesLeft = new LinkedList<>();
        spikesRight = new LinkedList<>();
        spikesTop = new LinkedList<>();
        spikesBottom = new LinkedList<>();

        rand = new Random();
        //spikesHeight = 11 * 43 * 3 + 7 * 3;
        //spikesWidth = 7 * 43 * 3 - 7 * 3;

        //leftMargin = (Functions.screenSize.x - spikesWidth)/2 - 15 * 3;
        //topMargin = (Functions.screenSize.y - spikesHeight)/2;

        this.player = player;

        setSpikes();
    }

    public boolean getCollided(){
        return collided;
    }
    public void checkCollision(boolean checkCollision){ this.checkCollision = checkCollision; }
    @Override
    public void Draw(Canvas canvas, int color) {
        for (Spike spike : spikesLeft) {
            spike.Draw(canvas, color);
        }for (Spike spike : spikesRight) {
            spike.Draw(canvas, color);
        }for (Spike spike : spikesTop) {
            spike.Draw(canvas, color);
        }for (Spike spike : spikesBottom) {
            spike.Draw(canvas, color);
        }

        Paint rectPaint = new Paint();
        rectPaint.setColor(color);
        canvas.drawRect(0, 0, Functions.screenSize.x, Values.topMargin, rectPaint); //Top
        canvas.drawRect(0, Functions.screenSize.y - Values.topMargin, Functions.screenSize.x, Functions.screenSize.y, rectPaint); //Bottom
        canvas.drawRect(0, 0, Values.leftMargin, Functions.screenSize.y, rectPaint); //Left
        canvas.drawRect(Functions.screenSize.x - Values.leftMargin, 0, Functions.screenSize.x, Functions.screenSize.y, rectPaint); //Right

    }

    @Override
    public void Update() {
        for (Spike spike : spikesLeft) {
            spike.Update();
        }
        for (Spike spike : spikesRight) {
            spike.Update();
        }

        if(checkCollision){
            if(direction == Directions.Left){
                for(int i = 0; i < spikesLeft.size(); i++)
                    if(spikesLeft.get(i).checkCollision(player)){
                        collided = true;
                        checkCollision = false;
                    }

            } else if (direction == Directions.Right){
                for(int i = 0; i < spikesRight.size(); i++)
                    if(spikesRight.get(i).checkCollision(player))
                    {
                        collided = true;
                        checkCollision = false;
                    }
            }
            for(int i = 0; i < spikesTop.size(); i++){
                if(spikesTop.get(i).checkCollision(player))
                {
                    collided = true;
                    checkCollision = false;
                }
                if(spikesBottom.get(i).checkCollision(player))
                {
                    collided = true;
                    checkCollision = false;
                }
            }
        } else {
            collided = false;
        }


    }

    public void SpikesCountUpdate(){
        spikesId++;
    }

    private void setSpikes(){ //Początkowe ustawienie schowanych kolców
       // int posY =(Values.bottomWidthOfTriangle + Values.gap) + Values.topMargin + Values.gap;

        for(int i = 0; i < 11; i++){
            spikesRight.add(new Spike(Values.leftMargin, i * (Values.bottomWidthOfTriangle + Values.gap) + Values.topMargin + Values.gap, Directions.Right));
            spikesLeft.add(new Spike(Values.leftMargin, i * (Values.bottomWidthOfTriangle + Values.gap) + Values.topMargin + Values.gap, Directions.Left));

        }
        for(int i = 0; i < 7; i++){
            spikesTop.add(new Spike( i * (Values.bottomWidthOfTriangle + Values.gap) + Values.leftMargin + 2 * Values.gap, Values.topMargin, Directions.Top));
            spikesBottom.add(new Spike(i * (Values.bottomWidthOfTriangle + Values.gap) + Values.leftMargin + 2 * Values.gap, Functions.screenSize.y - Values.topMargin, Directions.Bottom));
        }

    }
    public void NewSet(){  //Wysuwanie i losowanie kolców

        if(spikesId == 1)
            spikesCount = 3;
        else if(spikesId == 2)
            spikesCount = 4;
        else if (spikesId == 3)
            spikesCount = rand.nextInt(2) + 4;
        else if (spikesId >= 4 && spikesId <= 5)
            spikesCount = 5;
        else if (spikesId == 6)
            spikesCount = rand.nextInt(2) + 5;
        else if (spikesId >= 7 && spikesId <= 12)
            spikesCount = rand.nextInt(2) + 6;
        else if (spikesId == 13)
            spikesCount = 7;


        if (direction == Directions.Left){ //Zmiana na prawą stronę

            direction = Directions.Right;

            for(int i = 0; i < spikesCount; i++){
                int temp = rand.nextInt(spikesRight.size());
                if (spikesRight.get(temp).getHiddennes())
                    spikesRight.get(temp).setHiddennes(false);
                else
                    i--;
            }

            for (int i = 0; i < spikesLeft.size(); i++){
                spikesLeft.get(i).setHiddennes(true);
            }
        }
        else{ //Zmiana na lewą stronę

            direction = Directions.Left;

            for(int i = 0; i < spikesCount; i++){
                int temp = rand.nextInt(spikesLeft.size());
                if (spikesLeft.get(temp).getHiddennes())
                    spikesLeft.get(temp).setHiddennes(false);
                else
                    i--;
            }

            for (int i = 0; i < spikesRight.size(); i++){
                spikesRight.get(i).setHiddennes(true);
            }

        }

    }

}
