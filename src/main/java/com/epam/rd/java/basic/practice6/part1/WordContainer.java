package com.epam.rd.java.basic.practice6.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class WordContainer {

    private static final Logger logger = Logger.getLogger(WordContainer.class.getName());
    private static final String EXCEPTION_OCCURED = "Exception: ";

    private static Set<Word> words = new HashSet<>();

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    public static void main(String[] args) {

        List<String> input = getInput();

        for (String str : input) {
            for (String subStr : str.split(" ")) {
                getWordAndAddToSet(subStr);
            }
        }
        words = arrangeOutput(words);

        words.forEach(System.out::println);
    }

    public static List<String> getInput() {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String s;

            while (true) {
                s = br.readLine();
                if (s.contains("stop")) {
                    if (!s.equals("stop")) {
                        lines.add(s.split("stop")[0]);
                    }
                    break;
                }
                lines.add(s);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> EXCEPTION_OCCURED + e.getMessage());
        }
        return lines.stream().filter(el -> el != null && !el.trim().isEmpty()).collect(Collectors.toList());
    }

    public static void getWordAndAddToSet(String input) {
        Word word = new Word(input);

        boolean exists = false;

        for (Word w : words) {
            if (w.getContent().equals(word.getContent())) {
                w.increaseFrequency();
                exists = true;
            }
        }

        if (!exists) {
            word.increaseFrequency();
            words.add(word);
        }
    }

    public static Set<Word> arrangeOutput(Set<Word> set) {
        return set.stream().sorted(Word::compareTo).collect(Collectors.toCollection(LinkedHashSet::new));
    }

}
