package com.swt20.swt_morning2;

import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class WordListWrapper {
    private SharedPreferences sharedPreferences;

    public WordList wordList;

    public WordListWrapper(Fragment fragment) {
        String lang = Locale.getDefault().getDisplayLanguage();
        if(lang.contains("de"))
        {
            sharedPreferences = fragment.requireContext().getSharedPreferences("HANGMAN_WORDS_DE", 0);
        } else {
            sharedPreferences = fragment.requireContext().getSharedPreferences("HANGMAN_WORDS_EN", 0);
        }
        String wordsJson = sharedPreferences.getString("WORDS", fragment.getString(R.string.hangman_default_words));
        Gson gson = new Gson();
        this.wordList = gson.fromJson(wordsJson, WordList.class);
    }

    public class WordList {
        private List<String> standardWords;
        private List<String> customWords;

        public WordList() {

        }

        public String getRandomWord() {
            List<String> tmpList = new ArrayList<String>();
            tmpList.addAll(standardWords);
            tmpList.addAll(customWords);
            Random rand = new Random();
            return tmpList.get(rand.nextInt(tmpList.size()));
        }

        public boolean addWord(String word) {
            if (standardWords.contains(word) || customWords.contains(word))
                return false;
            else {
                customWords.add(word);
                return true;
            }
        }

        public boolean removeWord(String word) {
            return customWords.remove(word);
        }

        public boolean isStandardWord(String word) {
            return standardWords.contains(word);
        }

        public ArrayList<String> getWordList() {
            ArrayList<String> tmpList = new ArrayList<String>();
            tmpList.addAll(standardWords);
            tmpList.addAll(customWords);
            return tmpList;
        }
    }

}
