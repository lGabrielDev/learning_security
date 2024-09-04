package br.com.lGabrielDev.praticando.errors.exceptions;

public class UsuarioNotFound extends RuntimeException{
    
    //constructors
    public UsuarioNotFound(String errorMessage){
        super(errorMessage);
    }
}
