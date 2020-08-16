package com.epam.rd.java.basic.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {

    private Node head;
    private final int numOfElements;


    public Range(int n, int m) {
        this(n, m, false);
    }

    public Range(int firstBound, int secBound, boolean reversedOrder) {
        numOfElements = secBound - firstBound + 1;
        if (!reversedOrder) {
            head = new Node(firstBound, null);
            Node buf = head;
            for (int i = firstBound + 1; i <= secBound; i++) {
                Node node = new Node(i, null);

                if (buf.next == null) {
                    buf.next = node;
                    buf = buf.next;
                }
            }
        } else {
            head = new Node(secBound, null);
            Node buf = head;
            for (int i = secBound - 1; i >= firstBound; i--) {
                Node node = new Node(i, null);

                while (true) {
                    if (buf.next == null) {
                        buf.next = node;
                        buf = buf.next;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl();
    }

    private final class IteratorImpl implements Iterator<Integer> {

        private Node start = head;
        private int position;


        @Override
        public boolean hasNext() {
            return position < numOfElements;
        }

        @Override
        public Integer next() {
            if (position > numOfElements) throw new NoSuchElementException();
            ++position;
            Integer current = start.value;
            start = start.next;
            return current;
        }

    }

    private static class Node {
        private Integer value;
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }


}
