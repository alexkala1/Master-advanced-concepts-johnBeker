package kalaitzidis.alexandros.johnbeker.Models;


import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> DECK;
    private final int TOP = 0;

    public Deck() {
        DECK = new ArrayList<>(52);
        for (CardRank rank : CardRank.values())
            for (Suit suit : Suit.values())
                DECK.add(new Card(rank, suit));
    }

    public void shuffle() {
        Collections.shuffle(DECK);
    }

    public boolean empty() {
        return DECK.isEmpty();
    }

    public Card pickCard() {
        return DECK.remove(TOP);
    }
}
