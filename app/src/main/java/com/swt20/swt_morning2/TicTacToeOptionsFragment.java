package com.swt20.swt_morning2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class TicTacToeOptionsFragment extends Fragment {

    private SharedPreferences options;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.tictactoe_options, container, false);
        this.options = view.getContext().getApplicationContext()
                .getSharedPreferences("TicTacToe_Options", 0);

        int drawableFirstPlayer = options.getInt(TicTacToeGameFragment.DRAWABLE_FIRST_PLAYER,
                R.drawable.x);
        int drawableSecondPlayer = options.getInt(TicTacToeGameFragment.DRAWABLE_SECOND_PLAYER,
                R.drawable.o);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tictactoe_options, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
