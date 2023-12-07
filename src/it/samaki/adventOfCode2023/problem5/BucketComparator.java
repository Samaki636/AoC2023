package it.samaki.adventOfCode2023.problem5;

import java.util.Comparator;

/**
 * @author : Samaki
 **/
public class BucketComparator implements Comparator<Bucket> {
    @Override
    public int compare(Bucket bucket1, Bucket bucket2) {
        return Long.compare(bucket1.start(), bucket2.start());
    }
}
