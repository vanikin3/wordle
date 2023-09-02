package com.example.wordle.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.example.wordle.POJO.Word;

@Component
public class Dictionary {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    ResourceLoader resourceLoader;

    private ArrayList<Word> dictionary = new ArrayList<>();

    public Dictionary() {
        readFile();
    }

    public Word getWord(int index) {
        return dictionary.get(index);
    }

    public int getSize() {
        return dictionary.size();
    }

    private void readFile() {
        try {
            Resource resource = new ClassPathResource("words.txt");
            File filename = resource.getFile();
            Scanner scan = new Scanner(filename);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.length() == 5) {
                    dictionary.add(new Word(line));
                }
            }
            scan.close();
        } catch (IOException e) {
            log.error("An error occurred reading the words.txt file.");
            e.printStackTrace();
        }
    }

}
