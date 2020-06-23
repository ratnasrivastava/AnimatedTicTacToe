package com.ratnasrivastava.animatedtictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "unique7";
    TextView winnerTextView;
    private boolean player1 = true;
    private boolean gameActive = true;
    private int[] gameBoard = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int[][] winningSituations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    Button playAgainButton;
    private static int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hello
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winnerTextView = findViewById(R.id.winnerTextView);
        playAgainButton = findViewById(R.id.playAgain);
    }

    public void dropIn(View view) {
        count++;
        final ImageView imageView = (ImageView) view;
        int tag = Integer.parseInt(imageView.getTag().toString());
        if (gameActive && gameBoard[tag] == 2) {
            imageView.setTranslationY(-1500);
            if (player1) {
                imageView.setImageResource(R.drawable.red);
                gameBoard[tag] = 0;
            } else {
                imageView.setImageResource(R.drawable.yellow);
                gameBoard[tag] = 1;
            }
            imageView.animate().translationYBy(1500).rotation(3600).setDuration(300);
            if (checkForWin()) {
                Toast.makeText(this, player1 ? "player1 is winner" : "player2 is winner", Toast.LENGTH_SHORT).show();
                winnerTextView.setText(player1 ? "player1 is winner" : "player2 is winner");
                gameActive = false;
                dispalyViews();
            }
            if (count == 9) {
                Toast.makeText(this, "Tie", Toast.LENGTH_SHORT).show();
                winnerTextView.setText("Tie");
                dispalyViews();
                gameActive = false;
            }
            player1 = !player1;
        }
    }

    private void dispalyViews() {
        winnerTextView.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.VISIBLE);
    }

    private boolean checkForWin() {
        for (int win[] : winningSituations) {
            if (gameBoard[win[0]] != 2 && gameBoard[win[0]] == gameBoard[win[1]] && gameBoard[win[0]] == gameBoard[win[2]]) {
                return true;
            }
        }
        return false;
    }

    public void playAgain(View view) {
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageDrawable(null);
        }
        for (int i = 0; i < 9; i++) {
            gameBoard[i] = 2;
        }
        winnerTextView.setVisibility(View.INVISIBLE);
        count = 0;
        player1 = true;
        gameActive = true;
    }

    //TODO
    //1. start a different thread for animation
    //2. Shared preference for rotation of screen
}