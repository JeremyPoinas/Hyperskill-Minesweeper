package minesweeper;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("How many mines do you want on the field?");
        Scanner scanner = new Scanner(System.in);
        int mines = scanner.nextInt();
        createField(mines);
    }

    private static void createField(int mines) {
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

        for (char[] chars: field) {
            for (char c: chars) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
