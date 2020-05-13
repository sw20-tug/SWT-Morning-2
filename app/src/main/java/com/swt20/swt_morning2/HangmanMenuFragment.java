package com.swt20.swt_morning2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;

import java.util.Locale;

public class HangmanMenuFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private HangmanGameFragment.WordList wordList;

    private EditText field;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        String lang = Locale.getDefault().getDisplayLanguage();
        if(lang.contains("de"))
        {
            sharedPreferences = requireContext().getSharedPreferences("HANGMAN_WORDS_DE", 0);
        } else {
            sharedPreferences = requireContext().getSharedPreferences("HANGMAN_WORDS_EN", 0);
        }
        String wordsJson = sharedPreferences.getString("WORDS",getString(R.string.hangman_default_words));
        Gson gson = new Gson();
        wordList = gson.fromJson(wordsJson, HangmanGameFragment.WordList.class);

        return inflater.inflate(R.layout.hangman_menu, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.ttt_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HangmanMenuFragment.this)
                        .navigate(R.id.action_Menu_to_Game);
            }
        });

        field = view.findViewById(R.id.hangmanAddWordTextInput);

        view.findViewById(R.id.hangmanAddWordButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordList.addWord(field.getText().toString())){
                    Toast.makeText(getContext(), getString(R.string.hangman_toast_add_successful), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), getString(R.string.hangman_toast_add_failed), Toast.LENGTH_LONG).show();
                }
                field.setText("");

            }
        });

    }
}
