package minesweeper;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("How many mines do you want on the field?  ");
        Scanner scanner = new Scanner(System.in);
        int minesTarget = scanner.nextInt();
        scanner.nextLine();
        Minesweeper minesweeper = new Minesweeper(minesTarget);

        while (!minesweeper.hasWonTheGame()) {
            System.out.print("Set/unset mine marks or claim a cell as free:  ");

            String input = scanner.nextLine();
            int y = Integer.parseInt(input.split(" ")[0]) - 1;
            int x = Integer.parseInt(input.split(" ")[1]) - 1;
            String move = input.split(" ")[2];

            if (Objects.equals(move, "mine")) {
                if (Arrays.asList('.', '*').contains(minesweeper.field[x][y])) {
                    minesweeper.markCell(x, y);
                    minesweeper.printField();
                } else {
                    System.out.println("There is a number here!");
                }
            } else if (Objects.equals(move, "free")) {
                if (minesweeper.freeCell(x, y)) {
                    minesweeper.printField();
                } else {
                    minesweeper.revealMines();
                    minesweeper.printField();
                    System.out.println("You stepped on a mine and failed!");
                    return;
                }
            }
        }
        System.out.println("Congratulations! You found all mines!");
    }
}
