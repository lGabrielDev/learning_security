package br.com.lGabrielDev.praticando.models.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.lGabrielDev.praticando.models.usuario.UsuarioService;
import br.com.lGabrielDev.praticando.models.usuario.DTOs.UsuarioCreateDTO;
import br.com.lGabrielDev.praticando.models.usuario.DTOs.UsuarioFullDTO;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    //injected attributes
    @Autowired
    private UsuarioService us;

    
    // ====================== Rotas testes ======================

    @GetMapping("/rota1")
    public String rota1(){
        return "Salve rota1";
    }

    @GetMapping("/rota2")
    public String rota2(){
        return "Salve rota2";
    }



    // ====================== LIST ALL usuario ======================
    @GetMapping("/usuarios")
    public List<UsuarioFullDTO> listAllUsuarios(@RequestParam(value =  "cargo" , required = false) String cargoProcurado){
        return this.us.listAllUsuarios(cargoProcurado); //colocar depois para listar todos os usuarios que tenham "tal cargo"
    }


    // ====================== CREATE usuario ======================
    @PostMapping("/create_usuario")
    public UsuarioFullDTO createUsuario(@RequestBody(required = true) UsuarioCreateDTO novoUsuario){
        return this.us.createUsuario(novoUsuario);
    }


    // ====================== DAR cargo de "ADMIN" para um usuario ======================
    @PutMapping("/darCargo/{username}")
    public UsuarioFullDTO darCargoAdm(@PathVariable(value = "username") String username){
        return this.us.darPermissaoAdmin(username);
    }

}
