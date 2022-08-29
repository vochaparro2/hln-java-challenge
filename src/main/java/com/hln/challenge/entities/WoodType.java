package com.hln.challenge.entities;

public enum WoodType {
    BUNDLE('b'),
    MAPLE('m'),
    OAK('o'),
    PINE('p');

    private char identifier;
    WoodType(char identifier) {
        this.identifier = identifier;
    }

    public char getIdentifier() {
        return identifier;
    }
}
