package com.swt20.swt_morning2;

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

    private TicTacToeGameLogic logic;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        logic = new TicTacToeGameLogic(R.drawable.x, R.drawable.o);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tictactoe_game, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TableLayout game_field = ((TableLayout) view.findViewById(R.id.ttt_game_field));
        for (int row_count = 0; row_count < game_field.getChildCount(); row_count++) {
            final TableRow current_row = (TableRow) game_field.getChildAt(row_count);
            for (int col_count = 0; col_count < current_row.getChildCount(); col_count++) {
                final ImageView current_image_view = (ImageView) current_row.getChildAt(col_count);
                setupCell(col_count, row_count, current_image_view);
            }
        }
    }

    private void setupCell(final int x, final int y, final ImageView image_view) {
        image_view.setImageResource(R.drawable.empty);
        image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(logic.turn(x, y)) {
                    image_view.setImageResource(logic.getCell(x, y).getOwner().getResId());
                }
            }
        });
    }
}
