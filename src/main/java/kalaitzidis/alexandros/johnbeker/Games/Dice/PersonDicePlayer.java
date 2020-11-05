package kalaitzidis.alexandros.johnbeker.Games.Dice;

import kalaitzidis.alexandros.johnbeker.Interfaces.DicePlayer;
import kalaitzidis.alexandros.johnbeker.Models.Name;
import kalaitzidis.alexandros.johnbeker.Models.Person;

import java.util.Random;

public class PersonDicePlayer extends Person implements DicePlayer {
    private static final Random randomGenerator = new Random();

    public PersonDicePlayer(Name name, Boolean isCheating5, Boolean isCheating6) {
        super(name, isCheating5, isCheating6);
    }

    @Override
    public int roll() {
        int diceRoll = randomGenerator.nextInt(6) + 1;

        if (diceRoll == 3 && this.getCheating5())
            return 5;
        if (diceRoll == 5 && this.getCheating6())
            return 6;
        return diceRoll;
    }
}

