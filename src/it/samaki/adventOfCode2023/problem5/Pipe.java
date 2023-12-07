package it.samaki.adventOfCode2023.problem5;

/**
 * @author : Samaki
 **/
public class Pipe implements Comparable {
    private final long inputStart;
    private final long inputEnd;
    private final long range;
    private final long outputStart;
    private final long outputEnd;

    public Pipe(long inputStart, long range, long outputStart) {
        this.inputStart = inputStart;
        this.inputEnd = inputStart + range;
        this.range = range;
        this.outputStart = outputStart;
        this.outputEnd = outputStart + range;
    }

    public long process(Bucket bucket) {
        if (bucket.start() >= inputStart && bucket.start() < this.inputStart + bucket.range()) {
            return outputStart;
        }
        else return inputStart;
    }

    @Override
    public int compareTo(Object o) {
        return Long.compare(outputStart, ((Pipe) o).getOutputStart());
    }

    public long getInputStart() {
        return inputStart;
    }

    public long getInputEnd() {
        return inputEnd;
    }

    public long getRange() {
        return range;
    }

    public long getOutputStart() {
        return outputStart;
    }

    public long getOutputEnd() {
        return outputEnd;
    }
}
