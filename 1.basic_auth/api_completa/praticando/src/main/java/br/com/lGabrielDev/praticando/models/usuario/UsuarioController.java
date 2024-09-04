package br.com.lGabrielDev.praticando.models.usuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {
    
    //rotas

    @GetMapping("/rota1")
    public String rota1(){
        return "salve rota1";
    }

    @GetMapping("/rota2")
    public String rota2(){
        return "salve rota2";
    }
}
