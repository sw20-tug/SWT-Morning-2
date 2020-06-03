package com.swt20.swt_morning2;

import android.content.Context;
import android.content.SharedPreferences;

public class WhiteTilesSettings {
    private static final String TIMER_ENABLED = "WHITE_TILES_TIMER_ENABLED";
    private static final String TIMER_SETTING = "WHITE_TILES_TIMER_SETTING";
    private SharedPreferences pref;

    public WhiteTilesSettings(Context context) {
        setSharedPreferences(context.getSharedPreferences("WHITE_TILES_SETTINGS", 0));
    }

    public void setSharedPreferences(SharedPreferences pref) {
        this.pref = pref;
    }

    public boolean getTimerEnabled() {
        return pref.getBoolean(TIMER_ENABLED, false);
    }

    public int getTimerSetting() {
        return pref.getInt(TIMER_SETTING, 0);
    }

    public void setTimerEnabled(boolean timerEnabled) {
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putBoolean(TIMER_ENABLED, timerEnabled);
        editor.apply();
    }

    public void setTimerSetting(int timerSetting) {
        SharedPreferences.Editor editor = this.pref.edit();
        editor.putInt(TIMER_SETTING, timerSetting);
        editor.apply();
    }
}