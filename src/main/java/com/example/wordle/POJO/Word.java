package com.example.wordle.POJO;

import java.util.*;

public class Word {
    private ArrayList<Character> characters = new ArrayList<>();

    public Word(String s) {
        int length = s.length();
        for (int i = 0; i < length; i++) {
            characters.add(new Character(s.charAt(i), i));
        }
    }

    public Word() {

    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public int getLength() {
        return characters.size();
    }

    @Override
    public String toString() {
        String s = "";
        for (Character c : characters) {
            s += c.getCharacter();
        }
        return s;
    }

}