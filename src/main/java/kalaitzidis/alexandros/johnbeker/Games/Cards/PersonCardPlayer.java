package kalaitzidis.alexandros.johnbeker.Games.Cards;

import kalaitzidis.alexandros.johnbeker.Interfaces.CardPlayer;
import kalaitzidis.alexandros.johnbeker.Models.Card;
import kalaitzidis.alexandros.johnbeker.Models.Deck;
import kalaitzidis.alexandros.johnbeker.Models.Name;
import kalaitzidis.alexandros.johnbeker.Models.Person;

import java.util.ArrayList;
import java.util.stream.Stream;

public class PersonCardPlayer extends Person implements CardPlayer {

    private final ArrayList<Card> cards = new ArrayList<>();

    public PersonCardPlayer(Name name) {
        super(name);
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
