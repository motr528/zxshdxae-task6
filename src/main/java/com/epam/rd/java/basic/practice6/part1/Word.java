package com.epam.rd.java.basic.practice6.part1;

import java.util.Comparator;

public class Word implements Comparable<Word> {

	private String content;
	
	private int frequency;

    public Word(String content) {
        this.content = content;
        frequency = 0;
    }

    public void increaseFrequency() {
        frequency++;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int compareTo(Word o) {
        int num =  - this.frequency + o.frequency;

        if (num == 0) {
            num = this.content.compareTo(o.content);
        }
        return num;
    }

    @Override
    public String toString() {
        return content + " : " + frequency;
    }
}
