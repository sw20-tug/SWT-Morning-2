package com.swt20.swt_morning2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class WhiteTilesFragment extends Fragment {

    private HashMap<Integer, Integer> tilesButtons = new HashMap<>();
    public static List<Integer> tileButtonIds = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tiles_game, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tileButtonIds.add(R.id.tilesFieldButton0);
        tileButtonIds.add(R.id.tilesFieldButton1);
        tileButtonIds.add(R.id.tilesFieldButton2);
        tileButtonIds.add(R.id.tilesFieldButton3);
        tileButtonIds.add(R.id.tilesFieldButton4);
        tileButtonIds.add(R.id.tilesFieldButton5);
        tileButtonIds.add(R.id.tilesFieldButton6);
        tileButtonIds.add(R.id.tilesFieldButton7);
        tileButtonIds.add(R.id.tilesFieldButton8);
        tileButtonIds.add(R.id.tilesFieldButton9);
        tileButtonIds.add(R.id.tilesFieldButton10);
        tileButtonIds.add(R.id.tilesFieldButton11);
        tileButtonIds.add(R.id.tilesFieldButton12);
        tileButtonIds.add(R.id.tilesFieldButton13);
        tileButtonIds.add(R.id.tilesFieldButton14);
        tileButtonIds.add(R.id.tilesFieldButton15);

        scrambleButtons(view);
        tilesButtons.keySet().forEach((buttonId) -> {
            setupClickListener(view, buttonId);
        });

    }

    private void setupClickListener(View view, Integer buttonId) {
        view.findViewById(buttonId).setOnClickListener(view1 -> {
            Integer integer = tilesButtons.get(buttonId);
            view.findViewById(buttonId);
            if (integer != null && integer.equals(Color.WHITE)) {
                whiteButtonClicked();
            } else {
                blackButtonClicked(view);
            }
        });
    }

    private void whiteButtonClicked() {
        String text = getResources().getString(R.string.you_lose);;
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        NavHostFragment.findNavController(WhiteTilesFragment.this)
                .navigate(R.id.action_TilesGameFragment_to_TilesMenuFragment);
    }

    private void blackButtonClicked(View view) {
        ScoreTracker tracker = new ScoreTracker(view.getContext());
        tracker.addScore(Game.TILES, 1);
        scrambleButtons(view);
    }

    private void scrambleButtons(View view) {
        Random r = new Random();
        tileButtonIds.forEach(integer -> {
            Integer color = r.nextInt(2) == 1 ? Color.WHITE : Color.BLACK;
            tilesButtons.put(integer, color);
        });
        boolean validField = tilesButtons.containsValue(Color.BLACK);
        if (!validField) {
            List<Integer> buttonsAsList = new ArrayList<>(tilesButtons.keySet());
            Integer randomBlackButtonId = buttonsAsList.get(r.nextInt(buttonsAsList.size()));
            tilesButtons.put(randomBlackButtonId, Color.BLACK);
        }
        colorButtons(view);
    }

    private void colorButtons(View view) {
        tilesButtons.forEach((buttonId, color) -> {
            view.findViewById(buttonId).setBackgroundColor(color);
        });
    }


}
