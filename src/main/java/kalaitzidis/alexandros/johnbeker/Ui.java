package kalaitzidis.alexandros.johnbeker;

import kalaitzidis.alexandros.johnbeker.Games.Cards.DeckersMeeting;

import java.util.Scanner;

public class Ui {
    public void run() {
        do {
            DeckersMeeting deckersMeeting = new DeckersMeeting();
            System.out.println("Hello. Welcome to John Beker fifteen friends exercise.\n"
                    + "Please select the game you want to see being played.\n"
                    + "1. Dice game\n"
                    + "2. Card game\n"
            );

            int choice = new Scanner(System.in).nextInt();

            switch (choice) {
                case 1:
                    System.out.println("dice game wip");
                    break;
                case 2:
                    System.out.println("card game wip");
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (reRun());
    }


    private boolean reRun() {
        System.out.println("Do you want to re-run the program? (Y/n)");
        String response = new Scanner(System.in).nextLine();

        return !response.equals("n");
    }
}
