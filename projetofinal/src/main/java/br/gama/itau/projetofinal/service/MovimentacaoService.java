package br.gama.itau.projetofinal.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.model.Movimentacao;
import br.gama.itau.projetofinal.repository.MovimentacaoRepo;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepo repo;

    //Cadastra uma nova movimentacao
    //Se o Id for -1
    //se o id não for maior que 0
    //se o valor for maior ou igual a 0
    //se o tipo da movimentacao for 1 ou 2
    public Movimentacao cadastrarMovimentacao(Movimentacao novaMovimentacao) {
        Movimentacao movimentacaoInserida = null;

        if (novaMovimentacao.getNum() == -1) {
            
            return repo.save(novaMovimentacao);
            
        }
        if (novaMovimentacao.getNum() > 0) {
            return null;
        }

        if (novaMovimentacao.getValor() < 0) {
            return null;
        }

        if (novaMovimentacao.getTipo() != 1 && novaMovimentacao.getTipo() != 2) {
            return null;
        } 

        movimentacaoInserida = repo.save(novaMovimentacao);       
        
        return movimentacaoInserida;

    }

    //Retorna uma lista de movimentaçoes dentro de um intervalo de datas informadas
    public List<Movimentacao> recuperarMovimentacaoPeriodo(Conta conta, LocalDate dataInicio, LocalDate datafinal) {
        List<Movimentacao> listaMov = repo.findByContaAndDataOperacaoBetween(conta, dataInicio, datafinal);

        return listaMov;
    }

}
