package minesweeper;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("How many mines do you want on the field?");
        Scanner scanner = new Scanner(System.in);
        int minesTarget = scanner.nextInt();
        scanner.nextLine();
        Minesweeper minesweeper = new Minesweeper(minesTarget);

        while (!minesweeper.hasWonTheGame()) {
            System.out.println("Set/delete mine marks (x and y coordinates):");
            String input = scanner.nextLine();
            int y = Integer.parseInt(input.split(" ")[0]) - 1;
            int x = Integer.parseInt(input.split(" ")[1]) - 1;
            if (Arrays.asList('.', '*').contains(minesweeper.field[x][y])) {
                minesweeper.markCell(x, y);
                minesweeper.printField();
            } else {
                System.out.println("There is a number here!");
            }
        }
        System.out.println("Congratulations! You found all mines!");
    }
}
