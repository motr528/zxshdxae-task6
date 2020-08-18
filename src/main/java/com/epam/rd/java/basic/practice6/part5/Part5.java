package com.epam.rd.java.basic.practice6.part5;

public class Part5 {
    
    public static void main(String[] args) {

        Tree<Integer> tree = new Tree<>();

        tree.add(5);
        tree.add(2);
        tree.add(12);
        tree.add(-4);
        tree.add(3);
        tree.add(9);
        tree.add(21);
        tree.add(19);
        tree.add(25);

        tree.remove(12);

        tree.print();

    }
    
}
