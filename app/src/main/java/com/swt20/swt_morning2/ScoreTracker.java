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
        ticTacToeScore = hangmanScore = tilesScore = 0;
        setSharedPreferences(context.getSharedPreferences("SCOREBOARD", 0));
    }


    public void setSharedPreferences(SharedPreferences pref) {
        this.pref = pref;
        hangmanScore = pref.getInt(HANGMAN_SCORE, 0);
        ticTacToeScore = pref.getInt(TICTACTOE_SCORE, 0);
        tilesScore = pref.getInt(TILES_SCORE, 0);
    }

    private void storeScores() {
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putInt(HANGMAN_SCORE, hangmanScore);
        editor.putInt(TICTACTOE_SCORE, ticTacToeScore);
        editor.putInt(TILES_SCORE, tilesScore);
        editor.apply();
    }

    private void validatePreferences() {
        if (pref == null) {
            throw new RuntimeException("SharedPreferences not set! use .setSharedPreferences(...)");
        }
    }


    public int getScore(Game game) {
        validatePreferences();
        switch (game) {
            case HANGMAN:
                return hangmanScore;
            case TILES:
                return tilesScore;
            case TICTACTOE:
                return ticTacToeScore;
            default:
                return 0;
        }
    }

    public void addScore(Game game, int score) {
        validatePreferences();
        switch (game) {
            case HANGMAN:
                hangmanScore += score;
                break;
            case TILES:
                tilesScore += score;
                break;
            case TICTACTOE:
                ticTacToeScore += score;
                break;
            default:
                return;
        }
        storeScores();
    }

    public void reduceScore(Game game, int score) {
        addScore(game, -score);
    }

    public void setScore(Game game, int score) {
        validatePreferences();
        switch (game) {
            case HANGMAN:
                hangmanScore = score;
                break;
            case TILES:
                tilesScore = score;
                break;
            case TICTACTOE:
                ticTacToeScore = score;
                break;
        }
        storeScores();
    }
}
