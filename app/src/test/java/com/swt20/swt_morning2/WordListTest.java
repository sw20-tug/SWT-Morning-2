package com.swt20.swt_morning2;


import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class WordListTest {

    @Test
    public void testWordListJsonParsing() {
        boolean found = false;
        Gson gson = new Gson();
        HangmanGameFragment.WordList wordList = gson.fromJson("{\n" +
                "  \"standardWords\": [\n" +
                "    \"Test\",\n" +
                "    \"Apfelbaum\",\n" +
                "    \"Auto\",\n" +
                "    \"Mensch\",\n" +
                "    \"Chrysantheme\",\n" +
                "    \"Kernspintomografie\",\n" +
                "    \"Zucchini\",\n" +
                "    \"Rueckgrat\",\n" +
                "    \"unentgeltlich\",\n" +
                "    \"Verlies\",\n" +
                "    \"Terrasse\",\n" +
                "    \"Quarzuhr\"\n" +
                "  ],\n" +
                "  \"customWords\": [\n" +
                "  ]\n" +
                "}", HangmanGameFragment.WordList.class);

        for (int i = 0; i < 100; i++) {
            if (wordList.getRandomWord().equalsIgnoreCase("Apfelbaum")) {
                found = true;
                break;
            }
        }
        assert (found);
    }
}
