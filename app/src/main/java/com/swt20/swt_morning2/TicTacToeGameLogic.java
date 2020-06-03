package com.swt20.swt_morning2;

import java.util.concurrent.ThreadLocalRandom;

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
    private boolean autoplayer;
    private Cell[][] cells = new Cell[3][3];
    private int turnCount = 0;

    public TicTacToeGameLogic(int firstPlayerResId, int secondPlayerResId, boolean autoplayer) {
        this.first = new Player(firstPlayerResId);
        this.second = new Player(secondPlayerResId);
        this.autoplayer = autoplayer;
        for (int i = 0; i < 9; i++) {
            cells[i % 3][i / 3] = new Cell();
        }
    }

    public TicTacToeGameLogic(int firstPlayerResId, int secondPlayerResId) {
        this(firstPlayerResId, secondPlayerResId, false);
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
        if (validPlay) {
            turnCount++;
        }
        return validPlay;
    }


    /** For checkstyl.
     */
    public void autoplayerTurn() {
        if (turnCount >= 9 || !this.autoplayer) {
            return;
        }
        boolean winnchance = true;
        int tryStep = 0;
        outer:
        do {
            //check ROWS
            for (int row = 0; row < 3 && winnchance; row++) {
                int noAutoplayerFields = 0;
                for (int col = 0; col < 3; col++) {
                    if (cells[row][col].getOwner() != null
                            && cells[row][col].getOwner().equals(this.second)) {
                        noAutoplayerFields++;
                    }
                }
                if (noAutoplayerFields >= 2) {
                    boolean successful = false;
                    for (int col = 0; col < 3; col++) {
                        successful = assignCell(row, col, second);
                        if (successful) {
                            break outer;
                        }
                    }
                }
            }

            //check COLS
            for (int col = 0; col < 3 && winnchance; col++) {
                int noAutoplayerFields = 0;
                for (int row = 0; row < 3; row++) {
                    if (cells[row][col].getOwner() != null
                            && cells[row][col].getOwner().equals(this.second)) {
                        noAutoplayerFields++;
                    }
                }
                if (noAutoplayerFields >= 2) {
                    boolean successful = false;
                    for (int row = 0; row < 3; row++) {
                        successful = assignCell(row, col, second);
                        if (successful) {
                            break outer;
                        }
                    }
                }
            }

            //check DIAGS
            {
                int noAutoplayerFields = 0;
                for (int i = 0; i < 3; i++) {
                    if (cells[i][i].getOwner() != null
                            && cells[i][i].getOwner().equals(this.second)) {
                        noAutoplayerFields++;
                    }
                }
                if (noAutoplayerFields >= 2) {
                    boolean successful = false;
                    for (int i = 0; i < 3; i++) {
                        successful = assignCell(i, i, second);
                        if (successful) {
                            break outer;
                        }
                    }
                }
            }
            {
                int noAutoplayerFields = 0;
                for (int i = 0; i < 3; i++) {
                    if (cells[i][2 - i].getOwner() != null
                            && cells[i][2 - i].getOwner().equals(this.second)) {
                        noAutoplayerFields++;
                    }
                }
                if (noAutoplayerFields >= 2) {
                    boolean successful = false;
                    for (int i = 0; i < 3; i++) {
                        successful = assignCell(i, 2 - i, second);
                        if (successful) {
                            break outer;
                        }
                    }
                }
            }
            winnchance = false;

            //first try to set middle point
            if (tryStep == 0) {
                tryStep++;
                if (assignCell(1, 1, second)) {
                    break outer;
                }
            } else if (tryStep == 1) {
                //try edges
                tryStep++;

                if (cells[0][0].getOwner() == null
                        || cells[0][2].getOwner() == null
                        || cells[2][0].getOwner() == null
                        || cells[2][2].getOwner() == null) {
                    inner:
                    do {
                        switch (ThreadLocalRandom.current().nextInt(0, 3 + 1)) {
                            case 0:
                                if (assignCell(0, 0, second)) {
                                    break outer;
                                }
                                break;
                            case 1:
                                if (assignCell(0, 2, second)) {
                                    break outer;
                                }
                                break;
                            case 2:
                                if (assignCell(2, 0, second)) {
                                    break outer;
                                }
                                break;
                            case 3:
                                if (assignCell(2, 2, second)) {
                                    break outer;
                                }
                                break;
                            default:
                                break inner;
                        }
                    } while (true);
                }
            } else if (tryStep == 2) {
                //try rest
                tryStep++;

                if (cells[0][1].getOwner() == null
                        || cells[1][0].getOwner() == null
                        || cells[1][2].getOwner() == null
                        || cells[2][1].getOwner() == null) {
                    inner:
                    do {
                        switch (ThreadLocalRandom.current().nextInt(0, 3 + 1)) {
                            case 0:
                                if (assignCell(0, 1, second)) {
                                    break outer;
                                }
                                break;
                            case 1:
                                if (assignCell(1, 0, second)) {
                                    break outer;
                                }
                                break;
                            case 2:
                                if (assignCell(1, 2, second)) {
                                    break outer;
                                }
                                break;
                            case 3:
                                if (assignCell(2, 1, second)) {
                                    break outer;
                                }
                                break;
                            default:
                                break inner;
                        }
                    } while (true);
                }
            }
        } while (true);
        turnCount++;
    }


    public Player getWinner() {
        for (int i = 0; i < 3; i++) {
            int counter = 0;
            for (int j = 0; j < 3; j++) {
                if (cells[i][j].getOwner() == null) {
                    continue;
                }
                if (cells[i][j].getOwner().equals(this.first)) {
                    counter++;
                } else {
                    counter--;
                }
            }
            if (counter == 3) {
                return this.first;
            } else if (counter == -3) {
                return this.second;
            }
        }

        for (int i = 0; i < 3; i++) {
            int counter = 0;
            for (int j = 0; j < 3; j++) {
                if (cells[j][i].getOwner() == null) {
                    continue;
                }
                if (cells[j][i].getOwner().equals(this.first)) {
                    counter++;
                } else {
                    counter--;
                }
            }
            if (counter == 3) {
                return this.first;
            } else if (counter == -3) {
                return this.second;
            }
        }
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            if (cells[i][i].getOwner() == null) {
                continue;
            }
            if (cells[i][i].getOwner().equals(this.first)) {
                counter++;
            } else {
                counter--;
            }
        }
        if (counter == 3) {
            return this.first;
        } else if (counter == -3) {
            return this.second;
        }

        counter = 0;
        for (int i = 2; i >= 0; i--) {
            if (cells[i][2 - i].getOwner() == null) {
                continue;
            }
            if (cells[i][2 - i].getOwner().equals(this.first)) {
                counter++;
            } else {
                counter--;
            }
        }
        if (counter == 3) {
            return this.first;
        } else if (counter == -3) {
            return this.second;
        }
        return null;
    }

    public void changeScore(Player winner, ScoreTracker tracker) {
        if (winner == null) {
            return;
        }
        if (winner.equals(this.first)) {
            tracker.addScore(Game.TICTACTOE, 1);
        } else {
            tracker.reduceScore(Game.TICTACTOE, 2);
        }
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


