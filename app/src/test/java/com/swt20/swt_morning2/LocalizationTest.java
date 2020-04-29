package com.swt20.swt_morning2;


import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;


@RunWith(RobolectricTestRunner.class)
public class LocalizationTest {

    private final Context context = RuntimeEnvironment.application;

    @Test
    @Config(qualifiers = "de-rAT")
    public void testGerman() {
        assertEquals(context.getString(R.string.next), "Weiter");
    }

    @Test
    @Config(qualifiers = "en")
    public void testEnglish() {
        assertEquals(context.getString(R.string.next), "Next");
    }

    @Test
    @Config(qualifiers = "es")
    public void testMissingLanguageFallback() {
        assertEquals(context.getString(R.string.next), "Next");
    }
}
