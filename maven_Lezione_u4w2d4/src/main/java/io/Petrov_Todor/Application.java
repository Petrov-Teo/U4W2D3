package io.Petrov_Todor;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {


    public static void main(String[] args) {


        Supplier<User> userSupplier = () -> {
            Faker f = new Faker(Locale.ITALY);
            Random rnd = new Random();

            return new User(f.lordOfTheRings()
                    .character(), f.name()
                    .lastName(), rnd.nextInt(0, 100),
                    f.lordOfTheRings().location());

        };
        List<User> usersList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            usersList.add(userSupplier.get());

        }

        usersList.forEach(System.out::println);


        //**************************************************** COLLECTORS ********************************************
        System.out.println("----------------------------------------- STAMPA DI UN COLLECTORS -----------------------------");

        // 1. Raggruppare gli utenti per Città
        System.out.println("---------------------------------------  1. Raggruppare gli utenti per Città ------------------------------");
        Map<String, List<User>> userByCity = usersList.stream().filter(user -> user.getEta() >= 18).collect(Collectors.groupingBy(User::getCitta));
        userByCity.forEach((citta, userList) -> System.out.println("città: " + citta + ", " + userList));

        // 2. Raggruppare gli utenti per età
        System.out.println("--------------------------------------- 2. Raggruppare gli utenti per età ------------------------------");
        Map<Integer, List<User>> userByAge = usersList.stream().collect(Collectors.groupingBy(User::getEta));
        userByAge.forEach((eta, userlist) -> System.out.println("l'età: " + eta + ", " + userlist));

        // 3. Concateniamo tutti i nomi e cognomi
        System.out.println("--------------------------------------- 3. Concateniamo tutti i nomi e cognomi ------------------------------");
        String nomiECognomi = usersList.stream().map(user -> user.getNome() + " " + user.getCognome()).collect(Collectors.joining(", "));
        System.out.println(nomiECognomi);

        // 4. Concateniamo tutte le età
        System.out.println("--------------------------------------- 4. Concateniamo tutte le età ------------------------------");
        String etaConcatenate = usersList.stream().map(user -> String.valueOf(user.getEta())).collect(Collectors.joining(", "));
        System.out.println(etaConcatenate);

        // 5. Calcolo media dell'età
        System.out.println("---------------------------------------  5. Calcolo media dell'età  ------------------------------");
        double average = usersList.stream().collect(Collectors.averagingInt(User::getEta));
        System.out.println("la media è: " + average);

        // 6. Somma dell'età
        System.out.println("---------------------------------------  6. Somma dell'età  ------------------------------");
        int sommaEta = usersList.stream().collect(Collectors.summingInt(User::getEta));
        System.out.println("la somma dell'età è: " + sommaEta);

        // 7. Raggruppiamo per città e calcoliamo l'età media per ognuna di essa
        System.out.println("--------------------------------------- 7. Raggruppiamo per città e calcoliamo l'età media per ognuna di essa ------------------------------");
        Map<String, Double> averageAgePerCity = usersList.stream().collect(Collectors.groupingBy(User::getCitta, Collectors.averagingInt(User::getEta)));
        averageAgePerCity.forEach((citta, avarage) -> System.out.println("città: " + citta + ", " + avarage));

        // 8. Raggruppiamo per città e otteniamo statistiche tipo media età, età più alta, più bassa, ecc ecc.
        System.out.println("--------------------------------------- 8. Raggruppiamo per città e otteniamo statistiche tipo media età, età più alta, più bassa, ecc ecc. ------------------------------");

        Map<String, IntSummaryStatistics> statsPerCity = usersList.stream().collect(Collectors.groupingBy(User::getCitta, Collectors.summarizingInt(User::getEta)));
        statsPerCity.forEach((citta, stats) -> System.out.println("città: " + citta + ", " + stats));

        //**************************************************** COMPARATORS ********************************************
        //1. Ordiniamo la lista utenti per età (Ordine crescente)
        System.out.println("--------------------------------------- 1. Ordiniamo la lista utenti per età (Ordine crescente)------------------------------");
        List<User> userSortedByAge = usersList.stream().sorted(Comparator.comparingInt(User::getEta)).toList();
        userSortedByAge.forEach(System.out::println);

        //1. Ordiniamo la lista utenti per età (Ordine decrescente)
        System.out.println("--------------------------------------- 1. Ordiniamo la lista utenti per età (Ordine decrescente)------------------------------");
        List<User> userSortedByAgeDec = usersList.stream().sorted(Comparator.comparingInt(User::getEta).reversed()).toList();
        userSortedByAgeDec.forEach(System.out::println);

        //2. Ordiniamo la lista utenti per cognome
        System.out.println("---------------------------------------  2. Ordiniamo la lista utenti per cognome ------------------------------");

        List<User> ordinatiPerCognome = usersList.stream().sorted(Comparator.comparing(User::getCognome)).toList();
        ordinatiPerCognome.forEach(System.out::println);

        //**************************************************** LIMIT ********************************************
        //1. Ordiniamo la lista utenti per età (Ordine decrescente) dei primi 5
        System.out.println("--------------------------------------- 1. Ordiniamo la lista utenti per età (Ordine decrescente) dei primi 5 ------------------------------");

        List<User> fiveUserSortedByAgeDec = usersList.stream().sorted(Comparator.comparingInt(User::getEta).reversed()).limit(5).toList();
        fiveUserSortedByAgeDec.forEach(System.out::println);

        //**************************************************** MAP TO ********************************************

        // 1.Calcolo della somma delle età tramite reduce
        System.out.println("--------------------------------------- 1.Calcolo della somma delle età tramite reduce ------------------------------");
        int totalAge = usersList.stream().map(User::getEta).reduce(0, (acc, age) -> acc + age);
        // int totalAge = usersList.stream().map(User::getEta).reduce(0, Integer::sum); -> un'altro metodo alternativo
        System.out.println("la somma totale è: " + totalAge);

        // 2.Calcolo della somma delle età tramite mapToInt()
        System.out.println("--------------------------------------- 2.Calcolo della somma delle età tramite mapToInt() ------------------------------");
        int totalAge2 = usersList.stream().mapToInt(user -> user.getEta()).sum();
        System.out.println("la somma totale è: " + totalAge2);

        // 3.Calcolo della media dell'età tramite mapToInt()
        System.out.println("--------------------------------------- 3.Calcolo della media dell'età tramite mapToInt() ------------------------------");

        OptionalDouble avar = usersList.stream().mapToInt(User::getEta).average();
        System.out.println("La media dell'età è: " + avar);

        // 4.Calcolo dell'età maggiore mapToInt()
        System.out.println("---------------------------------------4.Calcolo dell'età maggiore mapToInt() ------------------------------");
        OptionalInt maxAge = usersList.stream().mapToInt(User::getEta).max();
        if (maxAge.isPresent()) System.out.println("L'età massima è: " + maxAge.getAsInt());
        else System.out.println("Non è stato possibile trovare il più vecchio");

        // 4.Calcolo dell'età maggiore mapToInt()
        System.out.println("---------------------------------------4.Calcolo dell'età maggiore mapToInt() ------------------------------");
        IntSummaryStatistics stats = usersList.stream().mapToInt(User::getEta).summaryStatistics();
        System.out.println(stats);

    }

}
