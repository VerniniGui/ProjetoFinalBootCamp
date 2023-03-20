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

    public boolean transferir(int  idContaOrigem, int idContaDestino, double valor){
        
        
       Conta contaOrigem = contaService.recuperarPeloNumero(idContaOrigem);
       Conta contaDestino = contaService.recuperarPeloNumero(idContaDestino);


    // Realiza a transfêrencia entre as contas, sacando da origem e depositando na destino   
    if(contaOrigem != null && contaDestino != null){
        contaService.sacar(valor, contaOrigem.getId());      
        contaService.depositar(valor, contaDestino.getId());
        
        // Realiza o cadastro das movimentações realizadas pelas contas
        movimentacaoService.cadastrarMovimentacao(new Movimentacao(-1, LocalDate.now(),valor, 2, "Tranferência - Saque", contaOrigem));
        movimentacaoService.cadastrarMovimentacao(new Movimentacao(-1, LocalDate.now(),valor, 1, "Tranferência - Deposito", contaDestino));

        return true;
    }
    
    return false;
    
    }
}    
