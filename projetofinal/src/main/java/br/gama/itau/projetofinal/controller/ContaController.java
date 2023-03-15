package br.gama.itau.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gama.itau.projetofinal.dto.MovimentacaoDto;
import br.gama.itau.projetofinal.exception.MyNullPointerException;
import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.service.ContaService;

@RestController
@RequestMapping("/contas")

public class ContaController {
    @Autowired
    private ContaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Conta> recuperaPeloNumero(@PathVariable Integer id) {
        return ResponseEntity.ok(service.recuperarPeloNumero(id));
    }

    // @GetMapping("/clientes/{id}")
    // public ResponseEntity<List<Conta>> recuperarConta(@PathVariable Integer id) {
    // List<Conta> list = service.recuperarContasPeloCliente(id);
    // return ResponseEntity.ok(list);
    // }

    @PostMapping
    public ResponseEntity<Conta> adicionarConta(@RequestBody Conta conta) {

        // if(novaConta == null){

        // return ResponseEntity.badRequest().build();
        // }

        try {
            Conta novaConta = service.adiconarConta(conta);
            return ResponseEntity.ok(novaConta);
        } catch (NullPointerException e) {
            throw new MyNullPointerException("Não foi possivel cadastrar o cliente");
        }

    }

    @GetMapping("/movimentacao/{id}")
    public ResponseEntity<List<MovimentacaoDto>> getTodasMovimentacao(@PathVariable Integer id) {

        return ResponseEntity.ok(service.recuperarMovimentacoes(id));
    }

}
