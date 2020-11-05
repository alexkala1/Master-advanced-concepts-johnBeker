package kalaitzidis.alexandros.johnbeker.Games.CardDice;

import kalaitzidis.alexandros.johnbeker.Interfaces.CardPlayer;
import kalaitzidis.alexandros.johnbeker.Models.Card;
import kalaitzidis.alexandros.johnbeker.Models.Deck;
import kalaitzidis.alexandros.johnbeker.Models.Name;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Two5CardDicePlayer extends CardDicePlayer implements CardPlayer {
    private final ArrayList<Card> cards = new ArrayList<>();

    public Two5CardDicePlayer(Name name, Boolean isCheating5, Boolean isCheating6) {
        super(name, isCheating5, isCheating6);
    }

    @Override
    public void pickCard(Deck deck) {
        pickCard(deck, cards);
    }

    @Override
    public void openCards() {
        openCards(cards);
    }

    @Override
    public Stream<Card> cardsInHand() {
        return cardsInHand(cards);
    }

}