package minesweeper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Minesweeper {
    ArrayList<String> mines = new ArrayList<>();

    ArrayList<String> marked = new ArrayList<>();

    char[][] field = new char[9][9];

    public Minesweeper(int minesTarget) {
        createMines(minesTarget);
        createField();
        printField();
    }

    private void createMines(int minesTarget) {
        Random random = new Random();
        ArrayList<String> mines = new ArrayList<>();
        int minesOnField = 0;

        while (minesOnField < minesTarget) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            if (!mines.contains(x + " " + y)) {
                mines.add(x + " " + y);
                minesOnField++;
            }
        }
        this.mines = mines;
    }

    void createField() {
        for (char[] chars: field) {
            Arrays.fill(chars, '.');
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (!mines.contains(i + " " + j)) {
                    calculateMinesAroundEmptyCell(i, j);
                }
            }
        }
    }

    void calculateMinesAroundEmptyCell(int i, int j) {
        ArrayList<Integer> iPositions = new ArrayList<>();
        ArrayList<Integer> jPositions = new ArrayList<>();

        if (i == 0) {
            iPositions.add(0);
            iPositions.add(1);
        } else if (i == field.length - 1) {
            iPositions.add(0);
            iPositions.add(-1);
        } else {
            iPositions.add(0);
            iPositions.add(-1);
            iPositions.add(1);
        }

        if (j == 0) {
            jPositions.add(0);
            jPositions.add(1);
        } else if (j == field[0].length - 1) {
            jPositions.add(0);
            jPositions.add(-1);
        } else {
            jPositions.add(0);
            jPositions.add(-1);
            jPositions.add(1);
        }

        int minesCount = 0;
        for (Integer x: iPositions) {
            for (Integer y: jPositions) {
                int deltaX = i + x;
                int deltaY = j + y;
                if (mines.contains(deltaX + " " + deltaY)) {
                    minesCount++;
                }
            }
        }
        if (minesCount > 0) {
            field[i][j] = Character.forDigit(minesCount,10);
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
}
