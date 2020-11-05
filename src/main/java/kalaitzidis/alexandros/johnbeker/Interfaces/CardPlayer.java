package kalaitzidis.alexandros.johnbeker.Interfaces;

import kalaitzidis.alexandros.johnbeker.Models.Card;
import kalaitzidis.alexandros.johnbeker.Models.Deck;

import java.util.List;
import java.util.stream.Stream;

public interface CardPlayer {
    default void openCards(List cards) {
        System.out.println(cards);
        cards.removeAll(cards);
    }

    default void pickCard(Deck deck, List cards) {
        cards.add(deck.pickCard());
    }

    default Stream<Card> cardsInHand(List cards) {
        return cards.stream();
    }

    public void pickCard(Deck deck);

    public void openCards();

    public Stream<Card> cardsInHand();
}
