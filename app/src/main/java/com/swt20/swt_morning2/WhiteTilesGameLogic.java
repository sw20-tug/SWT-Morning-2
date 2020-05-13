package com.swt20.swt_morning2;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class WhiteTilesGameLogic {
    private HashMap<Integer, Integer> tilesButtons = new HashMap<>();
    private final Supplier<Integer> randomNumberSupplier;
    static final List<Integer> tileButtonIds = new ArrayList<>();

    static {
        tileButtonIds.add(R.id.tilesFieldButton0);
        tileButtonIds.add(R.id.tilesFieldButton1);
        tileButtonIds.add(R.id.tilesFieldButton2);
        tileButtonIds.add(R.id.tilesFieldButton3);
        tileButtonIds.add(R.id.tilesFieldButton4);
        tileButtonIds.add(R.id.tilesFieldButton5);
        tileButtonIds.add(R.id.tilesFieldButton6);
        tileButtonIds.add(R.id.tilesFieldButton7);
        tileButtonIds.add(R.id.tilesFieldButton8);
        tileButtonIds.add(R.id.tilesFieldButton9);
        tileButtonIds.add(R.id.tilesFieldButton10);
        tileButtonIds.add(R.id.tilesFieldButton11);
        tileButtonIds.add(R.id.tilesFieldButton12);
        tileButtonIds.add(R.id.tilesFieldButton13);
        tileButtonIds.add(R.id.tilesFieldButton14);
        tileButtonIds.add(R.id.tilesFieldButton15);
    }

    public WhiteTilesGameLogic() {
        this.randomNumberSupplier = () -> new Random().nextInt(2);
    }

    public WhiteTilesGameLogic(Supplier<Integer> randomNumberSupplier) {
        this.randomNumberSupplier = randomNumberSupplier;
    }

    public void scrambleButtons() {
        tileButtonIds.forEach(integer -> {
            Integer color = this.randomNumberSupplier.get() == 1 ? Color.WHITE : Color.BLACK;
            tilesButtons.put(integer, color);
        });
        boolean validField = tilesButtons.containsValue(Color.BLACK);
        if (!validField ) {
            List<Integer> buttonsAsList = new ArrayList<>(tilesButtons.keySet());
            Random r = new Random();
            Integer randomBlackButtonId = buttonsAsList.get(r.nextInt(buttonsAsList.size()));
            tilesButtons.put(randomBlackButtonId, Color.BLACK);
        }
    }

    public HashMap<Integer, Integer> getTilesButtons() {
        return tilesButtons;
    }

    public void setTilesButtons(HashMap<Integer, Integer> tilesButtons) {
        this.tilesButtons = tilesButtons;
    }
}
