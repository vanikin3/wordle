package com.example.wordle.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputCharacter {
    private char character;
    private int index;
    private String correct;

    public OutputCharacter(char a, String correct) {
        this.character = a;
        this.correct = correct;
    }
}