package com.example.kamil.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.kamil.myapplication.DataBase.DataBaseHelper;
import com.example.kamil.myapplication.Structures_and_functions.Functions;
import com.example.kamil.myapplication.Structures_and_functions.Textures;

public class MainActivity extends Activity {

    GamePanel gamePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindowManager().getDefaultDisplay().getSize(Functions.screenSize);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Bitmap playerTexture = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        Textures.playerTexture = Bitmap.createScaledBitmap(playerTexture, 145 * 3, 93, false);

        Bitmap textureDeadBird = BitmapFactory.decodeResource(getResources(), R.drawable.dead_bird);
        Textures.deadTexture = Bitmap.createScaledBitmap(textureDeadBird, 145, 93, false);

        Textures.fontLicznik = Typeface.createFromAsset(getAssets(), "fonts/unispace bd.ttf");
        Textures.mainFont = Typeface.createFromAsset(getAssets(), "fonts/BlissfulThinking.otf");

        try{
            DataBaseHelper helper = new DataBaseHelper(this);
        } catch (Exception e){}

        gamePanel = new GamePanel(this);

        setContentView(gamePanel);

        Log.v("Text", "Create!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("Text", "Start!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        gamePanel.startGame();
        Log.v("Text", "Resume!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        gamePanel.stopThread();
        Log.v("Text", "Pause!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("Text", "Stop!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Text", "Destroy!");
    }
}
