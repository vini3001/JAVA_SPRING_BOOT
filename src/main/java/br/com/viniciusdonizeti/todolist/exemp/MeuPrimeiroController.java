package br.com.viniciusdonizeti.todolist.exemp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

// @Controller
@RestController
@RequestMapping("/PrimeiraRota/")
//http://localhost:8888/PrimeiraRota/
public class MeuPrimeiroController {
    /**
     * GET - BUSCAR INFORMAÇÃO
     * POST - ADICIONAR UMA INFORMAÇÃO
     * PATCH - ALTERAR UMA PARTE DA INFORMAÇÃO
     * PUT - ALTERAR VÁRIAS INFORMAÇÕes
     * DELETE - DELETAR UMA INFORMAÇÃO
     * @return
     */
    @GetMapping("/")
    public String MinhaPrimeiraMensagem(){
        return "Minha primeira mensagem";
    }
}
