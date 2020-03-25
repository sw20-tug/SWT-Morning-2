package com.swt20.swt_morning2;

import android.content.Context;
import android.content.SharedPreferences;

enum Game {
    HANGMAN,
    TICTACTOE,
    TILES
}

public class ScoreTracker {
    private static final String HANGMAN_SCORE = "HANGMAN_SCORE";
    private static final String TICTACTOE_SCORE = "TICTACTOE_SCORE";
    private static final String TILES_SCORE = "TILES_SCORE";
    private int ticTacToeScore;
    private int hangmanScore;
    private int tilesScore;
    private SharedPreferences pref;

    public ScoreTracker(Context context) {
    }


    private void storeScores() {

    }


    public int getScore(Game game) {
        return 99;
    }

    public void addScore(Game game, int score) {
    }

    public void reduceScore(Game game, int score) {
        addScore(game, -score);
    }

    public void setScore(Game game, int score) {

    }
}
