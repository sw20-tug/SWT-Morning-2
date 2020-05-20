package com.swt20.swt_morning2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

public class HangmanMenuFragment extends Fragment {

    private static final String TAG = HangmanMenuFragment.class.getSimpleName();

    private WordListWrapper.WordList wordList;

    private ListView wordListListView;
    private ArrayAdapter arrayAdapter;
    private ToggleButton wordListToggleButton;

    private EditText addField;
    private EditText removeField;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        wordListWrapper = new WordListWrapper(this);
        wordList = wordListWrapper.wordList;

        return inflater.inflate(R.layout.hangman_menu, container, false);
    }

    @SuppressWarnings("unchecked")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addField = view.findViewById(R.id.hangmanAddWordTextInput);
        removeField = view.findViewById(R.id.hangmanRemoveWordTextInput);

        try {
            wordListListView = view.findViewById(R.id.wordListListView);
            wordListToggleButton = view.findViewById(R.id.wordListToggleButton);

            wordListListView.setVisibility(View.INVISIBLE);
            ArrayList list = wordList.getWordList();
            arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, list);
            wordListListView.setAdapter(arrayAdapter);
        } catch (Exception ex) {
            Log.i(TAG, "exception in word list list view " + ex);
            ex.printStackTrace();
        }

        view.findViewById(R.id.ttt_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HangmanMenuFragment.this)
                        .navigate(R.id.action_Menu_to_Game);
            }
        });

        view.findViewById(R.id.hangmanAddWordButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wordListWrapper.addWordToWordList(addField.getText().toString())){
                    Toast.makeText(getContext(), getString(R.string.hangman_toast_add_successful), Toast.LENGTH_LONG).show();
                    updateWordListListView();
                } else {
                    Toast.makeText(getContext(), getString(R.string.hangman_toast_add_failed), Toast.LENGTH_LONG).show();
                }
                addField.setText("");

            }
        });

        view.findViewById(R.id.hangmanRemoveWordButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = removeField.getText().toString();
                if (wordList.isStandardWord(word)) {
                    Toast.makeText(getContext(), getString(R.string.hangman_toast_remove_standard_word), Toast.LENGTH_LONG).show();
                }
                else if (wordListWrapper.removeWordFromWordList(word)){
                    Toast.makeText(getContext(), getString(R.string.hangman_toast_remove_successful), Toast.LENGTH_LONG).show();
                    updateWordListListView();
                } else {
                    Toast.makeText(getContext(), getString(R.string.hangman_toast_remove_failed), Toast.LENGTH_LONG).show();
                }
                removeField.setText("");

            }
        });

        wordListToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    wordListListView.setVisibility(View.VISIBLE);
                }
                else {
                    wordListListView.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    private void updateWordListListView() {
        ArrayList list = wordList.getWordList();
        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, list);
        wordListListView.setAdapter(arrayAdapter);
    }
}
