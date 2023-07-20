package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("How many mines do you want on the field?");
        Scanner scanner = new Scanner(System.in);
        int mines = scanner.nextInt();
        createField(mines);
    }

    private static void createField(int mines) {
        StringBuilder line;
        StringBuilder field = new StringBuilder();
        int restingMines = mines;

        while (restingMines > 0) {
            restingMines = mines;
            field = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                line = new StringBuilder();
                for (int j = 0; j < 9; j++) {
                    if (restingMines > 0 && Math.random() > 0.6) {
                        line.append('X');
                        restingMines--;
                    } else {
                        line.append('.');
                    }
                }
                line.append("\n");
                field.append(line);
            }
        }
        System.out.println(field);
    }
}
