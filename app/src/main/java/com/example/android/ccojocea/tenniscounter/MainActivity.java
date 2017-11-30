/*
 * Copyright (c) 2017 Cosmin Cojocea
 *
 * This project was created and submitted by Cosmin Cojocea as part of the Google Challenge Scholarship: Android Basics for Udacity.
 *
 * According to Udacity's Honor code, your submissions must be your own work, hence submitting this project as your own might cause you to earn the suspension of your account.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Except as contained in this notice, the name(s) of the above copyright holders shall not be used in advertising or otherwise to promote the sale, use or other dealings in this Software without prior written authorization.
 *
 *
 *
 *
 */

package com.example.android.ccojocea.tenniscounter;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    /**
     * Variables keeping score for both players: Points in a game, Games in a set and Sets*
     * Undo variables save scores between button presses
     * tiebreak variable for tiebreak determination
     */
    private int setsPlayerOne;
    private int setsPlayerTwo;
    private int gamesPlayerOne;
    private int gamesPlayerTwo;
    private int pointsPlayerOne;
    private int pointsPlayerTwo;
    private int undoSetsPlayerOne;
    private int undoSetsPlayerTwo;
    private int undoGamesPlayerOne;
    private int undoGamesPlayerTwo;
    private int undoPointsPlayerOne;
    private int undoPointsPlayerTwo;
    private boolean tiebreak;
    private boolean undoTiebreak;
    //private boolean resetDuringTie;
    private boolean undoState;
    private boolean resetState;
    private int currentSet;
    private boolean setJustOver;

    //these variables are not saved on orientation change
    private ScoreTrack scoreTrk = new ScoreTrack();
    private ScoreTrack undoScoreTrk = new ScoreTrack();
    private String scoresP1Line;
    private String undoScoresP1Line;
    private String scoresP2Line;
    private String undoScoresP2Line;
    private boolean gameOver;
    private boolean gameOverForUndo;

    /**
     * The one which brings light into darkness!
     * Check screen dimension to determine if orientation change is allowed.
     * https://stackoverflow.com/questions/15052576/prevent-android-orientation-change-on-certain-devices
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getConfiguration().orientation == 1) {
            TableLayout scoresTbl = findViewById(R.id.scores_table_layout);

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, 1));
            scoresTbl.addView(tableRow, 1);

            View lineView = new View(this);
            lineView.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, 1));
            lineView.setBackgroundColor(Color.parseColor("#C6ED2C"));
            tableRow.addView(lineView);

            View lineView2 = new View(this);
            lineView2.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, 1));
            lineView2.setBackgroundColor(Color.parseColor("#C6ED2C"));
            tableRow.addView(lineView2);
        }

        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
                break;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            default:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        Log.i("saveInstanceState", "SAVE Instance called");
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
        outState.putBoolean("undoState", undoState);
        outState.putBoolean("resetState", resetState);
        outState.putInt("currentSet", currentSet);
        outState.putBoolean("setJustOver", setJustOver);

        outState.putString("scoresP1Line", scoresP1Line);
        outState.putString("undoScoresP1Line", undoScoresP1Line);
        outState.putString("scoresP2Line", scoresP2Line);
        outState.putString("undoScoresP2Line", undoScoresP2Line);
        outState.putBoolean("gameOver", gameOver);
        outState.putBoolean("gameOverForUndo", gameOverForUndo);

        outState.putIntegerArrayList("scoreTrk.scoresP1", scoreTrk.scoresP1);
        outState.putIntegerArrayList("scoreTrk.scoresP2", scoreTrk.scoresP2);
        outState.putIntegerArrayList("undoScoreTrk.scoresP1", undoScoreTrk.scoresP1);
        outState.putIntegerArrayList("undoScoreTrk.scoresP2", undoScoreTrk.scoresP2);

        ImageButton buttonA = findViewById(R.id.add_points_player_one);
        boolean buttonsDisabled = false;
        if(buttonA.isEnabled()){
            outState.putBoolean("buttonsDisabled", buttonsDisabled);
        } else {
            buttonsDisabled = true;
            outState.putBoolean("buttonsDisabled", buttonsDisabled);
        }

        TableLayout tblScores = findViewById(R.id.scores_table_layout);
        boolean tblScoresVisible = false;
        if(tblScores.getVisibility() == View.VISIBLE){
            tblScoresVisible = true;
            outState.putBoolean("tblScoresVisible", tblScoresVisible);
        }else{
            outState.putBoolean("tblScoresVisible", tblScoresVisible);
        }

        TextView tvWin = findViewById(R.id.win_message);
        String winText = tvWin.getText().toString();
        outState.putString("winText", winText);

//        int[] arrayScores1 = new int[scoreTrk.scoresP1.size()];
//        int[] arrayScores2 = new int[scoreTrk.scoresP2.size()];
//        for(int i = 0; i < scoreTrk.scoresP1.size(); i++) {
//            if (scoreTrk.scoresP1.get(i) != null) {
//                arrayScores1[i] = scoreTrk.scoresP1.get(i);
//            }
//        }
//        for(int i = 0; i < scoreTrk.scoresP2.size(); i++) {
//            if (scoreTrk.scoresP2.get(i) != null) {
//                arrayScores2[i] = scoreTrk.scoresP2.get(i);
//            }
//        }
//        outState.putIntArray("arrayScores1", arrayScores1);
//        outState.putIntArray("arrayScores2", arrayScores2);
//
//        int[] undoArrayScores1 = new int[undoScoreTrk.scoresP1.size()];
//        int[] undoArrayScores2 = new int[undoScoreTrk.scoresP2.size()];
//        for(int i = 0; i < undoScoreTrk.scoresP1.size(); i++) {
//            if (undoScoreTrk.scoresP1.get(i) != null){
//                undoArrayScores1[i] = undoScoreTrk.scoresP1.get(i);
//            }
//        }
//        for(int i = 0; i < undoScoreTrk.scoresP2.size(); i++) {
//            if (undoScoreTrk.scoresP2.get(i) != null) {
//                undoArrayScores2[i] = undoScoreTrk.scoresP2.get(i);
//            }
//        }
//        outState.putIntArray("undoArrayScores1", undoArrayScores1);
//        outState.putIntArray("undoArrayScores2", undoArrayScores2);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        Log.i("restoreInstanceState", "RESTORE Instance called");
        setsPlayerOne = savedInstanceState.getInt("setsPlayerOne");
        setsPlayerTwo = savedInstanceState.getInt("setsPlayerTwo");
        gamesPlayerOne = savedInstanceState.getInt("gamesPlayerOne");
        gamesPlayerTwo = savedInstanceState.getInt("gamesPlayerTwo");
        pointsPlayerOne = savedInstanceState.getInt("pointsPlayerOne");
        pointsPlayerTwo = savedInstanceState.getInt("pointsPlayerTwo");
        undoSetsPlayerOne = savedInstanceState.getInt("undoSetsPlayerOne");
        undoSetsPlayerTwo = savedInstanceState.getInt("undoSetsPlayerTwo");
        undoGamesPlayerOne = savedInstanceState.getInt("undoGamesPlayerOne");
        undoGamesPlayerTwo = savedInstanceState.getInt("undoGamesPlayerTwo");
        undoPointsPlayerOne = savedInstanceState.getInt("undoPointsPlayerOne");
        undoPointsPlayerTwo = savedInstanceState.getInt("undoPointsPlayerTwo");
        tiebreak = savedInstanceState.getBoolean("tiebreak");
        undoTiebreak = savedInstanceState.getBoolean("undoTiebreak");
        undoState = savedInstanceState.getBoolean("undoState");
        resetState = savedInstanceState.getBoolean("resetState");
        currentSet = savedInstanceState.getInt("currentSet");
        setJustOver = savedInstanceState.getBoolean("setJustOver");

        scoresP1Line = savedInstanceState.getString("scoresP1Line");
        scoresP2Line = savedInstanceState.getString("scoresP2Line");
        undoScoresP1Line = savedInstanceState.getString("undoScoresP1Line");
        undoScoresP2Line = savedInstanceState.getString("undoScoresP2Line");
        gameOver = savedInstanceState.getBoolean("gameOver");
        gameOverForUndo = savedInstanceState.getBoolean("gameOverForUndo");

        scoreTrk.scoresP1 = savedInstanceState.getIntegerArrayList("scoreTrk.scoresP1");
        scoreTrk.scoresP2 = savedInstanceState.getIntegerArrayList("scoreTrk.scoresP2");
        undoScoreTrk.scoresP1 = savedInstanceState.getIntegerArrayList("undoScoreTrk.scoresP1");
        undoScoreTrk.scoresP2 = savedInstanceState.getIntegerArrayList("undoScoreTrk.scoresP2");

        if(savedInstanceState.getBoolean("buttonsDisabled")){
            ImageButton buttonA = findViewById(R.id.add_points_player_one);
            ImageButton buttonB = findViewById(R.id.add_points_player_two);
            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
        }

        if(savedInstanceState.getBoolean("tblScoresVisible")){
            displayScoreTable(scoresP1Line, scoresP2Line, true);
        }

        if (undoState) {
            Button undoButton = findViewById(R.id.undo_button);
            undoButton.setEnabled(true);
        } else {
            Button undoButton = findViewById(R.id.undo_button);
            undoButton.setEnabled(false);
        }
        if (resetState) {
            Button resetButton = findViewById(R.id.reset_button);
            resetButton.setEnabled(true);
        } else {
            Button resetButton = findViewById(R.id.reset_button);
            resetButton.setEnabled(false);
        }
        displayAll();
        TextView tvWin = findViewById(R.id.win_message);
        tvWin.setText(savedInstanceState.getString("winText"));

        //        int[] arrayScores1 = savedInstanceState.getIntArray("arrayScores1");
//        for (int intValue : arrayScores1){
//            scoreTrk.scoresP1.add(intValue);
//        }
//        int[] arrayScores2 = savedInstanceState.getIntArray("arrayScores2");
//        for (int intValue : arrayScores2){
//            scoreTrk.scoresP2.add(intValue);
//        }
//        int[] undoArrayScores1 = savedInstanceState.getIntArray("undoArrayScores1");
//        for (int intValue : undoArrayScores1){
//            undoScoreTrk.scoresP1.add(intValue);
//        }
//        int[] undoArrayScores2 = savedInstanceState.getIntArray("undoArrayScores2");
//        for (int intValue : undoArrayScores2){
//            undoScoreTrk.scoresP2.add(intValue);
//        }
    }

    /**
     * Method saves end of set games situation for both players to be displayed at the bottom of the screen
     */
    public void saveGames(int currentSet, int gamesPlayerOne, int gamesPlayerTwo) {
        scoreTrk.scoresP1.add(gamesPlayerOne);
        scoreTrk.scoresP2.add(gamesPlayerTwo);
        scoreTrk.currentSet = currentSet;
        scoresP1Line = ScoreTrack.toMyString(scoreTrk.scoresP1);
        scoresP2Line = ScoreTrack.toMyString(scoreTrk.scoresP2);

        displayScoreTable(scoresP1Line, scoresP2Line, true);
    }

    /**
     * Display all scores
     */
    private void displayAll() {
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
    private void setAllZero() {
        setsPlayerOne = 0;
        setsPlayerTwo = 0;
        gamesPlayerOne = 0;
        gamesPlayerTwo = 0;
        pointsPlayerOne = 0;
        pointsPlayerTwo = 0;
        scoresP1Line = "";
        scoresP2Line = "";
        tiebreak = false;
        scoreTrk = new ScoreTrack();
    }

    /**
     * Save the state of current variables for use in undo
     */
    private void undoSave() {
        undoSetsPlayerOne = setsPlayerOne;
        undoSetsPlayerTwo = setsPlayerTwo;
        undoGamesPlayerOne = gamesPlayerOne;
        undoGamesPlayerTwo = gamesPlayerTwo;
        undoPointsPlayerOne = pointsPlayerOne;
        undoPointsPlayerTwo = pointsPlayerTwo;
        undoTiebreak = tiebreak;

        undoScoresP1Line = scoresP1Line;
        undoScoresP2Line = scoresP2Line;
        undoScoreTrk = ScoreTrack.copy(scoreTrk);
    }

    /**
     * Restore the state of variables on undo button press
     */
    private void undoRestore() {
        setsPlayerOne = undoSetsPlayerOne;
        setsPlayerTwo = undoSetsPlayerTwo;
        gamesPlayerOne = undoGamesPlayerOne;
        gamesPlayerTwo = undoGamesPlayerTwo;
        pointsPlayerOne = undoPointsPlayerOne;
        pointsPlayerTwo = undoPointsPlayerTwo;
        tiebreak = undoTiebreak;

        scoresP1Line = undoScoresP1Line;
        scoresP2Line = undoScoresP2Line;
        scoreTrk = ScoreTrack.copy(undoScoreTrk);

        if (gameOverForUndo) {
            ImageButton buttonA = findViewById(R.id.add_points_player_one);
            ImageButton buttonB = findViewById(R.id.add_points_player_two);
            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
            TextView win = findViewById(R.id.win_message);
            if (setsPlayerOne >= 4 | setsPlayerTwo >= 4) {
                if (setsPlayerOne > setsPlayerTwo) {
                    win.setText("Player One Wins!");
                } else {
                    win.setText("Player Two Wins!");
                }
            }
            displayScoreTable(scoresP1Line, scoresP2Line, true);
            gameOver = true;
            gameOverForUndo = false;
        } else if (gameOver) {
            ImageButton buttonA = findViewById(R.id.add_points_player_one);
            ImageButton buttonB = findViewById(R.id.add_points_player_two);
            buttonA.setEnabled(true);
            buttonB.setEnabled(true);
            TextView win = findViewById(R.id.win_message);
            win.setText("");
            displayScoreTable(scoresP1Line, scoresP2Line, true);
            gameOver = false;
        }

        if (!scoreTrk.scoresP1.isEmpty()) {
            displayScoreTable(scoresP1Line, scoresP2Line, true);
        } else {
            displayScoreTable(scoresP1Line, scoresP2Line, false);
        }
    }

    /**
     * Save current variables
     * Reset scores to zero
     */
    public void resetScores(View view) {
        Button undoButton = findViewById(R.id.undo_button);
        undoButton.setEnabled(true);
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setEnabled(false);
        undoState = true;
        resetState = false;
        setJustOver = false;
        //resetDuringTie = tiebreak;
        undoSave();
        setAllZero();
        displayAll();
        displayPoints("Points");

        if (gameOver) {
            gameOver = false;
            gameOverForUndo = true;
        }
        ImageButton buttonA = findViewById(R.id.add_points_player_one);
        ImageButton buttonB = findViewById(R.id.add_points_player_two);
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        TextView win = findViewById(R.id.win_message);
        win.setText("");
        displayScoreTable("", "", false);
    }

    /**
     * Undo previously added points to player one or two.
     * Button gets disabled after using.
     */
    public void undoLastAction(View view) {
        Button undoButton = findViewById(R.id.undo_button);
        undoButton.setEnabled(false);
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setEnabled(true);
        undoState = false;
        resetState = true;

        if (setJustOver) {
            currentSet--;
        }
        undoRestore();
        displayAll();
        if (pointsPlayerOne > 40 | pointsPlayerTwo > 40) {
            if (pointsPlayerOne - pointsPlayerTwo > 0) {
                displayAdvPlayerOne("Adv");
                displayAdvPlayerTwo("-");
            } else if (pointsPlayerOne - pointsPlayerTwo < 0) {
                displayAdvPlayerOne("-");
                displayAdvPlayerTwo("Adv");
            } else {
                displayAdvPlayerOne("-");
                displayAdvPlayerTwo("-");
            }
        }
        if (tiebreak) {
            displayPoints("Tie-break");
        } else if (pointsPlayerOne >= 40 & pointsPlayerTwo >= 40 & pointsPlayerOne == pointsPlayerTwo) {
            displayPoints("Deuce");
        } else if (pointsPlayerOne != pointsPlayerTwo) {
            displayPoints("Points");
        }
    }

    /**
     * Display set score for player one
     */
    private void displaySetPlayerOne(int set) {
        TextView setScoreView = findViewById(R.id.player_one_sets);
        setScoreView.setText(String.valueOf(set));
    }

    /**
     * Display set score for player two
     */
    private void displaySetPlayerTwo(int set) {
        TextView setScoreView = findViewById(R.id.player_two_sets);
        setScoreView.setText(String.valueOf(set));
    }

    /**
     * Display games score for player one
     */
    private void displayGamesPlayerOne(int games) {
        TextView setScoreView = findViewById(R.id.player_one_games);
        setScoreView.setText(String.valueOf(games));
    }

    /**
     * Display games score for player two
     */
    private void displayGamesPlayerTwo(int games) {
        TextView setScoreView = findViewById(R.id.player_two_games);
        setScoreView.setText(String.valueOf(games));
    }

    /**
     * Display points score for player one
     */
    private void displayPointsPlayerOne(int points) {
        TextView setScoreView = findViewById(R.id.player_one_points);
        setScoreView.setText(String.valueOf(points));
    }

    /**
     * Display points score for player two
     */
    private void displayPointsPlayerTwo(int points) {
        TextView setScoreView = findViewById(R.id.player_two_points);
        setScoreView.setText(String.valueOf(points));
    }

    /**
     * Display the String adv for player one after Deuce (can be either Adv or -)
     */
    private void displayAdvPlayerOne(String adv) {
        TextView setScoreView = findViewById(R.id.player_one_points);
        setScoreView.setText(adv);
    }

    /**
     * Display the String adv for player two after Deuce (can be either Adv or -)
     */
    private void displayAdvPlayerTwo(String adv) {
        TextView setScoreView = findViewById(R.id.player_two_points);
        setScoreView.setText(adv);
    }

    /**
     * Method called by button press for Player One.
     * Checks if it's tiebreak or normal game time and adds points as needed.
     * Also modifies the state of the Undo button to true in case it was previously used.
     * Goes into the method to add points if it's not tiebreak
     */
    public void addPointsPlOne(View view) {
        Button undoButton = findViewById(R.id.undo_button);
        undoButton.setEnabled(true);
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setEnabled(true);
        undoState = true;
        resetState = true;
        gameOverForUndo = false;

        undoSave();

        if (tiebreak) {
            boolean check = checkTieOver(pointsPlayerOne, pointsPlayerTwo);
            if (check) {
                //Player one wins set after tiebreak
                tiebreak = false;
                gamesPlayerOne++;
                saveGames(currentSet, gamesPlayerOne, gamesPlayerTwo);
                setsPlayerOne++;
                currentSet++;
                setJustOver = true;
                gamesPlayerOne = 0;
                gamesPlayerTwo = 0;
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                displayAll();
                displayPoints("Points");
                checkGameOver(setsPlayerOne, "Player One");
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
    public void addPointsPlTwo(View view) {
        Button undoButton = findViewById(R.id.undo_button);
        undoButton.setEnabled(true);
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setEnabled(true);
        undoState = true;
        resetState = true;
        gameOverForUndo = false;

        undoSave();

        if (tiebreak) {
            boolean check = checkTieOver(pointsPlayerTwo, pointsPlayerOne);
            if (check) {
                //Player Two wins set after tiebreak
                tiebreak = false;
                gamesPlayerTwo++;
                saveGames(currentSet, gamesPlayerOne, gamesPlayerTwo);
                setsPlayerTwo++;
                currentSet++;
                setJustOver = true;
                gamesPlayerOne = 0;
                gamesPlayerTwo = 0;
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                displayAll();
                displayPoints("Points");
                checkGameOver(setsPlayerTwo, "Player Two");
            } else {
                pointsPlayerTwo++;
                displayPointsPlayerTwo(pointsPlayerTwo);
            }
        } else {
            addPointsPlayerTwo();
        }
    }

    /**
     * Modify the Points text view in case of Deuce
     */
    private void displayPoints(String points) {
        TextView pointsView = findViewById(R.id.points);
        pointsView.setText(points);

        int height;

        if (tiebreak) {
            height = pointsView.getHeight();
            pointsView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.font_size_medium));
            pointsView.setHeight(height);
        } else {
            pointsView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.font_size_large));
        }
    }

    /**
     * Display method concerning Scores Table
     */
    private void displayScoreTable(String line1, String line2, boolean display) {
        TextView scoresP1 = findViewById(R.id.player_one_score_line);
        TextView scoresP2 = findViewById(R.id.player_two_score_line);
        scoresP1.setText(line1);
        scoresP2.setText(line2);
        TableLayout tblScores = findViewById(R.id.scores_table_layout);
        if (display) {
            tblScores.setVisibility(View.VISIBLE);
        } else {
            tblScores.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Method to add points for player one.
     * Automatically ends games/sets when needed.
     */
    private void addPointsPlayerOne() {
        if (setJustOver) {
            setJustOver = false;
        }
        int check = checkGameOver(pointsPlayerOne, pointsPlayerTwo);
        switch (check) {
            //playerOne wins a game
            case 1:
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                gamesPlayerOne++;
                displayPoints("Points");
                displayGamesPlayerOne(gamesPlayerOne);
                displayPointsPlayerOne(pointsPlayerOne);
                displayPointsPlayerTwo(pointsPlayerTwo);
                break;
            case 2:
                if (pointsPlayerOne == 0) {
                    pointsPlayerOne = 15;
                } else if (pointsPlayerOne == 15) {
                    pointsPlayerOne = 30;
                } else if (pointsPlayerOne == 30) {
                    pointsPlayerOne = 40;
                    if (pointsPlayerOne == pointsPlayerTwo) {
                        displayPoints("Deuce");
                    }
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
                displayPoints("Points");
                break;
            case 4:
                pointsPlayerOne++;
                displayAdvPlayerOne("-");
                displayAdvPlayerTwo("-");
                displayPoints("Deuce");
                break;
        }

        int checkSet = checkSetOver(gamesPlayerOne, gamesPlayerTwo);
        switch (checkSet) {
            //     player 1 wins set.
            case 1:
                saveGames(currentSet, gamesPlayerOne, gamesPlayerTwo);
                setsPlayerOne++;
                currentSet++;
                setJustOver = true;
                gamesPlayerOne = 0;
                gamesPlayerTwo = 0;
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                displayAll();
                checkGameOver(setsPlayerOne, "Player One");
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
                displayPoints("Tie-Break");
        }
    }

    /**
     * Method to add points for player two.
     * Automatically ends games/sets when needed.
     */
    private void addPointsPlayerTwo() {
        if (setJustOver) {
            setJustOver = false;
        }
        int checkGame = checkGameOver(pointsPlayerTwo, pointsPlayerOne);
        switch (checkGame) {
            //playerTwo wins a game
            case 1:
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                gamesPlayerTwo++;
                displayPoints("Points");
                displayGamesPlayerTwo(gamesPlayerTwo);
                displayPointsPlayerOne(pointsPlayerOne);
                displayPointsPlayerTwo(pointsPlayerTwo);
                break;
            case 2:
                if (pointsPlayerTwo == 0) {
                    pointsPlayerTwo = 15;
                } else if (pointsPlayerTwo == 15) {
                    pointsPlayerTwo = 30;
                } else if (pointsPlayerTwo == 30) {
                    pointsPlayerTwo = 40;
                    if (pointsPlayerOne == pointsPlayerTwo) {
                        displayPoints("Deuce");
                    }
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
                displayPoints("Points");
                break;
            case 4:
                pointsPlayerTwo++;
                displayPoints("Deuce");
                displayAdvPlayerOne("-");
                displayAdvPlayerTwo("-");
                break;
        }

        int checkSet = checkSetOver(gamesPlayerTwo, gamesPlayerOne);
        switch (checkSet) {
            //     player 2 wins set.
            case 1:
                saveGames(currentSet, gamesPlayerOne, gamesPlayerTwo);
                setsPlayerTwo++;
                currentSet++;
                setJustOver = true;
                gamesPlayerOne = 0;
                gamesPlayerTwo = 0;
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                displayAll();
                checkGameOver(setsPlayerTwo, "Player Two");
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
                displayPoints("Tie-Break");
        }

    }

    /**
     * Verifies if the current game is over in order to increase the games score
     * returns 1 if game ends
     * returns 2 if game continues as normal
     * returns 3 if score is 40-40 so it should continue on advantages
     * returns 4 if score before pushing button was Advantage for player B
     */
    private int checkGameOver(int pointsA, int pointsB) {
        if (pointsA > 40) {
            if (pointsA > pointsB) {
                return 1;
            } else if (pointsA < pointsB) {
                return 4;
            } else if (pointsA == pointsB) {
                return 3;
            }
        }
        if (pointsA == 40) {
            if (pointsB < 40) {
                return 1;
            } else if (pointsB == 40) {
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
    private int checkSetOver(int gamesA, int gamesB) {
        if (gamesA >= 6 & ((gamesA - gamesB) >= 2)) {
            return 1;
        } else if (gamesA == 6 & gamesB == 6) {
            return 3;
        }
        return 2;
    }

    /**
     * Return true if tie-break is won by player A
     * Return false if tie-break continues
     */
    private boolean checkTieOver(int pointsA, int pointsB) {
        return (pointsA + 1 >= 7) & ((pointsA + 1 - pointsB) >= 2);
    }

    /**
     * Method to check if a player reached 4 sets, in which case the game ends (4 out of 7)
     */
    private void checkGameOver(int set, String player) {
        if (set == 4) {
            ImageButton buttonA = findViewById(R.id.add_points_player_one);
            ImageButton buttonB = findViewById(R.id.add_points_player_two);
            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
            TextView win = findViewById(R.id.win_message);
            win.setText(player + " wins!");
            gameOver = true;
        }
    }
}
