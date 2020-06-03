package com.swt20.swt_morning2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class TicTacToeGameFragment extends Fragment {


    static final String DRAWABLE_FIRST_PLAYER = "DRAWABLE_FIRST_PLAYER";
    static final String DRAWABLE_SECOND_PLAYER = "DRAWABLE_SECOND_PLAYER";
    static final String AUTOPLAYER = "AUTOPLAYER";
    static final int DEFAULT_DRAWABLE_FIRST_PLAYER = R.drawable.x_ff0000;
    static final int DEFAULT_DRAWABLE_SECOND_PLAYER = R.drawable.o_0000ff;

    private TicTacToeGameLogic logic;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.tictactoe_game, container, false);
        SharedPreferences options = view.getContext().getApplicationContext()
                .getSharedPreferences("TicTacToe_Options", 0);
        int drawableFirstPlayer = options
                .getInt(DRAWABLE_FIRST_PLAYER, DEFAULT_DRAWABLE_FIRST_PLAYER);
        int drawableSecondPlayer = options
                .getInt(DRAWABLE_SECOND_PLAYER, DEFAULT_DRAWABLE_SECOND_PLAYER);
        boolean autoplayer = options
                .getBoolean(AUTOPLAYER, false);

        logic = new TicTacToeGameLogic(drawableFirstPlayer, drawableSecondPlayer, autoplayer);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tictactoe_game, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final TableLayout gameField = ((TableLayout) view.findViewById(R.id.ttt_game_field));
        for (int rowCount = 0; rowCount < gameField.getChildCount(); rowCount++) {
            final TableRow currentRow = (TableRow) gameField.getChildAt(rowCount);
            for (int colCount = 0; colCount < currentRow.getChildCount(); colCount++) {
                final ImageView currentImageView = (ImageView) currentRow.getChildAt(colCount);
                setupCell(colCount, rowCount, currentImageView, gameField);
            }
        }
    }

    private void setupCell(final int x, final int y, final ImageView imageView,
                           final TableLayout gameField) {
        imageView.setImageResource(R.drawable.empty);
        imageView.setOnClickListener(new View.OnClickListener() {
            private boolean checkWin() {
                TicTacToeGameLogic.Player winner = logic.getWinner();
                if (winner != null) {
                    String text;
                    ScoreTracker tracker = new ScoreTracker(imageView.getContext());
                    if (winner.equals(logic.getFirst())) {
                        text = getResources().getString(R.string.you_win);
                    } else {
                        text = getResources().getString(R.string.you_lose);
                    }
                    logic.changeScore(winner, tracker);
                    Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                    return true;
                }
                return false;
            }

            @Override
            public void onClick(View view) {
                if (logic.turn(x, y)) {

                    // outsource to fkt.
                    imageView.setImageResource(logic.getCell(x, y).getOwner().getResId());
                    if (checkWin()) {
                        return;
                    }
                    logic.autoplayerTurn();
                    for (int y = 0; y < gameField.getChildCount(); y++) {
                        final TableRow currentRow = (TableRow) gameField.getChildAt(y);
                        for (int x = 0; x < currentRow.getChildCount(); x++) {
                            final ImageView currentImageView = (ImageView) currentRow.getChildAt(x);
                            if (logic.getCell(x, y).getOwner() != null) {
                                currentImageView.setImageResource(logic.getCell(x, y).getOwner()
                                        .getResId());
                            }
                        }
                    }
                    checkWin();
                }
            }
        });
    }
}
