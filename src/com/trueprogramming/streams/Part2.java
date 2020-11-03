package com.trueprogramming.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Part2 {

    public static void main(String[] args) {

        IntStream stream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
        int result = stream.limit(5).filter(u -> (u % 2 == 0)).map(u -> u + 1).reduce((u, v) -> (u + v)).getAsInt();
        System.out.println("result=" + result);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
        result = 0;
        for (int i = 0; i < 5; i++) {
            Integer e = list.get(i);
            if (e % 2 == 0) {
                e = e + 1;
                result += e;
            }
        }
        System.out.println("result iterativo:" + result);

        stream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
        result = stream.limit(5).filter(u -> (u % 2 == 0)).map(u -> u + 1).reduce((u, v) -> (u + v)).getAsInt();
        System.out.println("result=" + result);

        stream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
        result = stream.parallel().limit(5).filter(u -> (u % 2 == 0)).map(u -> u + 1).reduce((u, v) -> (u + v)).getAsInt();
        System.out.println("result=" + result);

    }

}
