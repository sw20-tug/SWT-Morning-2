package com.swt20.swt_morning2;

import android.app.Application;
import android.app.ApplicationErrorReport;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static androidx.core.content.ContextCompat.getSystemService;

public class HangmanGameFragment extends Fragment {
    private static final String TAG = "[HangmanGameFragment]";
    private InputMethodManager imgr;
    private TextView nextChar;
    private TextView textView;
    private String word2guess;
    private String word2guessViewtext = "";
    private TextView textViewWord2Guess;
    private WordList wordList;
    private Integer tryCounter;
    private List<Character> tryedCharacters;
    private ImageView feedbackView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        String lang = Locale.getDefault().getDisplayLanguage();
        SharedPreferences sharedPreferences =
                requireContext().getSharedPreferences("HANGMAN_WORDS_EN", 0);
        if (lang.contains("de")) {
            sharedPreferences =
                    requireContext().getSharedPreferences("HANGMAN_WORDS_DE", 0);
        } else {
            sharedPreferences =
                    requireContext().getSharedPreferences("HANGMAN_WORDS_EN", 0);
        }
        String wordsJson;
        wordsJson = sharedPreferences.getString("WORDS", getString(R.string.hangman_default_words));
        Gson gson = new Gson();
        wordList = gson.fromJson(wordsJson, WordList.class);

        return inflater.inflate(R.layout.hangman_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nextChar = view.findViewById(R.id.plainText_nextChar);
        textView = view.findViewById(R.id.textView);
        imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        textViewWord2Guess = view.findViewById(R.id.textView_word2guess);
        tryCounter = 0;
        tryedCharacters = new ArrayList<Character>();
        feedbackView = view.findViewById(R.id.feedbackView);
        ;
        //TODO TCs

        word2guess = wordList.getRandomWord().toUpperCase(Locale.getDefault());
        for (int i = 0; i < word2guess.length(); i++) {
            word2guessViewtext += "_ ";
        }
        textViewWord2Guess.setText(word2guessViewtext);

        view.findViewById(R.id.button_playagain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                word2guess = wordList.getRandomWord().toUpperCase(Locale.getDefault());
                word2guessViewtext = "";
                for (int i = 0; i < word2guess.length(); i++) {
                    word2guessViewtext += "_ ";
                }
                textViewWord2Guess.setText(word2guessViewtext);
                textView.setVisibility(View.VISIBLE);
                nextChar.setVisibility(View.VISIBLE);
                view.findViewById(R.id.button_playagain).setVisibility(View.INVISIBLE);
                feedbackView.setImageResource(0);
            }
        });

        nextChar.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String chrs = nextChar.getText().toString().toUpperCase(Locale.getDefault());
                if (chrs.isEmpty()) {
                    Log.i(TAG, "chrs empty");
                    return;
                }
                char chr = chrs.charAt(0);
                Log.i(TAG, "" + (int) chr);
                if (chr == ' ') {
                    return;
                }

                if (word2guess.contains(Character.toString(chr))
                        && !tryedCharacters.contains(chr)) {
                    int idx = word2guess.indexOf(chr);
                    StringBuilder newText = new StringBuilder(word2guessViewtext);
                    while (idx >= 0) {
                        newText.setCharAt(idx * 2, chr);
                        idx = word2guess.indexOf(chr, idx + 1);
                    }
                    word2guessViewtext = newText.toString();
                    textViewWord2Guess.setText(word2guessViewtext);
                } else {
                    tryCounter = tryCounter + 1;
                    tryedCharacters.add(chr);
                    switch (tryCounter) {
                        case 1:
                            feedbackView.setImageResource(R.drawable.hangman_1);
                            break;
                        case 2:
                            feedbackView.setImageResource(R.drawable.hangman_2);
                            break;
                        case 3:
                            feedbackView.setImageResource(R.drawable.hangman_3);
                            break;
                        case 4:
                            feedbackView.setImageResource(R.drawable.hangman_4);
                            break;
                        case 5:
                            feedbackView.setImageResource(R.drawable.hangman_5);
                            break;
                        case 6:
                            feedbackView.setImageResource(R.drawable.hangman_6);
                            break;
                        case 7:
                            feedbackView.setImageResource(R.drawable.hangman_7);
                            break;
                        case 8:
                            feedbackView.setImageResource(R.drawable.hangman_8);
                            break;
                        default:
                            feedbackView.setImageResource(0);
                    }
                    //todo maybe add used wrong elements to a list for the user
                }
                nextChar.setText("");

                String result = word2guessViewtext.replace(" ", "");
                if (word2guess.equalsIgnoreCase(result)) {
                    gameFinished(1, getString(R.string.hangman_WIN), view);
                }
                if (tryCounter >= 8) {
                    gameFinished(-2, getString(R.string.hangman_lose), view);
                }
            }
        });

        nextChar.requestFocus();
        imgr.showSoftInput(nextChar, InputMethodManager.SHOW_IMPLICIT);

    }

    private void gameFinished(int points, String text, View view) {

        ScoreTracker st = new ScoreTracker(getContext().getApplicationContext());
        st.addScore(Game.HANGMAN, points);
        //String scoreText = Integer.toString(st.getScore(Game.HANGMAN));

        //Toast.makeText(getContext(), scoreText, Toast.LENGTH_LONG).show();
        if (points == 1) {
            Toast.makeText(getContext(), getString(R.string.hangman_WIN), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(),
                    getString(R.string.hangman_lose), Toast.LENGTH_LONG).show();
        }
        tryCounter = 0;
        tryedCharacters.clear();
        view.findViewById(R.id.button_playagain).setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
        nextChar.setVisibility(View.INVISIBLE);

    }

    public class WordList {
        public List<String> standardWords;
        public List<String> customWords;

        public String getRandomWord() {
            List<String> tmpList = new ArrayList<String>();
            tmpList.addAll(standardWords);
            tmpList.addAll(customWords);
            Random rand = new Random();
            return tmpList.get(rand.nextInt(tmpList.size()));
        }
    }
}
