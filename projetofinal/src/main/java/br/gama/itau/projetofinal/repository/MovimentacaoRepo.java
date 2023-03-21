package br.gama.itau.projetofinal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.model.Movimentacao;

public interface MovimentacaoRepo extends CrudRepository<Movimentacao, Integer>{

    List<Movimentacao> findByContaAndDataOperacaoBetween (
        Conta conta,
        LocalDate dataInicio, 
        LocalDate dataFinal
        );    
}
