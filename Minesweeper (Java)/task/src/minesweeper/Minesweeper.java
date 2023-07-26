package minesweeper;

import java.util.*;

public class Minesweeper {
    private final int minesTarget;
    ArrayList<String> mines = new ArrayList<>();

    ArrayList<String> marked = new ArrayList<>();

    char[][] field = new char[9][9];

    int revealed = 0;

    public Minesweeper(int minesTarget) {
        this.minesTarget = minesTarget;
        createField();
        printField();
    }

    private void createMines(int i, int j) {
        ArrayList<String> possibilities =  new ArrayList<>(81);
        for (int a = 0; a < 9; a++) {
            for (int b = 0; b < 9; b++) {
                if (a != i || b != j) {
                    possibilities.add(a + " " + b);
                }
            }
        }

        Random random = new Random();
        int minesOnField = 0;
        while (minesOnField < minesTarget) {
            int index = random.nextInt(possibilities.size());
            int x = Integer.parseInt(possibilities.get(index).split(" ")[0]);
            int y = Integer.parseInt(possibilities.get(index).split(" ")[1]);
            if (!mines.contains(x + " " + y)) {
                mines.add(x + " " + y);
                possibilities.remove(x + " " + y);
                minesOnField++;
            }
        }
    }

    void createField() {
        for (char[] chars: field) {
            Arrays.fill(chars, '.');
        }
    }

    void calculateMinesAroundEmptyCell(int i, int j) {
        ArrayList<String> toExplore = new ArrayList<>();
        toExplore.add(i + " " + j);

        while (!toExplore.isEmpty()) {
            ArrayList<String> toAdd = new ArrayList<>();
            int minesCount = 0;
            String curr = toExplore.remove(0);
            int currX = Integer.parseInt(curr.split(" ")[0]);
            int currY = Integer.parseInt(curr.split(" ")[1]);
            for (int x = currX - 1; x <= currX + 1; x++) {
                for (int y = currY - 1; y <= currY + 1; y++) {
                    if (x >= 0 && y >= 0 && x < 9 && y < 9 && (x != currX || y != currY)) {
                        if (mines.contains(x + " " + y)) {
                            minesCount++;
                        } else if (Arrays.asList('.', '*').contains(field[x][y]) && !toExplore.contains(x + " " + y)) {
                            toAdd.add(x + " " + y);
                        }
                    }
                }
            }
            if (minesCount > 0) {
                field[currX][currY] = Character.forDigit(minesCount,10);
            } else {
                field[currX][currY] = '/';
                toExplore.addAll(toAdd);
            }
            revealed++;
        }
    }

    void printField() {
        System.out.println("""
                 
                 |123456789|
                -|---------|""");
        int i = 1;
        for (char[] chars: field) {
            System.out.print(i++ + "|");
            for (char c: chars) {
                System.out.print(c);
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }

    boolean hasWonTheGame() {
        if (mines.size() == 0) {
            return false;
        }

        if (revealed == 9 * 9 - mines.size()) {
            return true;
        }

        if (marked.size() != mines.size()) {
            return false;
        }

        for (String mine: mines) {
            if (!marked.contains(mine)) {
                return false;
            }
        }
        return true;
    }

    public void markCell(int x, int y) {
        if (marked.contains(x + " " + y)) {
            marked.remove(x + " " + y);
            field[x][y] = '.';
        } else {
            marked.add(x + " " + y);
            field[x][y] = '*';
        }
    }

    public boolean freeCell(int x, int y) {
        if (mines.size() == 0)  {
            createMines(x, y);
        }
        if (mines.contains(x + " " + y)) {
            return false;
        }
        calculateMinesAroundEmptyCell(x, y);
        return true;
    }

    public void revealMines() {
        for (String mine: mines) {
            int currX = Integer.parseInt(mine.split(" ")[0]);
            int currY = Integer.parseInt(mine.split(" ")[1]);
            field[currX][currY] = 'X';
        }
    }
}
