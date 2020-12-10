package com.example.tiktaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//Player Representation
    //0 - X
    //1 - O

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2 ,2 ,2};
    boolean gameActive = true;
    //State meaning
    //1 - 0
    //0 - x
    //x - Blank
    int[][] WinPosition = {{0, 1, 2}, {3,4,5}, {6,7,8},
                           {0,3,6}, {1,4,7}, {2,5,8},
                           {0,4,8}, {2,4,6}};
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            gameReset(view);
        }
        if (gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.Status);
                status.setText("O's TURN TAP TO PLAY");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.Status);
                status.setText("x's TURN TAP TO PLAY");
            }
            /* it consist with animation */
            img.animate().translationYBy(1000f).setDuration(300);
        }else {
            gameReset(view);
        }
        //check if any player has won
        for (int[] WinPosition: WinPosition){
            if (gameState[WinPosition[0]]==gameState[WinPosition[1]] &&
                    gameState[WinPosition[1]]==gameState[WinPosition[2]] &&
                    gameState[WinPosition[0]]!=2){
                //find out who! can won
                String WinStr;
                gameActive =false;
                if (gameState[WinPosition[0]]==0){
                    WinStr = " CONGRATULATIONS X HAS WON!";
                }else {
                    WinStr = "o has won";
                }
                // update a status bar for winner announcement.
                TextView status = findViewById(R.id.Status);
                status.setText(WinStr);
            }
        }
    }
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.Status);
        status.setText("x's TURN TAP TO PLAY");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}