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
        /*
        Introduzione gentile agli stream e alle espressioni lambda in Java 8
        */
        Stream stream = Stream.of(1, 2, 3, 4, 4);
        System.out.println("Stampo lo stream.");
        stream.forEach(e -> System.out.print(e + " "));
        //Questo sotto da stream has already been operated upon or closed
        //Si. gli stream si consumano quindi le operazioni vanno effettuate in maniera diversa da come siamo abitutati con le collezioni

        stream = Stream.of(1, 2, 3, 4, 4);
        System.out.println("\nStampo lo stream dopo aver applicato distinct.");
        stream.distinct().forEach(e -> System.out.print(e + " "));
        System.out.println("\nNon trovero l'elemento 4 ripetuto due volte, il concetto è lo stesso di DISTINCT in SQL.");

        //Contare il numero di elementi in uno stream
        stream = Stream.of(1, 2, 3, 4, 4);
        long count = stream.count();
        System.out.println("\nCalcolo il numero di elementi dello stream. " + count);

        //Estrarre un elemento qualsiasi dal uno stream
        stream = Stream.of(1, 2, 3, 4, 4);
        Optional anyElement = stream.findAny();
        if (anyElement.isPresent()) {
            Object element = anyElement.get();
            System.out.println("And the winner is: " + element);
        } else {
            System.out.println("Stream is empty.");
        }
        //Anche qui non possiamo rilanciare un altra volta il metodo o otterremo una IllegalStateException : stream has already been operated upon or closed
        //Se controlliamo il numero di elementi chiamando count stessa eccezione.

        //Otteniamo il primo elemento
        stream = Stream.of(1, 2, 3, 4, 4);
        Optional findFirst = stream.findFirst();
        if (findFirst.isPresent()) {
            Object element = findFirst.get();
            System.out.println("The number one is: " + element);
        } else {
            System.out.println("Stream is empty.");
        }

        //limitiamo il numero di elementi di uno stream
        stream = Stream.of(1, 2, 3, 4, 4);
        Stream streamlimited = stream.limit(2);
        System.out.println("Stream limit(2).");
        streamlimited.forEach(e -> System.out.print(e + " "));

        //Saltiamo n elementi e torniamo lo stream risultante
        stream = Stream.of(1, 2, 3, 4, 4);
        Stream streamskipped = stream.skip(2);//salta i primi 2 elementi e torna lo stream conseguente
        System.out.println("Stream skip(2).");
        streamskipped.forEach(e -> System.out.print(e + " "));
        //otteniamo come prevedibile 3,4,4

        //ordiniamo uno stream
        stream = Stream.of(1, 3, 2, 4, 4);
        System.out.println("\nStream sorted.");
        stream.sorted().forEach(e -> System.out.print(e + " "));//ordina secondo l'ordine naturale

        //in quest'ultimo esempio abbiamo già cominciato ad utilizzare gli stream come in realtà si fa
        //abbiamo applicato in cascata 2 metodi: sorted e poi il forEach
        //Il disordine APPROFONDIRE NON FUNZIONA
        stream = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream unordered.");
        ((Stream) stream.sorted().unordered()).forEach(e -> System.out.print(e + " "));;//non ordinato

        //Nel mostarti il comportamento dei metodi visti fino a qui ho usato il metodo forEach per stampare
        //gli elementi di uno stream, il comportamento era piuttosto intuitivo, ma è giunto il momento di entrare
        //nel dettaglio di forEach: il metodo prende come argomento un Consumer, la lambda e->System.out.println(e+"")
        //è ovviamente un Consumer. Quando utilizzato sopra per stampare a console si potrebbe scrivere anche in questo modo
        stream = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream + forEach con Consumer.");
        stream.forEach(new Consumer() {
            @Override
            public void accept(Object t) {
                System.out.print(t + " ");
            }
        });
        //esiste anche una variante di forEach per processare in modo ordinato gli elementi di uno stream
        stream = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream + forEachOrderd con Consumer.");
        stream.forEachOrdered(new Consumer() {
            @Override
            public void accept(Object t) {
                System.out.print(t + " ");
            }
        });
        //Non noterete differenze dato che entrambi gli stream avevao un ordine, di questo parleremo in seguito
        //Riguardo il metodo sorted il metodo è disponibile anche nella variante che accetta un Comparator classico
        stream = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream sorted 2.");
        stream.sorted(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Integer) o1).compareTo((Integer) o2);
            }
        }).forEach(e -> System.out.print(e + " "));;//non ordinato

        //NO stream = Stream.of(1, 3, 2, 4, 5);
        //NO System.out.println("\nStream sorted 2.");
        //NO stream.sorted( (e1,e2) -> Integer.compare(e1,e2) ).forEach(e -> System.out.print(e + " "));;//non ordinato
        //puoi usare forme semplificate come sotto
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

        //Filtriamo elementi da uno stream
        //fino ad ora abbiamo visto come ottenere elementi da uno stream ordinarlo e applicare funzioni ad ogni elemento con le lambda
        //per filtrare gli elementi, quindi effettuare una selezione in base a regole possiamo utilizzare filter
        stream = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream filtra pari.");
        stream.filter(new Predicate() {
            @Override
            public boolean test(Object t) {
                return ((Integer) t) % 2 == 0;
            }
        }).forEach(e -> System.out.print(e + " "));

        //bello usare classi anonime di predicati, e a volte questo è necessario, la si può fare anche con le lambda
        //in maniera più concisa e leggibile (e compatta)
        Stream<Integer> stream1 = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream filtra pari 2.");
        stream1.filter(
                t -> ((t % 2) == 0)
        ).forEach(e -> System.out.print(e + " "));
        //Qui per semplificarci la vita e usare in modo natuale le lambda abbiamo definito lo stream come di Integer
        //

        //Map
        //Abbiamo visto come filtrare ordinare e se volessimo effettuare un operazione su ogni elemento tale da modificarne il valore?
        //Per esempio moltiplicarlo per 2
        stream1 = Stream.of(1, 3, 2, 4, 5);
        System.out.println("\nStream filtra pari 2.");
        stream1.map(
                t -> t * 2
        ).forEach(e -> System.out.print(e + " "));

        //Al metodo map abbiamo direttamente passato una lambda ma avremmo anche potuto 
        //fare qualcosa di più articolato
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

        //Qualcosa di più: Cosa c'è sotto il cofano
        //Abbiamo visto molti esempi ma praticamente nulla di teoria, per utlizzare correttamente e con produttività
        //gli stream è necessario capire come funzionano.
        //ora vediamo come operare una riduzione sullo stream, riduciamo da uno stream di n elementi ad uno solo
        //risultato di una certa operazione binaria da applicare sugli elementi 
        stream1 = Stream.of(1, 3, 2, 4, 5);
        int result = stream1.reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer t, Integer u) {
                return t + u;
            }
        }).get();
        System.out.println("usa la riduzione per fare 1+3+2+4+5=" + result);
        //Anche qui potevo usare le lambda expression
        stream1 = Stream.of(1, 3, 2, 4, 5);
        result = stream1.reduce((u, v) -> (u + v)).get();
        System.out.println("usa la riduzione per fare 1+3+2+4+5=" + result);
        
        //Ricapitolando abbiamo visto operazioni (metodi) terminali e non terminali. che si applicano ai singoli elementi
        //per dar luogo a un nuovo stream e operazioni di riduzione che a partire da uno stream di n elementi lo riducono a uno solo.
        //Gli strumenti di base li abbiamo tutti.
        
        //Con questi strumenti già possiamo fare molto e sopratutto scrivere codice molto più leggibile e in modo dichiarativo invece che interattivo
        //pensate alle operazioni di esempio viste sopra e come le averete scritte utilizzando dei for e degli if per esempio.
        
    }

}
