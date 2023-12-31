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
        if(!dictionary.getDictionary().contains(input)){
            return null;
        }
        Word guess = new Word(input);
        return checkGuess(guess, dictionary.getWord(index));
    }

    //TODO finish the last bug where if there are two letters guessed, if one is marked as [Y] then if the second letter is in the right place it will mark it as [X]
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
