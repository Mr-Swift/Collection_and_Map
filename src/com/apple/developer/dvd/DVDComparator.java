package com.apple.developer.dvd;

import java.util.Comparator;

@SuppressWarnings("all")
public class DVDComparator implements Comparator<DVDSet> {

    public int compare(DVDSet o1, DVDSet o2) {
        if (o1.getCounts() < o2.getCounts()) {
            return 1;
        } else if (o1.getCounts() > o2.getCounts()) {
            return -1;
        } else {
            return (int) (o1.getCounts() - o2.getCounts());
        }
    }

}
