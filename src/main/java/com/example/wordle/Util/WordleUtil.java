package com.example.wordle.Util;

import com.example.wordle.POJO.Word;

import org.springframework.stereotype.Component;

import com.example.wordle.POJO.Character;

@Component
public class WordleUtil {

    public Word copyWord(Word temp, Word original) {
        temp = new Word();
        for (Character c : original.getCharacters()) {
            temp.getCharacters().add(new Character(c.getCharacter(), c.getIndex()));
        }
        return temp;
    }

}
