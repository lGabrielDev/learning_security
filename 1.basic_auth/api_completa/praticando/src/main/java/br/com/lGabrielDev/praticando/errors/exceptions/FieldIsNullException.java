package br.com.lGabrielDev.praticando.errors.exceptions;

public class FieldIsNullException extends RuntimeException{
    
    //constructors
    public FieldIsNullException(String errorMessage){
        super(errorMessage);
    }
}
