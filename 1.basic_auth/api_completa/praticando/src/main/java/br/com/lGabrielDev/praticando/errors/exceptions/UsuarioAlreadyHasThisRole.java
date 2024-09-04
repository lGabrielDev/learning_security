package br.com.lGabrielDev.praticando.errors.exceptions;

public class UsuarioAlreadyHasThisRole extends RuntimeException{
    
    //constructors
    public UsuarioAlreadyHasThisRole(String errorMessage){
        super(errorMessage);
    }
}
