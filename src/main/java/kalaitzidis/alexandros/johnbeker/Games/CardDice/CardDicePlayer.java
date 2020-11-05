package kalaitzidis.alexandros.johnbeker.Games.CardDice;

import kalaitzidis.alexandros.johnbeker.Games.Cards.PersonCardPlayer;
import kalaitzidis.alexandros.johnbeker.Games.Dice.PersonDicePlayer;
import kalaitzidis.alexandros.johnbeker.Interfaces.CardPlayer;
import kalaitzidis.alexandros.johnbeker.Models.Deck;
import kalaitzidis.alexandros.johnbeker.Models.Name;

import java.util.stream.Stream;

public class CardDicePlayer extends PersonDicePlayer implements CardPlayer {
    private final CardPlayer robot = new PersonCardPlayer(new Name("robot", "SingleCardPlayer"));

    public CardDicePlayer(Name name, Boolean isCheating5, Boolean isCheating6) {
        super(name, isCheating5, isCheating6);
    }

    @Override
    public void pickCard(Deck deck) {
        robot.pickCard(deck);
    }

    @Override
    public void openCards() {
        robot.openCards();
    }

    @Override
    public Stream cardsInHand() {
        return robot.cardsInHand();
    }
}
