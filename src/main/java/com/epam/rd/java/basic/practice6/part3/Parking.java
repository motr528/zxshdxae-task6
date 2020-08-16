package com.epam.rd.java.basic.practice6.part3;

import java.util.Arrays;

public class Parking {

    private int[] places;

    public Parking(int capacity) {
        places = new int[capacity];
    }

    public int[] getPlaces() {
        return places;
    }

    public boolean arrive(int k) {
        boolean isParked;

        if (k > places.length - 1 || k < 0) throw new IllegalArgumentException();

        if (places[k] == 0) {
            places[k] = 1;
            return true;
        }

        if (k != places.length - 1) {
            isParked = goRight(k);
            if (!isParked) {
                isParked = goLeft(k);
            }
        } else {
            isParked = goLeft(k);
        }

        return isParked;
    }

    private boolean goRight(int k) {
        boolean isParked = false;

        for (int i = k + 1; i < places.length; i++) {
            if (places[i] == 0) {
                places[i] = 1;
                isParked = true;
                break;
            }
        }

        return isParked;
    }

    private boolean goLeft(int k) {
        boolean isParked = false;

        for (int i = 0; i < k; i++) {
            if (places[i] == 0) {
                places[i] = 1;
                isParked = true;
                break;
            }
        }

        return isParked;
    }

    public boolean depart(int k) {
        boolean isParked = false;

        if (k > places.length - 1 || k < 0) throw new IllegalArgumentException();

        if (places[k] == 1) {
            places[k] = 0;
            isParked = true;
        }

        return isParked;
    }

    public void print() {

        Arrays.stream(places).forEach(System.out::print);
        System.out.println();

    }
}
