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

    public boolean sacar(int id, double valor) {
        if (valor <= 0 || id <= 0) {
            return false;
        }

        // Cria uma movimentacao do tipo debito e faz um sque quando o ID e os valores
        // são validos
        Conta conta = contaService.recuperarPeloNumero(id);
        Movimentacao movimentacao = new Movimentacao(-1, LocalDate.now(), valor, 2, "Saque", conta);
        movimentacaoService.cadastrarMovimentacao(movimentacao);

        boolean retornoSacar = contaService.sacar(valor, id);

        return retornoSacar;
    }

    // Cria uma movimentacao do tipo credito e faz um deposito quando o ID e os
    // valores são validos
    public boolean depositar(int id, double valor) {
        if (valor <= 0 || id <= 0) {
            return false;
        }

        Conta conta = contaService.recuperarPeloNumero(id);
        Movimentacao movimentacao = new Movimentacao(-1, LocalDate.now(), valor, 2, "Deposito", conta);
        movimentacaoService.cadastrarMovimentacao(movimentacao);

        boolean retornoDepositar = contaService.depositar(valor, id);

        return retornoDepositar;
    }


    //realiza uma transferência de valores de uma conta Origem para uma conta destino
    //quando o idcontadestino é diferente do idcontaorigem
    //quando o contaOrigem e contadestino é diferente de null e o valor é maior que 0
    public boolean transferir(int idContaOrigem, int idContaDestino, double valor) {

        Conta contaOrigem = contaService.recuperarPeloNumero(idContaOrigem);
        Conta contaDestino = contaService.recuperarPeloNumero(idContaDestino);

        if (idContaDestino == idContaOrigem) {
            return false;
        }

        // Realiza a transfêrencia entre as contas, sacando da origem e depositando na
        // destino
        if (contaOrigem != null && contaDestino != null && valor > 0) {
            boolean retornoSacar = contaService.sacar(valor, contaOrigem.getId());
            if (retornoSacar) {
                contaService.depositar(valor, contaDestino.getId());

                // Realiza o cadastro das movimentações realizadas pelas contas
                movimentacaoService.cadastrarMovimentacao(
                        new Movimentacao(-1, LocalDate.now(), valor, 2, "Transferência - Saque", contaOrigem));
                movimentacaoService.cadastrarMovimentacao(
                        new Movimentacao(-1, LocalDate.now(), valor, 1, "Transferência - Deposito", contaDestino));

                return true;
            }

        }

        return false;

    }
}
