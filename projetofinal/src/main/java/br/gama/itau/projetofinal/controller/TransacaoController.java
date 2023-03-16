package br.gama.itau.projetofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gama.itau.projetofinal.service.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping("/sacar/{id},{valor}")
    public ResponseEntity<HttpStatus> sacarConta(@PathVariable Integer id, @PathVariable Double valor) {
        boolean sacar = service.sacar(id, valor);
        if (sacar == true) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/depositar/{id},{valor}")
    public ResponseEntity<HttpStatus> depositarConta(@PathVariable Integer id, @PathVariable Double valor) {
        boolean depositar = service.depositar(id, valor);
        if (depositar == true) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }
} 
