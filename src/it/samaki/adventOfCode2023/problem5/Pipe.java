package it.samaki.adventOfCode2023.problem5;

/**
 * @author : Samaki
 **/
public class Pipe implements Comparable {
    private final long inputStart;
    private final long inputEnd;
    private final long range;
    private final long outputStart;

    public Pipe(long inputStart, long range, long outputStart) {
        this.inputStart = inputStart;
        this.inputEnd = inputStart + range;
        this.range = range;
        this.outputStart = outputStart;
    }

    public long process(Bucket bucket) {
        if (bucket.start() >= inputStart && bucket.start() < this.inputStart + bucket.range()) {
            return outputStart;
        }
        else return inputStart;
    }

    @Override
    public int compareTo(Object o) {
        return Long.compare(inputStart, ((Pipe) o).getInputStart());
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
}
