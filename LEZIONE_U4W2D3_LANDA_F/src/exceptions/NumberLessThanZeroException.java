package exceptions;

public class NumberLessThanZeroException extends RuntimeException {

    public NumberLessThanZeroException(int num) {
        super("il numero inserito:" + num + " è inferiore a zero!");
    }
}
