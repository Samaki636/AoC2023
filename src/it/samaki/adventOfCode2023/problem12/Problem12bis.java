package it.samaki.adventOfCode2023.problem12;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author : Samaki01
 **/
public class Problem12bis {
    public static void main(String[] args) throws FileNotFoundException {
        String cfg;
        String numbers;
        String[] nums;
        BigInteger sum = BigInteger.valueOf(0);
        HashMap<String, Long> cache = new HashMap<>();
        try (Scanner scanner = new Scanner(new File("./res/it/samaki/adventOfCode2023/problem12/test2.txt"))) {
            while (scanner.hasNextLine()) {
                String[] tmp = scanner.nextLine().split(" ");
                cfg = tmp[0];
                numbers = tmp[1];
                nums = numbers.split(",");
                int[] n = new int[nums.length];

                for (int i = 0; i < nums.length; i++)
                    n[i] = Integer.parseInt(nums[i]);

                cfg = cfg+"?"+cfg+"?"+cfg+"?"+cfg+"?"+cfg;
                int[] copyN = new int[n.length * 5];
                for (int i = 0; i < 5; i++)
                    System.arraycopy(n, 0, copyN, i * n.length, n.length);
                n = copyN;

                sum = sum.add(BigInteger.valueOf(count(cfg, n, cache)));
            }
        }
        System.out.println("The sum of possible arrangement counts is: " + sum);
    }

    public static long count(String cfg, int[] n, HashMap<String, Long> cache) {
        //no idea
        if (cfg.isEmpty()) {
            if (n.length == 0)
                return 1;
            else
                return 0;
        }
        if (n.length == 0) {
            if (cfg.contains("#"))
                return 0;
            else
                return 1;
        }
        //no idea ^

        String key = cfg + Arrays.toString(n);
        if (cache.containsKey(key))
            return cache.get(key);

        long result = 0;

        if (".?".contains(String.valueOf(cfg.charAt(0))))
            result += count(cfg.substring(1), n, cache);

        if ("#?".contains(String.valueOf(cfg.charAt(0))))
            if (n[0] <= cfg.length() && !cfg.substring(0, n[0]).contains(".")
                    && (n[0] == cfg.length() || cfg.charAt(n[0]) != '#')) {
                if (n[0] + 1 <= cfg.length())
                    result += count(cfg.substring(n[0] + 1), Arrays.copyOfRange(n, 1, n.length), cache);
                else
                    result += count("", Arrays.copyOfRange(n, 1, n.length), cache);
            }
        cache.put(key, result);
        return result;
    }
}
