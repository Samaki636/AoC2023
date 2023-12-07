package it.samaki.adventOfCode2023.problem7;

import java.util.Comparator;

/**
 * @author : Samaki01
 **/
public class HandsComparator implements Comparator<Hand> {
    @Override
    public int compare(Hand hand1, Hand hand2) {
        return hand1.compareTo(hand2);
    }
}
