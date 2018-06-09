package com.example.kamil.myapplication.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.ViewAnimationUtils;

import com.example.kamil.myapplication.Enums.GameStates;
import com.example.kamil.myapplication.Intefaces.BaseObject;
import com.example.kamil.myapplication.Managers.CirclesManager;
import com.example.kamil.myapplication.Structures_and_functions.Functions;
import com.example.kamil.myapplication.Structures_and_functions.Textures;
import com.example.kamil.myapplication.Structures_and_functions.Values;


/**
 * Created by Kamil on 12.11.2017.
 */

public class Player implements BaseObject {

    private Rect playerRectangle;
    private Rect animationRectangle;
    private PointF position;
    private PointF force;
    private Bitmap playerTexture;

    private short frame = 0;

    private CirclesManager circlesManager;

    public boolean ChangeDirection = false;

    private boolean directionUp = false;
    private boolean changedForce = false;

    GameStates gameState;


    public Player(){

        this.playerRectangle = new Rect(0, 0, 145, 93);
        this.animationRectangle = new Rect(0, 0, 145, 93);

        this.position = new PointF(Functions.screenSize.x/2 - playerRectangle.width()/2, Functions.screenSize.y/2 - playerRectangle.height()/2);;
        this.force = new PointF(20, 5);

        this.playerTexture = Textures.playerTexture;
        this.circlesManager = new CirclesManager(playerRectangle);

    }

    public Rect getPlayerRectangle(){
        return playerRectangle;
    }

    @Override
    public void Draw(Canvas canvas, int color) {

        circlesManager.Draw(canvas);

        if(gameState == GameStates.Play || gameState == GameStates.Menu)
            canvas.drawBitmap(playerTexture, new Rect(145 * frame, 0, 145 + (145 * frame), 93), playerRectangle, null);
        else
            canvas.drawBitmap(Textures.deadTexture,null, new Rect((int)position.x, (int)position.y, Textures.deadTexture.getWidth(), Textures.deadTexture.getHeight()), null);


    }


    @Override
    public void Update() {
        if(gameState == GameStates.Menu){ //Menu

            if(directionUp){
                position.y -= force.y;
                frame = 0;
            }
            else{
                position.y += force.y;
                frame = 2;
            }


            playerRectangle.set((int)position.x, (int)position.y, (int)position.x + playerRectangle.width(), (int)position.y + playerRectangle.height());  //Update position of rectangle in menu

            if (!changedForce)
                force.y--;
            else
                force.y++;

            if (force.y <= 0){
                changedForce = true;
                if (directionUp)
                    directionUp = false;
                else
                    directionUp = true;
            }

            if (force.y >= 10)
                changedForce = false;


            //Log.v("force", String.valueOf(force.y));
        } else if (gameState == GameStates.Play){ //Playing

            position.x += force.x;
            position.y += force.y;
            playerRectangle.set((int)position.x, (int)position.y, (int)position.x + playerRectangle.width(), (int)position.y + playerRectangle.height());  //Update position of rectangle while playing

            if (position.x < Values.leftMargin || position.x > Functions.screenSize.x - Values.leftMargin - playerRectangle.width()){  //Rotate bird after hitting a wall
                force.x = -force.x;
                playerTexture = Functions.RotateBitmap(playerTexture);
                ChangeDirection = true;
            }

            circlesManager.Update(force.y, position);

            if(force.y < 0)
                frame = 1;
            else
                frame = 0;

            force.y += 3;

        } else if (gameState == GameStates.Death){

            position.x += force.x;
            position.y += force.y;
            playerRectangle.set((int)position.x, (int)position.y, (int)position.x + playerRectangle.width(), (int)position.y + playerRectangle.height());  //Update position of rectangle while playing

            if (position.x < Values.leftMargin || position.x > Functions.screenSize.x - Values.leftMargin - playerRectangle.width()){  //Rotate bird after hitting a wall
                force.x = -force.x;
                playerTexture = Functions.RotateBitmap(playerTexture);
                ChangeDirection = true;

                Functions.RotateBitmapDead(Textures.deadTexture);
            }

            if (position.y < Values.topMargin || position.y > Functions.screenSize.y - Values.topMargin - playerRectangle.height()){
                force.y = -force.y;

            }
            //force.y += 5;
        }
    }

    public void Jump(){

        force.y = -30;
    }

    public void setPlayerGameState(GameStates gameState){
        this.gameState = gameState;
    }

}
