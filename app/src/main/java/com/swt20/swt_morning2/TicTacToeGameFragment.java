package com.swt20.swt_morning2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.HashMap;
import java.util.Map;

public class TicTacToeGameFragment extends Fragment {

    int turn = 0;
    Map<Integer, Boolean> fieldSet = new HashMap<>();



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tictactoe_game, container, false);
    }

    /**
     * A long method that needs a comment :D TODO for the team who wrote this :P.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((ImageView) view.findViewById(R.id.imageView)).setImageResource(R.drawable.empty);
        ((ImageView) view.findViewById(R.id.imageView2)).setImageResource(R.drawable.empty);
        ((ImageView) view.findViewById(R.id.imageView3)).setImageResource(R.drawable.empty);
        ((ImageView) view.findViewById(R.id.imageView4)).setImageResource(R.drawable.empty);
        ((ImageView) view.findViewById(R.id.imageView5)).setImageResource(R.drawable.empty);
        ((ImageView) view.findViewById(R.id.imageView6)).setImageResource(R.drawable.empty);
        ((ImageView) view.findViewById(R.id.imageView7)).setImageResource(R.drawable.empty);
        ((ImageView) view.findViewById(R.id.imageView8)).setImageResource(R.drawable.empty);
        ((ImageView) view.findViewById(R.id.imageView9)).setImageResource(R.drawable.empty);

        view.findViewById(R.id.imageView9).setOnClickListener(new View.OnClickListener() {
            int res = R.id.imageView9;
            @Override
            public void onClick(View view) {
                if (fieldSet.containsKey(res)) {
                    return;
                }
                fieldSet.put(res, true);
                turn++;
                ImageView img = (ImageView) view.findViewById(res);
                if (turn % 2 == 0) {
                    img.setImageResource(R.drawable.o);
                } else {
                    img.setImageResource(R.drawable.x);
                }
            }
        });

        view.findViewById(R.id.imageView8).setOnClickListener(new View.OnClickListener() {
            int res = R.id.imageView8;
            @Override
            public void onClick(View view) {
                if (fieldSet.containsKey(res)) {
                    return;
                }
                fieldSet.put(res, true);
                turn++;
                ImageView img = (ImageView) view.findViewById(res);
                if (turn % 2 == 0) {
                    img.setImageResource(R.drawable.o);
                } else {
                    img.setImageResource(R.drawable.x);
                }
            }
        });

        view.findViewById(R.id.imageView7).setOnClickListener(new View.OnClickListener() {
            int res = R.id.imageView7;
            @Override
            public void onClick(View view) {
                if (fieldSet.containsKey(res)) {
                    return;
                }
                fieldSet.put(res, true);
                turn++;
                ImageView img = (ImageView) view.findViewById(res);
                if (turn % 2 == 0) {
                    img.setImageResource(R.drawable.o);
                } else {
                    img.setImageResource(R.drawable.x);
                }
            }
        });

        view.findViewById(R.id.imageView6).setOnClickListener(new View.OnClickListener() {
            int res = R.id.imageView6;
            @Override
            public void onClick(View view) {
                if (fieldSet.containsKey(res)) {
                    return;
                }
                fieldSet.put(res, true);
                turn++;
                ImageView img = (ImageView) view.findViewById(res);
                if (turn % 2 == 0) {
                    img.setImageResource(R.drawable.o);
                } else {
                    img.setImageResource(R.drawable.x);
                }
            }
        });

        view.findViewById(R.id.imageView5).setOnClickListener(new View.OnClickListener() {
            int res = R.id.imageView5;
            @Override
            public void onClick(View view) {
                if (fieldSet.containsKey(res)) {
                    return;
                }
                fieldSet.put(res, true);
                turn++;
                ImageView img = (ImageView) view.findViewById(res);
                if (turn % 2 == 0) {
                    img.setImageResource(R.drawable.o);
                } else {
                    img.setImageResource(R.drawable.x);
                }
            }
        });

        view.findViewById(R.id.imageView4).setOnClickListener(new View.OnClickListener() {
            int res = R.id.imageView4;
            @Override
            public void onClick(View view) {
                if (fieldSet.containsKey(res)) {
                    return;
                }
                fieldSet.put(res, true);
                turn++;
                ImageView img = (ImageView) view.findViewById(res);
                if (turn % 2 == 0) {
                    img.setImageResource(R.drawable.o);
                } else {
                    img.setImageResource(R.drawable.x);
                }
            }
        });

        view.findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
            int res = R.id.imageView3;
            @Override
            public void onClick(View view) {
                if (fieldSet.containsKey(res)) {
                    return;
                }
                fieldSet.put(res, true);
                turn++;
                ImageView img = (ImageView) view.findViewById(res);
                if (turn % 2 == 0) {
                    img.setImageResource(R.drawable.o);
                } else {
                    img.setImageResource(R.drawable.x);
                }
            }
        });

        view.findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
            int res = R.id.imageView2;
            @Override
            public void onClick(View view) {
                if (fieldSet.containsKey(res)) {
                    return;
                }
                fieldSet.put(res, true);
                turn++;
                ImageView img = (ImageView) view.findViewById(res);
                if (turn % 2 == 0) {
                    img.setImageResource(R.drawable.o);
                } else {
                    img.setImageResource(R.drawable.x);
                }
            }
        });

        view.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            int res = R.id.imageView;
            @Override
            public void onClick(View view) {
                if (fieldSet.containsKey(res)) {
                    return;
                }
                fieldSet.put(res, true);
                turn++;
                ImageView img = (ImageView) view.findViewById(res);
                if (turn % 2 == 0) {
                    img.setImageResource(R.drawable.o);
                } else {
                    img.setImageResource(R.drawable.x);
                }
            }
        });
    }
}
