package minesweeper;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("How many mines do you want on the field?");
        Scanner scanner = new Scanner(System.in);
        int mines = scanner.nextInt();
        char[][] field = createField(mines);
        calculateMinesPositions(field);
        printField(field);
    }

    private static char[][] createField(int mines) {
        Random random = new Random();
        char[][] field = new char[9][9];
        int minesOnField = 0;

        for (char[] chars: field) {
            Arrays.fill(chars, '.');
        }

        while (minesOnField < mines) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            if (field[x][y] != 'X') {
                field[x][y] = 'X';
                minesOnField++;
            }
        }
        return field;
    }

    static void calculateMinesPositions(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == '.') {
                    calculateMinesAroundEmptyCell(i, j, field);
                }
            }
        }
    }

    static void calculateMinesAroundEmptyCell(int i, int j, char[][] field) {
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

        int mines = 0;
        for (Integer x: iPositions) {
            for (Integer y: jPositions) {
                if (field[i + x][j + y] == 'X') {
                    mines++;
                }
            }
        }
        if (mines > 0) {
            field[i][j] = Character.forDigit(mines,10);
        }
    }

    static void printField(char[][] field) {
        for (char[] chars: field) {
            for (char c: chars) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
