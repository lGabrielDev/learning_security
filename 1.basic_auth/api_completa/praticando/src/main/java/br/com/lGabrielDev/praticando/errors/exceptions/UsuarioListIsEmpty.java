package br.com.lGabrielDev.praticando.errors.exceptions;

public class UsuarioListIsEmpty extends RuntimeException{

    //constructors
    public UsuarioListIsEmpty(String errorMessage){
        super(errorMessage);
    }
}
