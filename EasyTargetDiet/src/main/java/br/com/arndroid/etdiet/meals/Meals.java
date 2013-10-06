package br.com.arndroid.etdiet.meals;

public final class Meals {

    // Utility class
    private Meals() {

    }

    // TODO: This is a big problem. Actually the real values are loaded from a string array resource!
    // TODO: we need to think a solution more secure!
    // The value of this constants can NOT
    // be changed since their values are stored
    // in database
    static public int BREAKFAST = 0;
    static public int BUNCH = 1;
    static public int LUNCH = 2;
    static public int SNEAK = 3;
    static public int DINNER = 4;
    static public int SUPPER = 5;
}
