package br.gama.itau.projetofinal.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.model.Movimentacao;

@Service
public class TransacaoService {

    @Autowired
    private ContaService contaService;

    @Autowired
    private MovimentacaoService movimentacaoService;

    public boolean sacar (int id, double valor) {
        if (valor <= 0 || id <= 0) {
            return false;
        }

        
        Conta conta = contaService.recuperarPeloNumero(id);
        Movimentacao movimentacao = new Movimentacao(-1, LocalDate.now(),valor, 2, "Saque", conta);
        movimentacaoService.cadastrarMovimentacao(movimentacao);
        
        boolean retornoSacar = contaService.sacar(valor, id);

        return retornoSacar;
    }

    public boolean depositar (int id, double valor) {
        if (valor <= 0 || id <= 0) {
            return false;
        }

        Conta conta = contaService.recuperarPeloNumero(id);
        Movimentacao movimentacao = new Movimentacao(-1, LocalDate.now(),valor, 2, "Deposito", conta);
        movimentacaoService.cadastrarMovimentacao(movimentacao);

        boolean retornoDepositar = contaService.depositar(valor, id);

        return retornoDepositar;
    }

    

    
}
