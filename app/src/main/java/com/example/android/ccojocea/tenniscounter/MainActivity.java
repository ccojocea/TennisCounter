package com.example.android.ccojocea.tenniscounter;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
    * Variables keeping score for both players: Points in a game, Games in a set and Sets*
     * Undo variables save scores between button presses
     * tiebreak variable for tiebreak determination
     * */
    int setsPlayerOne;
    int setsPlayerTwo;
    int gamesPlayerOne;
    int gamesPlayerTwo;
    int pointsPlayerOne;
    int pointsPlayerTwo;
    boolean tiebreak;
    int undoSetsPlayerOne;
    int undoSetsPlayerTwo;
    int undoGamesPlayerOne;
    int undoGamesPlayerTwo;
    int undoPointsPlayerOne;
    int undoPointsPlayerTwo;
    boolean undoTiebreak;

    /**
     * The one which brings light into darkness!
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("setsPlayerOne", setsPlayerOne);
        outState.putInt("setsPlayerTwo", setsPlayerTwo);
        outState.putInt("gamesPlayerOne", gamesPlayerOne);
        outState.putInt("gamesPlayerTwo", gamesPlayerTwo);
        outState.putInt("pointsPlayerOne", pointsPlayerOne);
        outState.putInt("pointsPlayerTwo", pointsPlayerTwo);
        outState.putInt("undoSetsPlayerOne", undoSetsPlayerOne);
        outState.putInt("undoSetsPlayerTwo", undoSetsPlayerTwo);
        outState.putInt("undoGamesPlayerOne", undoGamesPlayerOne);
        outState.putInt("undoGamesPlayerTwo", undoGamesPlayerTwo);
        outState.putInt("undoPointsPlayerOne", undoPointsPlayerOne);
        outState.putInt("undoPointsPlayerTwo", undoPointsPlayerTwo);
        outState.putBoolean("tiebreak", tiebreak);
        outState.putBoolean("undoTiebreak", undoTiebreak);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setsPlayerOne = savedInstanceState.getInt("setsPlayerOne");
        setsPlayerTwo = savedInstanceState.getInt("setsPlayerTwo");
        gamesPlayerOne = savedInstanceState.getInt("gamesPlayerOne");
        gamesPlayerTwo = savedInstanceState.getInt("gamesPlayerTwo");
        pointsPlayerOne = savedInstanceState.getInt("pointsPlayerOne");
        pointsPlayerTwo = savedInstanceState.getInt("pointsPlayerTwo");
        tiebreak = savedInstanceState.getBoolean("tiebreak");
        undoSetsPlayerOne = savedInstanceState.getInt("undoSetsPlayerOne");
        undoSetsPlayerTwo = savedInstanceState.getInt("undoSetsPlayerTwo");
        undoGamesPlayerOne = savedInstanceState.getInt("undoGamesPlayerOne");
        undoGamesPlayerTwo = savedInstanceState.getInt("undoGamesPlayerTwo");
        undoPointsPlayerOne = savedInstanceState.getInt("undoPointsPlayerOne");
        undoPointsPlayerTwo = savedInstanceState.getInt("undoPointsPlayerTwo");
        undoTiebreak = savedInstanceState.getBoolean("undoTiebreak");
        displayAll();
    }

    /**
     * Display all scores
     */
    public void displayAll(){
        displayGamesPlayerOne(gamesPlayerOne);
        displayGamesPlayerTwo(gamesPlayerTwo);
        displayPointsPlayerOne(pointsPlayerOne);
        displayPointsPlayerTwo(pointsPlayerTwo);
        displaySetPlayerOne(setsPlayerOne);
        displaySetPlayerTwo(setsPlayerTwo);
    }

    /**
     * Set all variables to zero
     */
    public void setAllZero(){
        setsPlayerOne = 0;
        setsPlayerTwo = 0;
        gamesPlayerOne = 0;
        gamesPlayerTwo = 0;
        pointsPlayerOne = 0;
        pointsPlayerTwo = 0;
        tiebreak = false;
    }

    /**
     * Save the state of current variables for use in undo
     */
    public void undoSave(){
        undoSetsPlayerOne = setsPlayerOne;
        undoSetsPlayerTwo = setsPlayerTwo;
        undoGamesPlayerOne = gamesPlayerOne;
        undoGamesPlayerTwo = gamesPlayerTwo;
        undoPointsPlayerOne = pointsPlayerOne;
        undoPointsPlayerTwo = pointsPlayerTwo;
        undoTiebreak = tiebreak;
    }

    /**
     * Restore the state of variables on undo button press
     */
    public void undoRestore(){
        setsPlayerOne = undoSetsPlayerOne;
        setsPlayerTwo = undoSetsPlayerTwo;
        gamesPlayerOne = undoGamesPlayerOne;
        gamesPlayerTwo = undoGamesPlayerTwo;
        pointsPlayerOne = undoPointsPlayerOne;
        pointsPlayerTwo = undoPointsPlayerTwo;
        tiebreak = undoTiebreak;
    }

    /**
     * Save current variables
     * Reset scores to zero
     */
    public void resetScores(View view){
        Button undoButton = findViewById(R.id.undo_button);
        undoButton.setEnabled(true);
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setEnabled(false);

        undoSave();
        setAllZero();
        displayAll();
    }

    /**
     * Undo previously added points to player one or two.
     * Button gets disabled after using.
     */
    public void undoLastAction(View view){
        Button undoButton = findViewById(R.id.undo_button);
        undoButton.setEnabled(false);
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setEnabled(true);

        undoRestore();
        displayAll();
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

    /**
     * Display the String adv for player one after Deuce (can be either Adv or -)
     */
    public void displayAdvPlayerOne(String adv){
        TextView setScoreView = findViewById(R.id.player_one_points);
        setScoreView.setText(adv);
    }

    /**
     * Display the String adv for player two after Deuce (can be either Adv or -)
     */
    public void displayAdvPlayerTwo(String adv){
        TextView setScoreView = findViewById(R.id.player_two_points);
        setScoreView.setText(adv);
    }

    /**
     * Method called by button press for Player One.
     * Checks if it's tiebreak or normal game time and adds points as needed.
     * Also modifies the state of the Undo button to true in case it was previously used.
     * Goes into the method to add points if it's not tiebreak
     */
    public void addPointsPlOne(View view){
        Button undoButton = findViewById(R.id.undo_button);
        undoButton.setEnabled(true);
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setEnabled(true);

        undoSave();

        if (tiebreak) {
            boolean check = checkTieOver(pointsPlayerOne, pointsPlayerTwo);
            if (check){
                tiebreak = false;
                setsPlayerOne++;
                gamesPlayerOne = 0;
                gamesPlayerTwo = 0;
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                displayAll();
            } else {
                pointsPlayerOne++;
                displayPointsPlayerOne(pointsPlayerOne);
            }

        } else {
            addPointsPlayerOne();
        }
    }

    /**
     * Method called by button press for Player Two.
     * Checks if it's tiebreak or normal game time and adds points as needed.
     * Also modifies the state of the Undo button to true in case it was previously used.
     * Goes into the method to add points if it's not tiebreak
     */
    public void addPointsPlTwo(View view){
        Button undoButton = findViewById(R.id.undo_button);
        undoButton.setEnabled(true);
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setEnabled(true);

        undoSave();

        if (tiebreak) {
            boolean check = checkTieOver(pointsPlayerTwo, pointsPlayerOne);
            if (check){
                tiebreak = false;
                setsPlayerTwo++;
                gamesPlayerOne = 0;
                gamesPlayerTwo = 0;
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                displayAll();
            } else {
                pointsPlayerTwo++;
                displayPointsPlayerTwo(pointsPlayerTwo);
            }
        } else {
            addPointsPlayerTwo();
        }
    }

    /**
     * Method to add points for player one.
     * Automatically ends games/sets when needed.
     */
    public void addPointsPlayerOne(){
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
                pointsPlayerOne++;
                displayAdvPlayerOne("Adv");
                displayAdvPlayerTwo("-");
                break;
            case 4:
                pointsPlayerOne++;
                displayAdvPlayerOne("-");
                displayAdvPlayerTwo("-");
                break;
        }

        int checkSet = checkSetOver(gamesPlayerOne, gamesPlayerTwo);
        switch (checkSet) {
            //     player 1 wins set.
            case 1:
                setsPlayerOne++;
                gamesPlayerOne = 0;
                gamesPlayerTwo = 0;
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                displayAll();
                break;
            //set just continues, not yet ending
            case 2:
                return;
            //tiebreak time
            case 3:
                tiebreak = true;
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                gamesPlayerOne = 6;
                gamesPlayerTwo = 6;
                displayAll();
        }
    }

    /**
     * Method to add points for player two.
     * Automatically ends games/sets when needed.
     */
    public void addPointsPlayerTwo(){
        int checkGame = checkGameOver(pointsPlayerTwo, pointsPlayerOne);
        switch (checkGame){
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
                pointsPlayerTwo++;
                displayAdvPlayerTwo("Adv");
                displayAdvPlayerOne("-");
                break;
            case 4:
                pointsPlayerTwo++;
                displayAdvPlayerOne("-");
                displayAdvPlayerTwo("-");
                break;
        }

        int checkSet = checkSetOver(gamesPlayerTwo, gamesPlayerOne);
        switch (checkSet) {
            //     player 2 wins set.
            case 1:
                setsPlayerTwo++;
                gamesPlayerOne = 0;
                gamesPlayerTwo = 0;
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                displayAll();
                break;
            //set just continues, not yet ending
            case 2:
                return;
            //tiebreak time
            case 3:
                tiebreak = true;
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                gamesPlayerOne = 6;
                gamesPlayerTwo = 6;
                displayAll();
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
            } else if (pointsA == pointsB) {
                return 3;
            }
        }
        if(pointsA == 40){
            if(pointsB < 40){
                return 1;
            } else if (pointsB ==40) {
                return 3;
            } else if (pointsB > 40) {
                return 4;
            }
        }
        return 2;
    }

    /**
     * Verifies if the current set is over in order to increase the sets score
     * return 1 if set is over, player A wins set.
     * return 2 if set just continues, not yet ending
     * return 3 if tiebreak should start
     */
    public int checkSetOver (int gamesA, int gamesB) {
        if(gamesA >= 6 & ((gamesA - gamesB) >= 2)){
            return 1;
        } else if (gamesA == 6 & gamesB == 6){
            return 3;
        }
        return 2;
    }

    /**
     * Return true if tie-break is won by player A
     * Return falso if tie-break continues
     */
    public boolean checkTieOver(int pointsA, int pointsB){
        if ((pointsA+1 >= 7) & ((pointsA+1 - pointsB) >=2)){
            return true;
        } else {
            return false;
        }
    }
}