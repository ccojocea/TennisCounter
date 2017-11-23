package com.example.android.ccojocea.tenniscounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
    * Variables keeping score for both players: Points in a game, Games in a set and Sets
     */
    int setsPlayerOne;
    int setsPlayerTwo;
    int gamesPlayerOne;
    int gamesPlayerTwo;
    int pointsPlayerOne;
    int pointsPlayerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Display set score for player one
     */
    public void displaySetPlayerOne(int set){
        TextView setScoreView = findViewById(R.id.player_one_sets);
        setScoreView.setText(String.valueOf(set));
    }

    /**
     * Display set score for player two
     */
    public void displaySetPlayerTwo(int set){
        TextView setScoreView = findViewById(R.id.player_two_sets);
        setScoreView.setText(String.valueOf(set));
    }

    /**
     * Display games score for player one
     */
    public void displayGamesPlayerOne(int games){
        TextView setScoreView = findViewById(R.id.player_one_games);
        setScoreView.setText(String.valueOf(games));
    }

    /**
     * Display games score for player two
     */
    public void displayGamesPlayerTwo(int games){
        TextView setScoreView = findViewById(R.id.player_two_games);
        setScoreView.setText(String.valueOf(games));
    }

    /**
     * Display points score for player one
     */
    public void displayPointsPlayerOne(int points){
        TextView setScoreView = findViewById(R.id.player_one_points);
        setScoreView.setText(String.valueOf(points));
    }

    /**
     * Display points score for player two
     */
    public void displayPointsPlayerTwo(int points){
        TextView setScoreView = findViewById(R.id.player_two_points);
        setScoreView.setText(String.valueOf(points));
    }

    public void displayAdvPlayerOne(String adv){
        TextView setScoreView = findViewById(R.id.player_one_points);
        setScoreView.setText(adv);
    }

    public void displayAdvPlayerTwo(String adv){
        TextView setScoreView = findViewById(R.id.player_two_points);
        setScoreView.setText(adv);
    }

    /**
     * Method to add points for player one.
     * Automatically ends games/sets when needed.
     */
    public void addPointsPlayerOne(View view){
        int check = checkGameOver(pointsPlayerOne, pointsPlayerTwo);
        switch (check){
            case 1:
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                gamesPlayerOne++;
                displayGamesPlayerOne(gamesPlayerOne);
                displayPointsPlayerOne(pointsPlayerOne);
                displayPointsPlayerTwo(pointsPlayerTwo);
                break;
            case 2:
                if (pointsPlayerOne == 0){
                    pointsPlayerOne = 15;
                } else if (pointsPlayerOne == 15) {
                    pointsPlayerOne = 30;
                } else if (pointsPlayerOne == 30) {
                    pointsPlayerOne = 40;
                } else if (pointsPlayerOne > 30) {
                    pointsPlayerOne++;
                }
                displayPointsPlayerOne(pointsPlayerOne);
                displayPointsPlayerTwo(pointsPlayerTwo);
                break;
            case 3:
                displayAdvPlayerOne("Adv");
                displayAdvPlayerTwo("-");
                break;
            case 4:
                displayAdvPlayerOne("-");
                displayAdvPlayerTwo("-");
                break;
        }
    }

    public void addPointsPlayerTwo(View view){
        int check = checkGameOver(pointsPlayerTwo, pointsPlayerOne);
        switch (check){
            case 1:
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                gamesPlayerTwo++;
                displayGamesPlayerTwo(gamesPlayerTwo);
                displayPointsPlayerOne(pointsPlayerOne);
                displayPointsPlayerTwo(pointsPlayerTwo);
                break;
            case 2:
                if (pointsPlayerTwo == 0){
                    pointsPlayerTwo = 15;
                } else if (pointsPlayerTwo == 15) {
                    pointsPlayerTwo = 30;
                } else if (pointsPlayerTwo == 30) {
                    pointsPlayerTwo = 40;
                } else if (pointsPlayerTwo > 30) {
                    pointsPlayerTwo++;
                }
                displayPointsPlayerOne(pointsPlayerOne);
                displayPointsPlayerTwo(pointsPlayerTwo);
                break;
            case 3:
                displayAdvPlayerTwo("Adv");
                displayAdvPlayerOne("-");
                break;
            case 4:
                displayAdvPlayerOne("-");
                displayAdvPlayerTwo("-");
                break;
        }
    }

    /**
     * Verifies if the current game is over in order to increase the games score
     * returns 1 if game ends
     * returns 2 if game continues as normal
     * returns 3 if score is 40-40 so it should continue on advantages
     * returns 4 if score before pushing button was Advantage for player B
     */
    public int checkGameOver (int pointsA, int pointsB){
        if(pointsA > 40) {
            if(pointsA > pointsB){
                return 1;
            } else if (pointsA < pointsB){
                return 4;
            }
        }
        if(pointsA == 40){
            if(pointsB != 40){
                return 1;
            } else if (pointsB ==40) {
                return 3;
            }
        }
        return 2;
    }

    /**
     * Verifies if the current set is over in order to increase the sets score
     */
    //public boolean checkSetOver (int games) {

    //}
}
