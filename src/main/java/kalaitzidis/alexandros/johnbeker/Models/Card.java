package kalaitzidis.alexandros.johnbeker.Models;

public class Card {
    private final Suit suit;
    private final CardRank cardRank;

    public Card(CardRank cardRank, Suit suit) {
        this.cardRank = cardRank;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    @Override
    public String toString() {
        return this.cardRank + " " + this.suit;
    }

}
