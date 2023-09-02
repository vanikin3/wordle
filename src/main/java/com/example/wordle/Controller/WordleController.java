package com.example.wordle.Controller;

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

@RestController
public class WordleController {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    WordleService service;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "wordle/start", produces = "application/json")
    public ResponseEntity<Object> getHiddenWord() {
        return new ResponseEntity<>(service.randomNumber(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "wordle/{index}/{guess}", produces = "application/json")
    public ResponseEntity<Object> userGuess(@PathVariable int index, @PathVariable String guess) {
        log.debug("index = " + index + " and guess = " + guess);
        return new ResponseEntity<>(service.checkGuess(index, guess), HttpStatus.OK);
    }

}
