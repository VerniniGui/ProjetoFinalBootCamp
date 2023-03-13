package br.gama.itau.projetofinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal.model.Movimentacao;
import br.gama.itau.projetofinal.repository.MovimentacaoRepo;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepo repo;


    public Movimentacao cadastrarMovimentacao(Movimentacao novaMovimentacao) {

        if (novaMovimentacao.getNum() > 0) {
            return null;
        }
        Movimentacao movimentacaoInserica = repo.save(novaMovimentacao);
        return movimentacaoInserica;
    }

    // public List<Movimentacao> recuperarTodas(int numero) {
    //     List<Movimentacao> lista = (List<Movimentacao>) repo.findAll();

    //     for (Movimentacao movimentacao : lista) {
    //         if (movimentacao.) {
                
    //         }
    //     }
    // }


    
}