package com.example.wordle.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Character {
    private char character;
    private int index;

    public Character(char a, int index) {
        this.character = a;
        this.index = index;
    }
}