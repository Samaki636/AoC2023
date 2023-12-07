package it.samaki.adventOfCode2023.problem5;

import java.util.Comparator;

/**
 * @author : Samaki
 **/
public class PipeComparator implements Comparator<Pipe> {
    @Override
    public int compare(Pipe pipe1, Pipe pipe2) {
        return pipe1.compareTo(pipe2);
    }
}
