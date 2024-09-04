package br.com.lGabrielDev.praticando.models.role;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.lGabrielDev.praticando.models.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;




@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority{

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "authority")
    private String authority;


    @JsonIgnore //para nao ficar loopando
    @ManyToMany(mappedBy = "authorities")
    private Set<Usuario> usuarios;

    //constructors
    public Role(){
        this.usuarios = new HashSet<>();//para nao dar problema de "lista null"
    }

    public Role(String authority, Set<Usuario> usuarios){
        this.authority = authority;
        this.usuarios = usuarios;
    }


    //getters and setters
    public Long getId(){
        return this.id;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }


    public void setAuthority(String authority){
        this.authority = authority;
    }


    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    //toString(){}
    @Override
    public String toString() {
        return "Role [id=" + id + ", authority=" + authority + "]";
    }
    
}
