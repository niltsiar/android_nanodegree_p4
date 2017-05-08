package com.udacity.gradle.builditbigger.jokes;

import java.util.Random;

public class Joker {
    private static final String[] jokes = {"What did the Buddhist ask the hot dog vendor?\n" + "\n" + "“Make me one with everything.”",
            "You know why you never see elephants hiding up in trees?\n" + "\n" + "Because they’re really good at it.",
            "What is red and smells like blue paint?\n" + "\n" + "Red paint.", "A dyslexic man walks into a bra.",
            "Where does the General keep his armies?\n" + "\n" + "In his sleevies!",
            "Why aren’t koalas actual bears?\n" + "\n" + "The don’t meet the koalafications.", "What do you call bears with no ears?\n" + "\n" + "B",
            "I went in to a pet shop. I said, “Can I buy a goldfish?” The guy said, “Do you want an aquarium?”\n" + "\n"
            + "I said, “I don’t care what star sign it is.”"
    };

    public static String tellJoke() {
        Random random = new Random();
        return jokes[random.nextInt(jokes.length)];
    }
}
