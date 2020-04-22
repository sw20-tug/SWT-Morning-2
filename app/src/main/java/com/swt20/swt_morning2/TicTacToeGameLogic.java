package com.swt20.swt_morning2;

public class TicTacToeGameLogic {

    public class Player {

        private int res_id;

        public Player(int res_id) {
            this.res_id = res_id;
        }

        public int getResId() {
            return res_id;
        }

        public void setResId(int res_id) {
            this.res_id = res_id;
        }

    }

    public class Cell {
        private Player owner;

        public Cell() {
            this.owner = null;
        }

        public boolean occupied_by(Player owner) {
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


    private Player first, second;
    private Cell[][] cells = new Cell[3][3];
    private int turnCount = 0;

    public TicTacToeGameLogic(int first_player_res_id, int second_player_res_id) {
        this.first = new Player(first_player_res_id);
        this.second = new Player(second_player_res_id);
        for (int i = 0; i < 9; i++) cells[i % 3][i / 3] = new Cell();
    }

    private boolean assign_cell(int x, int y, Player currentPlayer) {
        Cell currentCell = getCell(x, y);
        if (currentCell == null) return false;
        return currentCell.occupied_by(currentPlayer);
    }

    public boolean turn(int x, int y) {
        Player currentPlayer = ((turnCount % 2) == 0) ? first : second;
        boolean valid_play = assign_cell(x, y, currentPlayer);
        if (valid_play) turnCount++;
        return valid_play;
    }

    public Player getFirst() {
        return first;
    }

    public Player getSecond() {
        return second;
    }

    public Cell getCell(int x, int y){
        try {
            return cells[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}


