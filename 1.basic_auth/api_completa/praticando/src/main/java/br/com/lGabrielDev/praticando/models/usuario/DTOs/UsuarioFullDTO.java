package br.com.lGabrielDev.praticando.models.usuario.DTOs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import br.com.lGabrielDev.praticando.models.role.Role;
import br.com.lGabrielDev.praticando.models.usuario.Usuario;

//Class DTO para mostrarmos todos os attributes relevantes
public class UsuarioFullDTO {
    
    //attributes
    Long id;
    String username;
    String password;
    Set<Role> cargos;

    //constructors
    public UsuarioFullDTO(){
        this.cargos = new HashSet<>();
    }

    public UsuarioFullDTO(Usuario usuarioCru){
        this.id = usuarioCru.getId();
        this.username = usuarioCru.getUsername();
        this.password = usuarioCru.getUsername();
        this.cargos = usuarioCru.getCargos();
    }


    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Role> getCargos() {
        return cargos;
    }

    public void setCargos(Set<Role> cargos) {
        this.cargos = cargos;
    }


    
    //method para transformar uma lista de "Usuario" em uma lista de "UsuarioFullDTO"
    public static List<UsuarioFullDTO> converterLista(List<Usuario> listaUsuariosCrus){
        List<UsuarioFullDTO> listaConvertida = new ArrayList<>();
        
        for(Usuario i : listaUsuariosCrus){
            UsuarioFullDTO u1 = new UsuarioFullDTO(i);
            listaConvertida.add(u1);
        }
        return listaConvertida;
    }

    //toString(){}
}
