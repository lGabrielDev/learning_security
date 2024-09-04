package br.com.lGabrielDev.praticando.models.usuario.DTOs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//usaremos essa Class para criar um "usuario"

@NoArgsConstructor
@AllArgsConstructor

public class UsuarioCreateDTO {

    //attributes
    private String username;
    private String password;
   
    //constructors --> Criados pelo lombock


    //getters and setters
     public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    //toString()
    @Override
    public String toString(){
        return
            String.format(
                "username: %s\n" +
                "password: %s\n", this.username, this.password
            );
    }   
    
}
