package br.gama.itau.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.gama.itau.projetofinal.model.Movimentacao;
import br.gama.itau.projetofinal.service.MovimentacaoService;

@Controller
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    
    @Autowired
    public MovimentacaoService service;

    @PostMapping
    public ResponseEntity<Movimentacao> newMovimentacao (@RequestBody Movimentacao novaMovimentacao){
        Movimentacao movimentacaoInserida = service.cadastrarMovimentacao(novaMovimentacao);

        if(movimentacaoInserida == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoInserida);
    }

    @GetMapping("movimentacao/{id}")
    public ResponseEntity<List<Movimentacao>> getTodas(@PathVariable int numero ) {
        List<Movimentacao> recuperarMovimentacao = service.recuperarTodas(numero);
        return ResponseEntity.ok(recuperarMovimentacao);
    }
}
