import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
      /*  StringModifier dotsWrapper = str -> "... " + str + " ...";
        StringModifier starsWrapper = str -> "*** " + str + " ***";

        System.out.println(dotsWrapper.modify("Ciao"));
        System.out.println(starsWrapper.modify("Ciao"));

        StringModifier stringInverter = str -> {
            String[] splited = str.split("");
            String inverted = "";
            for (int i = splited.length - 1; i >= 0; i--) {
                inverted += splited[i];
            }
            return inverted;
        };
        System.out.println(stringInverter.modify("Ciao"));

        //******************************* PREDICATE ******************************************

        Predicate<Integer> isMoreThanZero = num -> num > 0;
        Predicate<Integer> isLessThanHundred = num -> num < 100;


        User aldo = new User("Aldo", "Pippino", 20);
        System.out.println(isMoreThanZero.test(aldo.getEta()));
        System.out.println(isMoreThanZero.and(isLessThanHundred).test(aldo.getEta()));

        //******************************* SUPPLIER ******************************************
        Supplier<Integer> randomNumberSupplier = () -> {
            Random rndm = new Random();

            return rndm.nextInt(1, 100000);
        };
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < 155; i++) {
            randomNumbers.add(randomNumberSupplier.get());
        }
        System.out.println(randomNumbers);

        Faker f = new Faker(Locale.ITALY);

        System.out.println(f.name().firstName());
        System.out.println(f.name().lastName());

        Supplier<User> userSupplier = () -> {
            return new User(f.lordOfTheRings().character(), f.name().lastName(), randomNumberSupplier.get());
        };


        Supplier<List<User>> randomListSupplier = () -> {
            List<User> users = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                users.add(userSupplier.get());
            }
            return users;
        };

        System.out.println(randomListSupplier.get());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(randomListSupplier.get());


        List<User> randomUsers = randomListSupplier.get();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        randomUsers.forEach(user -> System.out.println(user));
       */


        //******************************* STREAMS ******************************************
        Supplier<Integer> randomNumberSupplier = () -> {
            Random rndm = new Random();

            return rndm.nextInt(1, 100);
        };
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            randomNumbers.add(randomNumberSupplier.get());
        }
        System.out.println(randomNumbers);

        /*
        Stream<Integer> stream = randomNumbers.stream().filter(num -> num > 50 && num < 1000);
        stream.forEach(System.out::println);
        */
        randomNumbers.stream().filter(num -> num > 50 && num < 1000).forEach(System.out::println);
        // Gli Stream vanno sempre "aperti" utilizzando .stream(), poi posso fare tutte le operazioni INTERMEDIE che voglio,
        //tipo .filter() alla fine però per ottenere un risultato dovrò concludere lo Stream.
        // Concludere lo stream si può fare in più maniere, ad esempio .forEach può essere utile per concluderlo magari
        // stampando a video i risultati

        // Nei filter posso utilizzare anche dei predicati definiti in precedenza
        Predicate<Integer> isMoreThanZero = num -> num > 0;
        Predicate<Integer> isLessThanHundred = num -> num < 100;

        Predicate<User> isUserAgeMoreThan18 = user -> user.getEta() > 18;

        Faker f = new Faker(Locale.ITALY);
        Supplier<User> userSupplier = () -> new User(f.lordOfTheRings().character(), f.name().lastName(), randomNumberSupplier.get());

        Supplier<List<User>> randomListSupplier = () -> {
            List<User> users = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                users.add(userSupplier.get());
            }
            return users;
        };

        List<User> randomUsers = randomListSupplier.get();
        System.out.println(randomUsers);

        randomUsers.stream().filter(isUserAgeMoreThan18.negate()).forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        randomNumbers.stream().filter(isMoreThanZero.and(isLessThanHundred)).forEach(System.out::println);

        //******************************* STREAMS MAP ******************************************
        System.out.println("******************************* STREAMS MAP ******************************************");
        randomUsers.stream().map(User::getEta).forEach(System.out::println);
        //******************************* STREAMS MAP & FILTER ******************************************
        System.out.println("******************************* STREAMS MAP & FILTER ******************************************");
        randomUsers.stream().filter(isUserAgeMoreThan18.negate()).map(User -> User.getNome() + " " + User.getCognome() + " " + User.getEta()).forEach(System.out::println);

        //******************************* COME TERMINARE GLI STREAM ******************************************
        // Oltre a .forEach posso terminare gli Stream anche in maniere più utili, come ad esempio:
        // - reduce, quando voglio 'ridurre' una collezione dati ad un singolo valore, magari effettuando un qualche calcolo
        //- collect, oppure toList, per ricondurre lo Stream a una nuova lista
        //- matching, tramite allMatch e anyMatch posso testare le collezioni su una certa condizione (torna un booleano)

        System.out.println("******************************* STREAM  ******************************************");

        //******************************* STREAMS REDUCE ******************************************
        System.out.println("******************************* STREAMS REDUCE ******************************************");
        int totale = randomUsers.stream()
                .filter(isUserAgeMoreThan18.negate())
                .map(User -> User.getEta())
                .reduce(0, (partialSum, currentNumber) -> partialSum + currentNumber);
        System.out.println("IL totale dell'età di tutti i minorenni è: " + totale);

        //*******************************COME OTTENERE UNA LISTA DA UNO STREAMS ******************************************
        System.out.println("******************************* COME OTTENERE UNA LISTA DA UNO STREAMS******************************************");
        System.out.println("--------------------------------LISTA NUMERICA UTENTI MINORENNI-----------------------------------");
        List<Integer> listaEtaMinorenni = randomUsers.stream()
                .filter(isUserAgeMoreThan18.negate())
                .map(User -> User.getEta())
                .collect(Collectors.toList());
        System.out.println(listaEtaMinorenni);
        System.out.println("-------------------------------- UTENTI MINORENNI LISTA COMPLETA DI OGGETTI-----------------------------------");
        List<User> listaUtentiMinorenni = randomUsers.stream()
                .filter(isUserAgeMoreThan18.negate())
                .toList();
        System.out.println(listaUtentiMinorenni);

        System.out.println("--------------------------------NOMI UTENTI MINORENNI-----------------------------------");
        List<String> listaNomiMinorenni = randomUsers.stream()
                .filter(isUserAgeMoreThan18.negate()).map(User::getNome)
                .toList();

        listaNomiMinorenni.forEach(System.out::println);


        System.out.println("--------------------------------STREAMS - ALLMATCH & ANYMATCH-----------------------------------");
        //.some() e every() di JS corrispondono a anyMatch() e allMatch() di Java
        //Questi metodi ci consentono di controllare se ALMENTO UNO (.some e anyMatch) della lista passa una certa condizione, o se
        // TUTTI GLI ELEMENTI (.every(), allMatch()) passano una certa condizione.

        if (randomUsers.stream().allMatch(user -> user.getEta() >= 18))
            System.out.println("Tutti gli utenti sono maggiorenni");
        else System.out.println("Non tutti gli utenti sono maggiornenni");

        if (randomUsers.stream().anyMatch(user -> user.getEta() >= 99))
            System.out.println("Ci sono 99 enni!");
        else System.out.println("nessun 99 enne!");
        System.out.println("-------------------------------- DATE -----------------------------------");

        LocalDate today = LocalDate.now();
        System.out.println(today);

        System.out.println("la data di domani è :" + today.plusDays(1));
        System.out.println("la data di ieri era :" + today.minusDays(1));

        LocalDateTime ora = LocalDateTime.now();
        System.out.println(ora);

        LocalDate date = LocalDate.parse("2024-02-05");
        System.out.println(date);
        

    }

}