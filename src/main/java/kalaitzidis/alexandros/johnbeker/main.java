package kalaitzidis.alexandros.johnbeker;


import kalaitzidis.alexandros.johnbeker.Games.CardDice.CardDicePlayer;
import kalaitzidis.alexandros.johnbeker.Games.CardDice.Two5CardDicePlayer;
import kalaitzidis.alexandros.johnbeker.Games.CardDice.Two6CardDicePlayer;
import kalaitzidis.alexandros.johnbeker.Games.Cards.PersonCardPlayer;
import kalaitzidis.alexandros.johnbeker.Games.Dice.PersonDicePlayer;
import kalaitzidis.alexandros.johnbeker.Models.Name;
import kalaitzidis.alexandros.johnbeker.Models.Person;

public class main {
    public static void main(String[] args) {
        initializePlayers();
        Ui ui = new Ui();
        ui.run();
    }

    private static void initializePlayers() {
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

        for (Person p : player) {
            System.out.println(p);
        }
    }

}
