package com.epam.rd.java.basic.practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {

    private static final int LIMIT = 10000;

    public static void main(String[] args) {
        ArrayList<Integer> list = Stream.iterate(0, x -> x + 1).limit(LIMIT)
                .collect(Collectors.toCollection(ArrayList::new));

        LinkedList<Integer> linkedList = Stream.iterate(0, x -> x + 1).limit(LIMIT)
                .collect(Collectors.toCollection(LinkedList::new));

        getOutputForIndexRemoval(list,linkedList);
        getOutputForIteratorRemoval(list,linkedList);

    }

    public static long removeByIndex(final List<Integer> list, final int k) {

        long start = System.currentTimeMillis();

        int counter = 0;
        int pointer = 0;
        while (list.size() > 1) {
            ++counter;
            if (pointer > list.size() - 1) {
                pointer = 0;
            }

            if (counter % k == 0 && counter != 0) {
                list.remove(pointer);
                counter = 0;
            } else {
                pointer++;
            }
        }

        long end = System.currentTimeMillis();
        return end - start;
    }


    public static long removeByIterator(final List<Integer> list, int k) {
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = list.iterator();
        int count = 0;
        while (list.size() > 1) {
            ++count;
            iterator.next();
            if (count % k == 0) {
                iterator.remove();
            }
            if (!iterator.hasNext()) {
                iterator = list.iterator();
            }
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void getOutputForIndexRemoval (List<Integer> arrayList, List<Integer> linkedList) {
        System.out.println(String.format("ArrayList#Index: %d",removeByIndex(new ArrayList<>(arrayList),4)));
        System.out.println(String.format("LinkedList#Index: %d",removeByIndex(new LinkedList<>(linkedList),4)));
    }

    public static void getOutputForIteratorRemoval (List<Integer> arrayList, List<Integer> linkedList) {
        System.out.println(String.format("ArrayList#Iterator: %d",removeByIterator(new ArrayList<>(arrayList),4)));
        System.out.println(String.format("LinkedList#Iterator: %d",removeByIterator(new LinkedList<>(linkedList),4)));
    }
}
