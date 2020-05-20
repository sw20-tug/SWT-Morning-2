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


public class WhiteTilesFragment extends Fragment {

    private WhiteTilesGameLogic logic;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tiles_game, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        logic = new WhiteTilesGameLogic();
        super.onViewCreated(view, savedInstanceState);

        logic.scrambleButtons();
        colorButtons(view);
        logic.getTilesButtons().keySet().forEach((buttonId) -> {
            setupClickListener(view, buttonId);
        });

    }

    private void setupClickListener(View view, Integer buttonId) {
        view.findViewById(buttonId).setOnClickListener(view1 -> {
            Integer integer = logic.getTilesButtons().get(buttonId);
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
        getActivity().onBackPressed();
    }

    private void blackButtonClicked(View view) {
        ScoreTracker tracker = new ScoreTracker(view.getContext());
        tracker.addScore(Game.TILES, 1);
        logic.scrambleButtons();
        colorButtons(view);
    }

    private void colorButtons(View view) {
        logic.getTilesButtons().forEach((buttonId, color) -> {
            view.findViewById(buttonId).setBackgroundColor(color);
        });
    }


}
