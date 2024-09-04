package br.com.lGabrielDev.praticando.models.usuario;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lGabrielDev.praticando.errors.exceptions.FieldIsNullException;
import br.com.lGabrielDev.praticando.errors.exceptions.UsuarioAlreadyExists;
import br.com.lGabrielDev.praticando.errors.exceptions.UsuarioAlreadyHasThisRole;
import br.com.lGabrielDev.praticando.errors.exceptions.UsuarioListIsEmpty;
import br.com.lGabrielDev.praticando.errors.exceptions.UsuarioNotFound;
import br.com.lGabrielDev.praticando.models.role.Role;
import br.com.lGabrielDev.praticando.models.role.RoleRepositoy;
import br.com.lGabrielDev.praticando.models.usuario.DTOs.UsuarioCreateDTO;
import br.com.lGabrielDev.praticando.models.usuario.DTOs.UsuarioFullDTO;

@Service
public class UsuarioService implements UserDetailsService {

    //injected attributes
    @Autowired
    private UsuarioRepository ur;

    @Autowired
    private RoleRepositoy rr;

    @Autowired
    private PasswordEncoder protegerSenha;

    

    // ====================== Conferir credenciais - automaticamente ======================
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //verificamos se existe algum usuario no banco com o "username" informado no formulario
        
        Optional<Usuario> uOptional = this.ur.findByUsername(username);

        if(uOptional.isEmpty()){
            throw new RuntimeException("User not found in our database");
        }


        return uOptional.get(); //retornamos esse usuario do banco. Podemos retornar, pois "Username" está implementando a "UserDetailsService"
    }




    // ====================== CREATE usuario ======================
    public UsuarioFullDTO createUsuario(UsuarioCreateDTO novoUsuario){
        
        //verificar os dados informados no body da requisicao
        System.out.println("\n\n\n\n==========================\n");
        System.out.println("Usuario informado no body:\n");
        System.out.println(novoUsuario.toString());
        System.out.println("\n==========================\n");


        //verificamos se os campos "username" and "password" estão preenchidos. Não permitiremos campos nulos.
        if(novoUsuario.getUsername() == null){
            throw new FieldIsNullException("'username' cannot be null");
        }
        if(novoUsuario.getPassword() == null){
            throw new FieldIsNullException("'password' cannot be null");
        }


        //verificar se ja existe algum usuario no banco com esse "username" informado
        Optional<Usuario> uOptional = this.ur.findByUsername(novoUsuario.getUsername());
        
        if(uOptional.isPresent()){
            throw new UsuarioAlreadyExists("'username' já existe no banco!");
        }


        //Tudo certo! Vamos criar um "Usuario" e cadastrar no banco
        //Sempre que criamos um usuario, ele recebe o cargo padrao "USUARIO_PADRAO"
        
        Role r1 = this.rr.findByAuthority("USUARIO_PADRAO").get();
        Set<Role> roles = new HashSet<>();
        roles.add(r1);


        Usuario u1 = new Usuario();
        u1.setUsername(novoUsuario.getUsername());
        u1.setPassword(this.protegerSenha.encode(novoUsuario.getPassword()));
        u1.setAuthorities(roles);

        //bilateralidade. Se um dos lados recebe, o outro tambem recebe.
        //usuario vai receber um cargo
        //o cargo vai receber esse usuario
        r1.getUsuarios().add(u1);

        //salvamos no banco
        this.ur.save(u1);
        this.rr.save(r1);

        UsuarioFullDTO dtoFull = new UsuarioFullDTO(u1);

        return dtoFull;
    }


    // ====================== LIST ALL usuarios ======================
    public List<UsuarioFullDTO> listAllUsuarios(String cargoProcurado){
        
        List<Usuario> usuarios = new ArrayList<>();

        //se o cliente nao informar o filtro que quer fazer, retornamos TODOS os usuarios
        if(cargoProcurado == null){
            usuarios = this.ur.findAll();    
        }
        else{
            usuarios = this.ur.findAll(cargoProcurado);
        }

        
        if(usuarios.size() <= 0){
            throw new UsuarioListIsEmpty("Ainda não foi cadastrado nenhum usuario");
        }


        
        return UsuarioFullDTO.converterLista(usuarios);
    }



    // ====================== Dar permissao de "ADMIN" ======================
    public UsuarioFullDTO darPermissaoAdmin(String usename){

        //verificamos se existe de fato algum usuario com o "username" informado
        Optional<Usuario> uOptional = this.ur.findByUsername(usename);

        if(uOptional.isEmpty()){
            throw new UsuarioNotFound("Usuario nao encontrado.");
        }

        //verificamos se o usuario ja possui o cargo de ADM
        if(uOptional.get().hasRole("ADMIN")){
            throw new UsuarioAlreadyHasThisRole("Usuario ja possui essa role");
        }

        //tudo certo... Usuario existe E ainda não possui o cargo de ADM. Vamos dar o cargo a ele.
        Usuario u1 = uOptional.get();

        Role roleAdmin = this.rr.findByAuthority("ADMIN").get(); //pegamos a role "ADMIN" do banco
    
        //bilateralidade
        u1.getCargos().add(roleAdmin); //usuario recebe o cargo
        roleAdmin.getUsuarios().add(u1); //o cargo recebe esse usuario


        //salvamos no banco
        this.ur.save(u1);
        this.rr.save(roleAdmin);

        UsuarioFullDTO dtoFull = new UsuarioFullDTO(u1);

        return dtoFull;
        
    }




   
    
}
