package it.samaki.adventOfCode2023.problem15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author : Samaki01
 **/
@SuppressWarnings("unchecked")
public class Problem15 {
    public static void main(String[] args) throws FileNotFoundException {
        String tmp;
        try (Scanner scanner = new Scanner(new File("res/it/samaki/adventOfCode2023/problem15/test2.txt"))) {
            tmp = scanner.nextLine();
        }
        String[] input = tmp.split(",");

        int totalFocusingPower = 0;
        final int N_BOXES = 256;
        LinkedList<Lens>[] boxes = new LinkedList[N_BOXES];
        for (int i = 0; i < boxes.length; i++)
            boxes[i] = new LinkedList<>();

        for (String s : input) {
            char operation;
            if (s.contains("=")) operation = '=';
            else operation = '-';

            //line[0] = lens label, line[1] = focal length
            String[] line = s.split("[=-]");

            int boxN = hash(line[0]);
            LinkedList<Lens> box = boxes[boxN];

            if (line.length == 2)
                System.out.println("Operation: " + boxN + " " + operation + " " + line[0] + " " + line[1]);
            else
                System.out.println("Operation: " + boxN + " " + operation + " " + line[0]);

            System.out.println("Box n " + boxN + " before : " + boxes[boxN]);
            //use the operation on the box
            if (operation == '=') {
                Lens newLens = new Lens(line[0], Integer.parseInt(line[1]));
                if (box.isEmpty()) {
                    box.add(newLens);
                } else {
                    for (Lens l : box) {
                        if (l.label().equals(line[0])) {
                            box.set(box.indexOf(l), newLens);
                            break;
                        }
                        else if (box.indexOf(l)+1 == box.size()) {
                            box.add(newLens);
                            break;
                        }
                    }
                }
            } else
                box.removeIf(l -> l.label().equals(line[0]));
            System.out.println("Box n " + boxN + " after: " + boxes[boxN]);
        }

        //check lenses installation
        for (int i = 0; i < boxes.length; i++) {
            System.out.println(boxes[i]);
            for (int j = 0; j < boxes[i].size(); j++) {
                totalFocusingPower += (i+1) * (j+1) * boxes[i].get(j).focalLength();
            }
        }

        System.out.println("The total focusing power of all the lenses is: " + totalFocusingPower);
    }

    private static int hash(String line) {
        int currentValue = 0;
        for (char c : line.toCharArray()) {
            if (c == '\n') continue;
            currentValue += c;
            currentValue *= 17;
            currentValue %= 256;
        }
        return currentValue;
    }
}
//442216 244199