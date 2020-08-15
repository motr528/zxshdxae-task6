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

    private Set<Word> words = new HashSet<>();

    public static void main(String[] args) {

        WordContainer wordContainer = new WordContainer();

        List<String> input = wordContainer.getInput();

        for (String str : input) {
            for (String subStr : str.split(" ")) {
                wordContainer.getWordAndAddToSet(subStr);
            }
        }
        wordContainer.words = arrangeOutput(wordContainer.words);

        wordContainer.words.forEach(System.out::println);
    }

    public List<String> getInput() {
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

    public void getWordAndAddToSet(String input) {
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
