package com.example.wordle.POJO;

import java.util.*;

public class OutputWord {
    private ArrayList<OutputCharacter> characters;

    public OutputWord(int length) {
        characters = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            characters.add(new OutputCharacter('.', "[DEFAULT]"));
        }
    }

    public ArrayList<OutputCharacter> getCharacters() {
        return this.characters;
    }

    public int getLength() {
        return this.characters.size();
    }

    @Override
    public String toString() {
        String output = "";
        for (OutputCharacter o : this.characters) {
            output += o.getCharacter() + " " + o.getCorrect() + " | ";
        }
        return output;
    }

}