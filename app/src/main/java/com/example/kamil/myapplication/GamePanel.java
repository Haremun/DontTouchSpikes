package com.example.kamil.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.kamil.myapplication.Enums.GameStates;
import com.example.kamil.myapplication.Managers.ScreenManager;
import com.example.kamil.myapplication.Managers.SpikesManager;
import com.example.kamil.myapplication.Managers.StringsManager;
import com.example.kamil.myapplication.Objects.Player;
import com.example.kamil.myapplication.Structures_and_functions.Functions;
import com.example.kamil.myapplication.Structures_and_functions.Textures;
import com.example.kamil.myapplication.Structures_and_functions.Values;

/**
 * Created by Kamil on 12.11.2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;

    private Player player;
    private SpikesManager spikesManager;
    private ScreenManager screenManager;
    private StringsManager stringsManager;

    private GameStates gameState;

    String path;

    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        gameState = GameStates.Menu;

        player = new Player();
        spikesManager = new SpikesManager(player.getPlayerRectangle());
        screenManager = new ScreenManager(context);
        stringsManager = new StringsManager();

        path = context.getApplicationContext().getPackageName();

        screenManager.ChangeColors();
        stringsManager.loadStrings(screenManager.getColorOfSpikes(), Values.spikesHeight);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //thread = new MainThread(getHolder(), this);
        Log.v("Surface", "Created!");
    }



    public void stopThread(){

        thread.setRunning(false);
        /*try {
            thread.setRunning(false);
            //thread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void resumeThread(){
        thread.setRunning(true);
        thread.notifyAll();
    }

    public void startGame(){
        thread.setRunning(true);
        //thread.start();
        //thread.notifyAll();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        thread.setRunning(true);
        thread.start();

        Log.v("Surface", "Changed!");

        //screenManager.ChangeColors();
        //stringsManager.loadStrings(screenManager.getColorOfSpikes(), spikesManager.getspikesHeight());
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        Log.v("Surface", "Destroyed!");
        thread.setRunning(false);

        /*while (true){
            try {
                thread.setRunning(false);
                //thread.join();

            } catch (Exception e) {e.printStackTrace();}
        }*/
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:{
                player.Jump();
                gameState = GameStates.Play;
            }
        }

        return true;
        //return super.onTouchEvent(event);
    }

    public void update(){

        player.setPlayerGameState(gameState);
        player.Update();

        if(gameState == GameStates.Play){

            if (player.ChangeDirection){
                Functions.Licznik++;
                if(Functions.Licznik == 2){
                    spikesManager.SpikesCountUpdate();
                }
                else if(Functions.Licznik % 5 == 0){
                    screenManager.ChangeColors();
                    spikesManager.SpikesCountUpdate();
                }
                spikesManager.NewSet();

                player.ChangeDirection = false;
            }

            screenManager.Update();
            spikesManager.Update();
            if(spikesManager.getCollided())
                gameState = GameStates.Death;

        }
        else if(gameState == GameStates.Menu){

        }


    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(screenManager.getColorOfBackground());


        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.WHITE);
        canvas.drawCircle(Functions.screenSize.x/2, Functions.screenSize.y/2, 100 * Values.scale, circlePaint);

        if(gameState == GameStates.Menu){

            stringsManager.drawStrings(canvas);
        }
        else if (gameState == GameStates.Play){

            Paint paint = new Paint();
            paint.setTypeface(Textures.fontLicznik);
            paint.setColor(screenManager.getColorOfBackground());
            if (Functions.Licznik < 100)
                paint.setTextSize(380/3 * Values.scale);
            else
                paint.setTextSize(100 * Values.scale);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(String.format("%02d", Functions.Licznik), Functions.screenSize.x/2, Functions.screenSize.y/2 - paint.ascent()/2, paint);

        }

        player.Draw(canvas, 0);
        spikesManager.Draw(canvas, screenManager.getColorOfSpikes());

    }
}
