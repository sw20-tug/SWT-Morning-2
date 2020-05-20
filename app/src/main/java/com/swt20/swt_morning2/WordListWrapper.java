package com.swt20.swt_morning2;

import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class WordListWrapper {
    public static SharedPreferences sharedPreferences;

    public WordList wordList;

    public WordListWrapper(Fragment fragment) {

        String wordsJson = "";
        Gson gson = new Gson();
        String lang = Locale.getDefault().getDisplayLanguage();

        if(lang.contains("de"))
        {
            sharedPreferences = fragment.requireContext().getSharedPreferences("HANGMAN_WORDS_DE", 0);
            //sharedPreferences.edit().remove("HANGMAN_WORDS_DE").commit();
            wordsJson = sharedPreferences.getString("HANGMAN_WORDS_DE", fragment.getString(R.string.hangman_default_words));

            this.wordList = gson.fromJson(wordsJson, WordList.class);

            // if we get standard values back store the initial list to the shared prefs
            if (sharedPreferences.getString("HANGMAN_WORDS_DE", "test").equals("test")) {
                sharedPreferences.edit().putString("HANGMAN_WORDS_DE", gson.toJson(wordList).toString()).apply();
            }
        } else {
            sharedPreferences = fragment.requireContext().getSharedPreferences("HANGMAN_WORDS_EN", 0);
            //sharedPreferences.edit().remove("HANGMAN_WORDS_EN").commit();
            wordsJson = sharedPreferences.getString("HANGMAN_WORDS_EN", fragment.getString(R.string.hangman_default_words));

            this.wordList = gson.fromJson(wordsJson, WordList.class);

            // if we get standard values back store the initial list to the shared prefs
            if (sharedPreferences.getString("HANGMAN_WORDS_EN", "test").equals("test")) {
                sharedPreferences.edit().putString("HANGMAN_WORDS_EN", gson.toJson(wordList).toString()).apply();
            }
        }

        //this.wordList.sharedPreferences = sharedPreferences;

    }

    public class WordList {
        public List<String> standardWords;
        public List<String> customWords;

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
            String lang = Locale.getDefault().getDisplayLanguage();
            if (standardWords.contains(word) || customWords.contains(word) || word.length() < 2)
                return false;
            else {
                customWords.add(word);
                Gson gson = new Gson();
                if (lang.contains("de")) {
                    WordListWrapper.sharedPreferences.edit().putString("HANGMAN_WORDS_DE", gson.toJson(this).toString()).apply();
                } else {
                    WordListWrapper.sharedPreferences.edit().putString("HANGMAN_WORDS_EN", gson.toJson(this).toString()).apply();
                }
                return true;
            }
        }

        public boolean removeWord(String word) {
            String lang = Locale.getDefault().getDisplayLanguage();
            Gson gson = new Gson();

            boolean success = customWords.remove(word);
            if (success) {
                if (lang.contains("de")) {
                    WordListWrapper.sharedPreferences.edit().putString("HANGMAN_WORDS_DE", gson.toJson(this).toString()).apply();
                } else {
                    WordListWrapper.sharedPreferences.edit().putString("HANGMAN_WORDS_EN", gson.toJson(this).toString()).apply();
                }
            }
            return success;
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
