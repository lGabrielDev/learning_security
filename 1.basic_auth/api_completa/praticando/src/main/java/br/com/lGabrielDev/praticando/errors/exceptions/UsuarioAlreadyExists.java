package br.com.lGabrielDev.praticando.errors.exceptions;

public class UsuarioAlreadyExists extends RuntimeException{
    
    //constructors
    public UsuarioAlreadyExists(String errorMessage){
        super(errorMessage);
    }
}
