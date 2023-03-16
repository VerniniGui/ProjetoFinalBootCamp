package br.gama.itau.projetofinal.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gama.itau.projetofinal.dto.ContaDto;
import br.gama.itau.projetofinal.dto.MovimentacaoDto;
import br.gama.itau.projetofinal.exception.MyNoSuchElementException;
import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.service.ContaService;

@RestController
@RequestMapping("/contas")

public class ContaController {
    @Autowired
    private ContaService service;

    @GetMapping("/{id}")
    public ResponseEntity<ContaDto> recuperaPeloNumero(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.recuperarPeloNumero(id));
        } catch (NoSuchElementException e) {
            throw new MyNoSuchElementException("Conta não encontrada");
        }

    }

    // @GetMapping("/clientes/{id}")
    // public ResponseEntity<List<Conta>> recuperarConta(@PathVariable Integer id) {
    // List<Conta> list = service.recuperarContasPeloCliente(id);
    // return ResponseEntity.ok(list);
    // }

    @PostMapping
    public ResponseEntity<ContaDto> adicionarConta(@RequestBody Conta conta) {

        ContaDto novaConta = service.adiconarConta(conta);
        return ResponseEntity.ok(novaConta);

    }

    @GetMapping("/movimentacao/{id}")
    public ResponseEntity<List<MovimentacaoDto>> getTodasMovimentacao(@PathVariable Integer id) {

        try {
            List<MovimentacaoDto> listaMov = service.recuperarMovimentacoes(id);
            if (listaMov.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(service.recuperarMovimentacoes(id));
        } catch (NoSuchElementException e) {
            throw new MyNoSuchElementException("Conta não encontrada");
        }

    }

    @GetMapping("/movimentacao/periodo/{id},{dataInicio},{dataFinal}")
    public ResponseEntity<List<MovimentacaoDto>> getMovimentacaoByPeriodo(@PathVariable int id,
            @PathVariable LocalDate dataInicio, @PathVariable LocalDate dataFinal) {
        List<MovimentacaoDto> lista;

        lista = service.retornaHistoricoMovimentacao(id, dataInicio, dataFinal);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);

    }

}
