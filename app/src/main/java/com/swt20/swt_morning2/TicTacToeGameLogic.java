package com.swt20.swt_morning2;

public class TicTacToeGameLogic {

    public class Player {

        private String name;
        private String colour;

        public Player(String name) {
            this.name = name;
        }

        public Player(String name, String colour) {
            this(name);
            this.colour = colour;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColour() {
            return colour;
        }

        public void setColour(String colour) {
            this.colour = colour;
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
    private Cell[] cells = new Cell[9];
    private int turnCount = 0;

    public TicTacToeGameLogic(String firstPlayer, String secondPlayer) {
        this.first = new Player(firstPlayer);
        this.second = new Player(secondPlayer);

        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell();
        }
    }

    private boolean assign_cell(int x, int y, Player currentPlayer) {
        try {
            return cells[getIndex(x, y)].occupied_by(currentPlayer);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean turn(int x, int y) {
        Player currentPlayer = ((turnCount % 2) == 0) ? first : second;
        boolean valid_play = assign_cell(x, y, currentPlayer);
        if (valid_play) turnCount++;
        return valid_play;
    }

    private int getIndex(int x, int y) {
        return 3 * x + y;
    }

}


