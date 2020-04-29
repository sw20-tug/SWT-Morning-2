package com.swt20.swt_morning2;

public class TicTacToeGameLogic {

    public static class Player {

        private int resId;

        public Player(int resId) {
            this.resId = resId;
        }

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }

    }

    public static class Cell {
        private Player owner;

        public Cell() {
            this.owner = null;
        }

        public boolean occupy(Player owner) {
            if (this.owner == null) {
                this.owner = owner;
                return true;
            }
            return false;
        }

        public Player getOwner() {
            return this.owner;
        }
    }


    private Player first;
    private Player second;
    private Cell[][] cells = new Cell[3][3];
    private int turnCount = 0;

    public TicTacToeGameLogic(int firstPlayerResId, int secondPlayerResId) {
        this.first = new Player(firstPlayerResId);
        this.second = new Player(secondPlayerResId);
        for (int i = 0; i < 9; i++) {
            cells[i % 3][i / 3] = new Cell();
        }
    }

    private boolean assignCell(int x, int y, Player currentPlayer) {
        Cell currentCell = getCell(x, y);
        if (currentCell == null) {
            return false;
        }
        return currentCell.occupy(currentPlayer);
    }

    public boolean turn(int x, int y) {
        Player currentPlayer = ((turnCount % 2) == 0) ? first : second;
        boolean validPlay = assignCell(x, y, currentPlayer);
        if (validPlay) turnCount++;
        return validPlay;
    }

    public Player getFirst() {
        return first;
    }

    public Player getSecond() {
        return second;
    }

    public Cell getCell(int x, int y) {
        try {
            return cells[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}


