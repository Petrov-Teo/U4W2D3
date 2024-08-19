package io.Petrov_Todor.exceptions;

public class StringNotValidException extends Exception {

    public StringNotValidException(String str) {
        super("La stringa inserita: " + str + "non è valida!");
    }

}
