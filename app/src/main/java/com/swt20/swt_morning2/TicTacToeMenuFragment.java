package com.swt20.swt_morning2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static java.lang.Math.ceil;
import static java.lang.Math.getExponent;

public class TicTacToeMenuFragment extends Fragment {

    private static class ColorChoice {

        private RelativeLayout view;
        private int resId;
        private int invertedResId;

        private ColorChoice(RelativeLayout view, int resId) {
            this.view = view;
            this.resId = resId;
        }

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tictactoe_menu, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Switch autoplayer = view.findViewById(R.id.switch1);

        view.findViewById(R.id.ttt_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences options = getActivity().getApplicationContext()
                        .getSharedPreferences("TicTacToe_Options", 0);
                SharedPreferences.Editor editor = options.edit();
                editor.putBoolean(TicTacToeGameFragment.AUTOPLAYER, autoplayer.isChecked());
                editor.apply();
                NavHostFragment.findNavController(TicTacToeMenuFragment.this)
                        .navigate(R.id.action_Menu_to_Game);
            }
        });

        // create color selection in GUI
        TableLayout tableColorChoiceP1 = view.findViewById(R.id.ttt_color_choice_p1);

        ArrayList<ColorChoice> choicesP1 = createColorChoicesFromDrawables("^x_[0-9a-fA-F]{6}$",
                TicTacToeGameFragment.DRAWABLE_FIRST_PLAYER);
        ArrayList<ColorChoice> choicesP2 = createColorChoicesFromDrawables("^o_[0-9a-fA-F]{6}$",
                TicTacToeGameFragment.DRAWABLE_SECOND_PLAYER);

        for (int i = 0; i < choicesP1.size(); i++) {
            choicesP1.get(i).invertedResId = choicesP2.get((i + 1) % choicesP2.size()).resId;
            choicesP2.get((i + 1) % choicesP2.size()).invertedResId = choicesP1.get(i).resId;
        }

        ArrayList<ColorChoice> allChoices = choicesP1;
        allChoices.addAll(choicesP2);

        fillTableWithChoices(tableColorChoiceP1, allChoices);

        setupSelectionLogic(allChoices);

        loadColorSelection(allChoices);

    }

    private void loadColorSelection(ArrayList<ColorChoice> chP1) {

        // load from shared preferences
        SharedPreferences options = this.getContext().getApplicationContext()
                .getSharedPreferences("TicTacToe_Options", 0);
        int resP1 = options.getInt(TicTacToeGameFragment.DRAWABLE_FIRST_PLAYER,
                TicTacToeGameFragment.DEFAULT_DRAWABLE_FIRST_PLAYER);

        // select values in the GUI
        for (ColorChoice choice : chP1) {
            if (choice.resId == resP1) {
                choice.view.getChildAt(0).callOnClick();
                break;
            }
        }
    }

    private void storeColorSelection(String player, int rid) {
        SharedPreferences options = this.getContext().getApplicationContext()
                .getSharedPreferences("TicTacToe_Options", 0);
        SharedPreferences.Editor editor = options.edit();
        editor.putInt(player, rid);
        editor.apply();
    }


    private void setupSelectionLogic(final ArrayList<ColorChoice> colorChoices) {
        for (final ColorChoice choice : colorChoices) {
            choice.view.getChildAt(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (ColorChoice otherChoice : colorChoices) {
                        otherChoice.view.getChildAt(0).setBackgroundColor(0x008bc34a);
                    }
                    choice.view.getChildAt(0).setBackgroundColor(0xff8bc34a);
                    storeColorSelection(TicTacToeGameFragment.DRAWABLE_FIRST_PLAYER, choice.resId);
                    storeColorSelection(TicTacToeGameFragment.DRAWABLE_SECOND_PLAYER,
                            choice.invertedResId);
                }
            });
        }
    }

    private void fillTableWithChoices(TableLayout table, ArrayList<ColorChoice> colorChoices) {
        // create table rows
        for (int rowIdx = 0; rowIdx < (int) ceil(colorChoices.size() / 2.); rowIdx++) {
            TableRow row = new TableRow(table.getContext());
            table.addView(row);
        }

        // fill with choices
        for (int choiceIdx = 0; choiceIdx < colorChoices.size(); choiceIdx++) {
            ((TableRow) table.getChildAt(choiceIdx / 2)).addView(colorChoices.get(choiceIdx).view);
        }
    }

    private ColorChoice createColorChoice(String player, int rid) {
        RelativeLayout imgLayout = new RelativeLayout(this.getContext());
        ImageView imgView = new ImageView(this.getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        imgLayout.addView(imgView, layoutParams);

        imgView.setScaleType(ImageView.ScaleType.CENTER);
        imgView.setImageResource(rid);
        imgView.setPadding(dpToPixels(10), dpToPixels(10), dpToPixels(10), dpToPixels(10));
        imgView.setBackgroundColor(0x008bc34a);

        return new ColorChoice(imgLayout, rid);
    }

    private ArrayList<ColorChoice> createColorChoicesFromDrawables(String regex, String player) {
        ArrayList<ColorChoice> choices = new ArrayList<>();

        // reflection to iterate over our drawables
        Field[] fields = (R.drawable.class).getDeclaredFields();
        for (Field field : fields) {  // one field is one drawable
            if (field.getName().matches(regex)) {
                int resId;
                try {
                    resId = field.getInt(fields);
                } catch (Exception e) {
                    continue;
                }
                choices.add(createColorChoice(player, resId));
            }
        }

        return choices;
    }

    private int dpToPixels(int dp) {
        // src: https://stackoverflow.com/questions/9685658/add-padding-on-view-programmatically
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
