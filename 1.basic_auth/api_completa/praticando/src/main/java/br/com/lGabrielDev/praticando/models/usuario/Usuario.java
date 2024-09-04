package br.com.lGabrielDev.praticando.models.usuario;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import br.com.lGabrielDev.praticando.models.role.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails{
    
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER) //carregamento eager. Ou seja, esse campo sempre vai ser carregado
    @JoinTable(
        name = "usuario_role",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> authorities;



    //constructors
    public Usuario(){
        this.authorities = new HashSet<>(); //inicializamos para nao ter problema
    }

    public Usuario(String username, String password, Set<Role> authorities){
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }


    //getters and setters
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
     
    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //method que verifica as roles/cargos que esse usuario tem. Quando samos o hasAuthority() lá na Class de configuracao, essee o method que é chamado
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    //vamos criar um get igualzinho o de cima, para conseguir adicionar cargos no attribute "authorities"
    public Set<Role> getCargos(){
        return this.authorities;
    }


    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    //retornar todas as authorities/cargos do usuario
    public String getAuthoritiesToString(){
        String cargosEmString = "";
        
        for(Role i : this.authorities){
            cargosEmString += "   " + i + "\n";
        }
        return cargosEmString;
    }


    //method para verificar se um usuario possui "tal" cargo
    public Boolean hasRole(String roleProcurada){
        for(Role i : this.authorities){
            if(i.getAuthority().equals(roleProcurada)){
                return true;
            }
        }
        return false;
    }


    //toString()
    @Override
    public String toString(){
        return
            String.format(
                "#ID: %d\n" +
                "username: %s\n" +
                "password: %s\n" +
                "authorities: %s\n", this.id, this.username, this.password, this.getAuthoritiesToString()
            );
    }
    
}
