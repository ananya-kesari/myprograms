package com.example.gamefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //yellow=0,red=1
    int activePlayer=0;
    boolean gameIsActive=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2&&gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1000f).rotation(90).setDuration(300);
            for(int[]winningposition:winningPositions)
                if(gameState[winningposition[0]]==gameState[winningposition[1]]&&gameState[winningposition[1]]==
                        gameState[winningposition[2]]&&gameState[winningposition[0]]!=2) {
                    //someone has won
                    gameIsActive = false;
                    String winner = "Red";
                    if (gameState[winningposition[0]] == 0) {
                        winner = "Yellow";
                    }
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }else{
                    boolean gameIsOver=true;
                    for(int counterState:gameState){
                        if(counterState==2)gameIsOver=false;
                    }
                    if(gameIsOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("ITS A DRAW!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }


                }
        }
    }
    public void playAgain(View view) {
        gameIsActive=true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        LinearLayout linear1 = (LinearLayout) findViewById(R.id.layout1);
        for (int i = 0; i < 3; i++) {
            ((ImageView) linear1.getChildAt(i)).setImageResource(0);
        }
        LinearLayout linear2 = (LinearLayout) findViewById(R.id.layout2);
        for (int i = 0; i < 3; i++) {
            ((ImageView) linear2.getChildAt(i)).setImageResource(0);
        }
        LinearLayout linear3 = (LinearLayout) findViewById(R.id.layout3);
        for (int i = 0; i < 3; i++) {
            ((ImageView) linear3.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
