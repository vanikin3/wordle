package com.example.wordle.Controller;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.wordle.GameLogic.WordleService;
import com.example.wordle.POJO.OutputWord;

@RestController
public class WordleController {

    private static final Logger log = LogManager.getLogger(WordleController.class);

    @Autowired
    WordleService service;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/wordle/start", produces = "application/json")
    public ResponseEntity<Object> getHiddenWord() {
        int number = service.randomNumber();
        log.debug("Wordle started, sent number " + number);
        return new ResponseEntity<>(number, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/wordle/{index}/{guess}", produces = "application/json")
    public ResponseEntity<Object> userGuess(@PathVariable int index, @PathVariable String guess) {
        log.debug("index = " + index + " and guess = " + guess);
        OutputWord ow = null;
        if(index >= 0 && !guess.isEmpty()){
            ow = service.checkGuess(index, guess);
            if(!Objects.isNull(ow)){
                return new ResponseEntity<>(ow, HttpStatus.OK);
            }
            return new ResponseEntity<Object>(ow, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<Object>(ow, HttpStatus.BAD_REQUEST);
        }
    }

}
