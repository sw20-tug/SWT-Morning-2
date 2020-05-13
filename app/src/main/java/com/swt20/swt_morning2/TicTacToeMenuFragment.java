package com.swt20.swt_morning2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.HashMap;
import java.util.Map;

public class TicTacToeMenuFragment extends Fragment {

    private Map<Integer, Integer> posToRidFirstPlayer = new HashMap<>();
    private Map<Integer, Integer> posToRidSecondPlayer = new HashMap<>();
    private Map<Integer, Integer> ridToPosFirstPlayer = new HashMap<>();
    private Map<Integer, Integer> ridToPosSecondPlayer = new HashMap<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // map index of dropdown to resource id in both directions
        posToRidFirstPlayer.put(0, R.drawable.x_0000ff);
        posToRidFirstPlayer.put(1, R.drawable.x_009933);
        posToRidFirstPlayer.put(2, R.drawable.x_cc00cc);
        posToRidFirstPlayer.put(3, R.drawable.x_ff0000);
        posToRidSecondPlayer.put(0, R.drawable.o_0000ff);
        posToRidSecondPlayer.put(1, R.drawable.o_009933);
        posToRidSecondPlayer.put(2, R.drawable.o_cc00cc);
        posToRidSecondPlayer.put(3, R.drawable.o_ff0000);
        for (int i = 0; i < 4; i++) {
            ridToPosFirstPlayer.put(posToRidFirstPlayer.get(i), i);
            ridToPosSecondPlayer.put(posToRidSecondPlayer.get(i), i);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tictactoe_menu, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.ttt_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TicTacToeMenuFragment.this)
                        .navigate(R.id.action_Menu_to_Game);
            }
        });

        Spinner spinner1 = (Spinner) view.findViewById(R.id.ttt_spinner_color_p1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getContext(),
                R.array.ttt_colors, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                storeColorSelection(TicTacToeGameFragment.DRAWABLE_FIRST_PLAYER,
                        posToRidFirstPlayer.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinner2 = (Spinner) view.findViewById(R.id.ttt_spinner_color_p2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.getContext(),
                R.array.ttt_colors, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                storeColorSelection(TicTacToeGameFragment.DRAWABLE_SECOND_PLAYER,
                        posToRidSecondPlayer.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // set values from shared pref
        SharedPreferences options = this.getContext().getApplicationContext()
                .getSharedPreferences("TicTacToe_Options", 0);
        int resX = options.getInt(TicTacToeGameFragment.DRAWABLE_FIRST_PLAYER,
                TicTacToeGameFragment.DEFAULT_DRAWABLE_FIRST_PLAYER);
        int resO = options.getInt(TicTacToeGameFragment.DRAWABLE_SECOND_PLAYER,
                TicTacToeGameFragment.DEFAULT_DRAWABLE_SECOND_PLAYER);
        int posX = ridToPosFirstPlayer.get(resX);
        int posO = ridToPosSecondPlayer.get(resO);
        spinner1.setSelection(posX);
        spinner2.setSelection(posO);
    }

    private void storeColorSelection(String player, int rid) {
        SharedPreferences options = this.getContext().getApplicationContext()
                .getSharedPreferences("TicTacToe_Options", 0);
        SharedPreferences.Editor editor = options.edit();
        editor.putInt(player, rid);
        editor.apply();
    }
}
