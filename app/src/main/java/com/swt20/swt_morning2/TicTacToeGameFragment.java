package com.swt20.swt_morning2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class TicTacToeGameFragment extends Fragment {


    static final String DRAWABLE_FIRST_PLAYER = "DRAWABLE_FIRST_PLAYER";
    static final String DRAWABLE_SECOND_PLAYER = "DRAWABLE_SECOND_PLAYER";

    private TicTacToeGameLogic logic;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.tictactoe_game, container, false);
        SharedPreferences options = view.getContext().getApplicationContext()
                .getSharedPreferences("TicTacToe_Options", 0);
        int drawableFirstPlayer = options.getInt(DRAWABLE_FIRST_PLAYER, R.drawable.x);
        int drawableSecondPlayer = options.getInt(DRAWABLE_SECOND_PLAYER, R.drawable.o);

        logic = new TicTacToeGameLogic(drawableFirstPlayer, drawableSecondPlayer);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tictactoe_game, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TableLayout gameField = ((TableLayout) view.findViewById(R.id.ttt_game_field));
        for (int rowCount = 0; rowCount < gameField.getChildCount(); rowCount++) {
            final TableRow currentRow = (TableRow) gameField.getChildAt(rowCount);
            for (int colCount = 0; colCount < currentRow.getChildCount(); colCount++) {
                final ImageView currentImageView = (ImageView) currentRow.getChildAt(colCount);
                setupCell(colCount, rowCount, currentImageView);
            }
        }
    }

    private void setupCell(final int x, final int y, final ImageView imageView) {
        imageView.setImageResource(R.drawable.empty);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (logic.turn(x, y)) {
                    imageView.setImageResource(logic.getCell(x, y).getOwner().getResId());
                }
            }
        });
    }
}
