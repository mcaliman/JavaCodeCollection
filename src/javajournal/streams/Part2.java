package javajournal.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Part2 {

    public static void main(String[] args) {
        //Parte 2 - mettiamo un pò di cose insieme
        IntStream stream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
        int result = stream.limit(5).filter(u -> (u % 2 == 0)).map(u -> u + 1).reduce((u, v) -> (u + v)).getAsInt();
        System.out.println("result=" + result);
        //Il risultato è 9, vediamo cosa abbiamo fatto leggendo letteralmente il codice
        //1.Abbiamo creato uno stream di elementi interi da [1,19]
        //2.Abbiamo preso (limitato) ai primi 5 elementi lo stream [1,2,3,4,5]
        //3.Abbiamo filtrato lo stream con la lambda u -> u%2 ==0 , cioè agli elementi divisibili per 2 (2,4)
        //4.Abbiamo "mappato" ogni elemento con la lambda u -> u + 1, e cioè incrementato di un uno 
        //o più matematicamente parlando abbiamo di fatto applicato la funzione successore! (3,5)
        //Abbiamo applicato la riduzione sommando due a due gli elementi. = 8
        //Abbiamo poi preso il risultato con getAsInt (dato che è un IntStream non avevamo il generico metodo get()

        //Vediamo una soluzione iterativa
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
        //Ok non è la migliore e la più compatta delle possibili, ma è cmq già ottimizzata (non ho creato una lista
        //nuova dalle prime 5 ne una che conteneva il successore dell'elemento. L'esempio è facile, non ho impiegato
        //molto ne a scriverlo ne si impiegherebbe molto a capirlo, ma abbiamo comunque dovuto leggere (e prima scrivere) 
        //7 righe di codice
        //Immaginate su problemi più compessi 
        
        //E ora qualcosa di veramente interesante.
        //Riprendiamo il codice che utilizza lo stream  
        stream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
        result = stream.limit(5).filter(u -> (u % 2 == 0)).map(u -> u + 1).reduce((u, v) -> (u + v)).getAsInt();
        System.out.println("result=" + result);
        //Possiamo notare che alcune operazioni come il filtraggio o selezione degli elementi pari e l'applicazione
        //del successore o incremento di una unità si sarebbero potute fare in parallelo, e questo è possibile
        //semplicemente "trasformando" il nostro stream da sequenziale a parallelo, come ? Applicando parallel()
        stream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
        result = stream.parallel().limit(5).filter(u -> (u % 2 == 0)).map(u -> u + 1).reduce((u, v) -> (u + v)).getAsInt();
        System.out.println("result=" + result);
        //Di fatto la concorrenza a costo zero o quasi, meglio non mi sono dovuto preoccupare di come parallelizzare
        //il codice. Attenzione parallelizzare non sempre è un idea buona, anche se hai più di un core non è detto 
        //che i tempi migliorino, anzi a volte peggiorano, e non è assolutamente questo il caso in cui avremo dei benefici
        //Quindi la regola è provare,provare e provare.
        //Ho realizzato un piccolo esempio basato su elaborazioni su numeri primi in cui in piccolo vantaggio sono riuscito
        //a ottenerlo, facendo riferimento al mio i7, circa la meta del tempo con la versione parallela.
        
        
        
    }

}
