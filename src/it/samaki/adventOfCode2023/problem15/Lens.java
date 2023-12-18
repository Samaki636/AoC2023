package it.samaki.adventOfCode2023.problem15;

/**
 * @author : Samaki01
 **/
public record Lens(String label, int focalLength) {
    @Override
    public String toString() {
        return "[" + label + " " + focalLength + "]";
    }
}
