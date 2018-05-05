package javajournal.streams;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Part1 {

    public static void main(String[] args) {
        
        Stream stream = Stream.of(1, 2, 3, 4, 4);
        System.out.println("Stampo lo stream.");
        stream.forEach(e -> System.out.print(e + " "));
        
        stream = Stream.of(1, 2, 3, 4, 4);
        System.out.println("\nStampo lo stream dopo aver applicato distinct.");
        stream.distinct().forEach(e -> System.out.print(e + " "));
        System.out.println("\nNon trovero l'elemento 4 ripetuto due volte, il concetto è lo stesso di DISTINCT in SQL.");
        
        stream = Stream.of(1, 2, 3, 4, 4);
        long count = stream.count();
        System.out.println("\nCalcolo il numero di elementi dello stream. " + count);


        stream = Stream.of(1, 2, 3, 4, 4);
        Optional anyElement = stream.findAny();
        if (anyElement.isPresent()) {
            Object element = anyElement.get();
            System.out.println("And the winner is: " + element);
        } else {
            System.out.println("Stream is empty.");
        }

        stream = Stream.of(1, 2, 3, 4, 4);
        Optional findFirst = stream.findFirst();
        if (findFirst.isPresent()) {
            Object element = findFirst.get();
            System.out.println("The number one is: " + element);
        } else {
            System.out.println("Stream is empty.");
        }

        stream = Stream.of(1, 2, 3, 4, 4);
        Stream streamlimited = stream.limit(2);
        System.out.println("Stream limit(2).");
        streamlimited.forEach(e -> System.out.print(e + " "));

        stream = Stream.of(1, 2, 3, 4, 4);
        Stream streamskipped = stream.skip(2);
        System.out.println("Stream skip(2).");
        streamskipped.forEach(e -> System.out.print(e + " "));

        stream = Stream.of(1, 3, 2, 4, 4);
        System.out.println("\nStream sorted.");
        stream.sorted().forEach(e -> System.out.print(e + " "));

        stream = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream + forEach con Consumer.");
        stream.forEach(new Consumer() {
            @Override
            public void accept(Object t) {
                System.out.print(t + " ");
            }
        });

        stream = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream + forEachOrderd con Consumer.");
        stream.forEachOrdered(new Consumer() {
            @Override
            public void accept(Object t) {
                System.out.print(t + " ");
            }
        });

        stream = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream sorted 2.");
        stream.sorted(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Integer) o1).compareTo((Integer) o2);
            }
        }).forEach(e -> System.out.print(e + " "));;//non ordinato

        stream = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream sorted reverse.");
        stream.sorted(Comparator.reverseOrder()).forEach(e -> System.out.print(e + " "));

        stream = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream sorted x.");
        stream.sorted(Comparator.comparing(Integer::doubleValue)).forEach(e -> System.out.print(e + " "));

        stream = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream sorted x.");
        Function<Integer, Integer> keyExtractor = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t.intValue() + 1;
            }
        };
        stream.sorted(Comparator.comparing(keyExtractor)).forEach(e -> System.out.print(e + " "));

        stream = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream filtra pari.");
        stream.filter(new Predicate() {
            @Override
            public boolean test(Object t) {
                return ((Integer) t) % 2 == 0;
            }
        }).forEach(e -> System.out.print(e + " "));

        Stream<Integer> stream1 = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream filtra pari 2.");
        stream1.filter(
                t -> ((t % 2) == 0)
        ).forEach(e -> System.out.print(e + " "));

        stream1 = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream filtra pari 2.");
        stream1.map(
                t -> t * 2
        ).forEach(e -> System.out.print(e + " "));

        stream1 = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream filtra pari 2.");
        stream1.map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t * 2;
            }

            @Override
            public <V> Function<V, Integer> compose(Function<? super V, ? extends Integer> before) {
                return Function.super.compose(before); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <V> Function<Integer, V> andThen(Function<? super Integer, ? extends V> after) {
                return Function.super.andThen(after); //To change body of generated methods, choose Tools | Templates.
            }

        }).forEach(e -> System.out.print(e + " "));
        
        stream1 = Stream.of(1, 3, 2, 4, 5);
        int result = stream1.reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer t, Integer u) {
                return t + u;
            }
        }).get();
        System.out.println("usa la riduzione per fare 1+3+2+4+5=" + result);

        stream1 = Stream.of(1, 3, 2, 4, 5);
        result = stream1.reduce((u, v) -> (u + v)).get();
        System.out.println("usa la riduzione per fare 1+3+2+4+5=" + result);
        
    }

}
