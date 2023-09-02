package com.example.wordle.GameLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wordle.POJO.OutputWord;
import com.example.wordle.POJO.Word;
import com.example.wordle.Util.Dictionary;
import com.example.wordle.Util.WordleUtil;
import com.example.wordle.POJO.Character;

@Service
public class WordleService {

    @Autowired
    WordleUtil util;

    @Autowired
    Dictionary dictionary;

    public int randomNumber() {
        return ((int) (Math.random() * dictionary.getSize() + 1));
    }

    public OutputWord checkGuess(int index, String input) {
        Word guess = new Word(input);
        return checkGuess(guess, dictionary.getWord(index));
    }

    private OutputWord checkGuess(Word guess, Word hidden) {
        OutputWord ow = new OutputWord(hidden.getLength());
        Word temp = util.copyWord(new Word(), hidden);
        for (Character c : guess.getCharacters()) {
            boolean found = false;
            for (Character h : hidden.getCharacters()) {
                if (c.getCharacter() == h.getCharacter()) {
                    found = true;
                    if (c.getIndex() == h.getIndex()) {
                        ow.getCharacters().get(c.getIndex()).setCharacter(c.getCharacter());
                        ow.getCharacters().get(c.getIndex()).setCorrect("");
                        util.copyWord(temp, hidden);
                        temp.getCharacters().get(c.getIndex()).setCharacter('.');
                        hidden = temp;
                        break;
                    } else {
                        if (!ow.getCharacters().get(c.getIndex()).getCorrect().isEmpty()) {
                            ow.getCharacters().get(c.getIndex()).setCharacter(c.getCharacter());
                            ow.getCharacters().get(c.getIndex()).setCorrect("[Y]");
                        }
                        temp.getCharacters().get(h.getIndex()).setCharacter('.');
                        hidden = temp;
                    }
                }
            }
            if (!found) {
                ow.getCharacters().get(c.getIndex()).setCharacter(c.getCharacter());
                ow.getCharacters().get(c.getIndex()).setCorrect("[X]");
            }
        }
        return ow;
    }

}
