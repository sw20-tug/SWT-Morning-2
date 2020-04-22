package com.swt20.swt_morning2;

import android.content.res.Resources;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class MainMenuFragment extends Fragment{

    private static final String TAG = "MainMenuFragment";
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        ScoreTracker score = new ScoreTracker(view.getContext());

        String text = getResources().getString(R.string.game_score) + " " +  score.getScore(Game.TICTACTOE);
        ((TextView)view.findViewById(R.id.ticTacToeScore)).setText(text);

        text = getResources().getString(R.string.game_score) + " " +  score.getScore(Game.HANGMAN);
        ((TextView)view.findViewById(R.id.hangmanScore)).setText(text);

        text = getResources().getString(R.string.game_score) + " " +  score.getScore(Game.TILES);
        ((TextView)view.findViewById(R.id.whiteTilesScore)).setText(text);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.ticTacToeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "tic tac toe button pressed");
                NavHostFragment.findNavController(com.swt20.swt_morning2.MainMenuFragment.this)
                        .navigate(R.id.action_mainMenuFragment_to_TicTacToeMenu);
            }
        });

        view.findViewById(R.id.hangmanButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "hangman button pressed");
                // todo start appropriate game later
                //NavHostFragment.findNavController(com.swt20.swt_morning2.MainMenuFragment.this)
                //        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.whiteTilesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "white tiles button pressed");
                // todo start appropriate game later
                //NavHostFragment.findNavController(com.swt20.swt_morning2.MainMenuFragment.this)
                //        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}

