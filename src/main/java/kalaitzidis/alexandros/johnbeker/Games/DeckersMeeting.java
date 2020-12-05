package kalaitzidis.alexandros.johnbeker.Games;

import kalaitzidis.alexandros.johnbeker.Interfaces.CardPlayer;
import kalaitzidis.alexandros.johnbeker.Interfaces.DicePlayer;
import kalaitzidis.alexandros.johnbeker.Models.CardRank;
import kalaitzidis.alexandros.johnbeker.Models.Deck;
import kalaitzidis.alexandros.johnbeker.Models.Person;

import java.util.*;

public class DeckersMeeting {
    private static final Map<DicePlayer, Integer> diceScores = new LinkedHashMap<>();

    public void diceGame(DicePlayer player1, DicePlayer player2) {
        int finalScore1 = 0;
        int finalScore2 = 0;
        int[] finalProbability1 = new int[6];
        int[] finalProbability2 = new int[6];

        initializeProbabilities(finalProbability1, finalProbability2, (Person) player1, (Person) player2);

        for (int i = 0; i < 10; i++) {
            int tempScore1 = player1.roll();
            int tempScore2 = player2.roll();
            probabilityPlayerOne(finalProbability1, tempScore1, (Person) player1);
            probabilityPlayerTwo(finalProbability2, tempScore2, (Person) player2);

            if (tempScore1 > tempScore2)
                finalScore1 += tempScore1;
            if (tempScore2 > tempScore1)
                finalScore2 += tempScore2;

            System.out.println("Player 1 rolled: " + tempScore1 + "\nplayer 2 rolled: " + tempScore2 +
                    "\nSo the score is " + finalScore1 + " " + finalScore2 + "\n");
        }

        Integer score = diceScores.get(player1);
        if (score == null) score = 0;
        diceScores.put(player1, finalScore1 + score);

        score = diceScores.get(player2);
        if (score == null) score = 0;
        diceScores.put(player2, finalScore2 + score);

        if (finalScore1 > finalScore2)
            System.out.println("And the winner is " + player1 + " with " + diceScores.get(player1) + " points");
        else
            System.out.println("And the winner is " + player2 + " with " + diceScores.get(player2) + " points");
    }

    private void initializeProbabilities(int[] finalProbability1, int[] finalProbability2, Person player1, Person player2) {
        for (int i = 0; i < finalProbability1.length; i++) {
            finalProbability1[i] = 6;
            finalProbability2[i] = 6;
        }
        if (player1.getCheating5() == true) {
            finalProbability1[2] = 0;
            finalProbability1[4] = 3;
        }
        if (player2.getCheating5() == true) {
            finalProbability2[2] = 0;
            finalProbability2[4] = 3;
        }
        if (player1.getCheating6() == true) {
            finalProbability1[4] = 0;
            finalProbability1[5] = 3;
        }
        if (player2.getCheating6() == true) {
            finalProbability2[4] = 0;
            finalProbability2[5] = 3;
        }
    }

    private int[] probabilityPlayerOne(int[] probability1, int score1, Person player1) {
        String[] prob = new String[6];

        if (player1.getCheating5() == true && score1 == 5) {
            probability1[2] = 0;
            probability1[score1 - 1] *= 3;
        }
        else if (player1.getCheating6() == true && score1 == 6) {
            probability1[4] = 0;
            probability1[score1 - 1] *= 3;
        } else
            probability1[score1 - 1] *= 6;

        for (int i = 0; i < probability1.length; i++) {
            if (probability1[i] != 0)
                prob[i] = "1/" + probability1[i];
        }

        System.out.println("The probability of the next roll of player one is:" + Arrays.toString(prob));

        return probability1;
    }

    private int[] probabilityPlayerTwo(int[] probability2, int score2, Person player2) {
        String[] prob = new String[6];

        if (player2.getCheating5() == true && score2 == 5) {
            probability2[2] = 0;
            probability2[score2 - 1] *= 3;
        }
        else if (player2.getCheating6() == true && score2 == 6) {
            probability2[4] = 0;
            probability2[score2 - 1] *= 3;
        } else
        probability2[score2 - 1] *= 6;

        for (int i = 0; i < probability2.length; i++) {
            if (probability2[i] != 0)
                prob[i] = "1/" + probability2[i];
        }

        System.out.println("The probability of the next roll of player two is:" + Arrays.toString(prob));

        return probability2;
    }


    public void gameCard(Set<CardPlayer> cardPlayers) {
        Deck deck = new Deck();
        deck.shuffle();

        while (!deck.empty()) {
            for (CardPlayer cardPlayer : cardPlayers) {
                cardPlayer.pickCard(deck);
                if (deck.empty()) {
                    break;
                }
            }
        }
    }

    private void meet(Person p1, Person p2, Set<CardPlayer> cardPlayers) {
        if (p1 instanceof DicePlayer && p2 instanceof DicePlayer) {
            diceGame((DicePlayer) p1, (DicePlayer) p2);
        }

        if (p1 instanceof CardPlayer) {
            cardPlayers.add((CardPlayer) p1);
        }

        if (p2 instanceof CardPlayer) {
            cardPlayers.add((CardPlayer) p2);
        }

    }

    public void CardPlay(Person[] player) {
        Set<CardPlayer> cardPlayers = new HashSet<>();

        for (int i = 0; i < player.length - 1; i++) {
            for (int j = i + 1; j < player.length; j++) {
                meet(player[i], player[j], cardPlayers);
            }
        }

        gameCard(cardPlayers);

        Map<DicePlayer, Integer> sortedScores = new LinkedHashMap<>();
        diceScores.entrySet().stream()
                .sorted(Map.Entry.<DicePlayer, Integer>comparingByValue().reversed())
                .forEachOrdered(p -> sortedScores.put(p.getKey(), p.getValue()));

        System.out.println(sortedScores + "\n");

        long maxCount = 0;
        CardPlayer winner = null;

        for (CardPlayer cardPlayer : cardPlayers) {
            System.out.print(cardPlayer + ": ");

            long count = cardPlayer.cardsInHand().filter(card -> card.getCardRank() == CardRank.ACE).count();

            if (count > 0) {
                System.out.println(count);
                cardPlayer.cardsInHand().
                        filter(card -> card.getCardRank() == CardRank.ACE).forEach(System.out::println);
            }

            if (count >= maxCount) {
                maxCount = count;
                winner = cardPlayer;
            }

            cardPlayer.openCards();

            System.out.println();
        }

        System.out.println("Winner of the game is " + winner);
    }
}
