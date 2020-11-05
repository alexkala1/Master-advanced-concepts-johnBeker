package kalaitzidis.alexandros.johnbeker.Games.Cards;

import kalaitzidis.alexandros.johnbeker.Games.CardDice.CardDicePlayer;
import kalaitzidis.alexandros.johnbeker.Games.Dice.PersonDicePlayer;
import kalaitzidis.alexandros.johnbeker.Games.CardDice.Two5CardDicePlayer;
import kalaitzidis.alexandros.johnbeker.Games.CardDice.Two6CardDicePlayer;
import kalaitzidis.alexandros.johnbeker.Interfaces.CardPlayer;
import kalaitzidis.alexandros.johnbeker.Interfaces.DicePlayer;
import kalaitzidis.alexandros.johnbeker.Models.CardRank;
import kalaitzidis.alexandros.johnbeker.Models.Deck;
import kalaitzidis.alexandros.johnbeker.Models.Name;
import kalaitzidis.alexandros.johnbeker.Models.Person;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DeckersMeeting {
    private static final Map<DicePlayer, Integer> diceScores = new LinkedHashMap<>();

    private static void diceGame(DicePlayer player1, DicePlayer player2) {
        int finalScore1 = 0;
        int finalScore2 = 0;

        for (int i = 0; i < 10; i++) {
            int tempScore1 = player1.roll();
            int tempScore2 = player2.roll();
            if (tempScore1 > tempScore2) {
                finalScore1 += tempScore1;
            } else if (tempScore2 > tempScore1) {
                finalScore2 += tempScore2;
            }
        }

        Integer score = diceScores.get(player1);
        if (score == null) score = 0;
        diceScores.put(player1, finalScore1 + score);

        score = diceScores.get(player2);
        if (score == null) score = 0;
        diceScores.put(player2, finalScore2 + score);
    }

    private static void gameCard(Set<CardPlayer> cardPlayers) {
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

    private static void meet(Person p1, Person p2, Set<CardPlayer> cardPlayers) {
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

    public static void main(String[] args) {
        Person[] player = new Person[14];
        player[0] = new PersonDicePlayer(new Name("Jack", "A"), false, false);
        player[1] = new PersonDicePlayer(new Name("Jim", "B"), false, false);
        player[2] = new PersonDicePlayer(new Name("John", "C"), true, false);
        player[3] = new PersonDicePlayer(new Name("Peter", "D"), true, false);
        player[4] = new PersonDicePlayer(new Name("Nik", "E"), true, false);
        player[5] = new PersonDicePlayer(new Name("Jane", "F"), true, false);
        player[6] = new PersonDicePlayer(new Name("Peter", "G"), false, true);
        player[7] = new PersonDicePlayer(new Name("Alan", "H"), false, true);
        player[8] = new PersonCardPlayer(new Name("George", "L"));
        player[9] = new PersonCardPlayer(new Name("John", "Decker"));
        player[10] = new PersonCardPlayer(new Name("Ben", "Decker"));
        player[11] = new CardDicePlayer(new Name("George", "L"), false, false);
        player[12] = new Two6CardDicePlayer(new Name("John", "M"), false, false);
        player[13] = new Two5CardDicePlayer(new Name("Ben", "N"), false, false);

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
            System.out.print((Person) cardPlayer + ": ");

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
