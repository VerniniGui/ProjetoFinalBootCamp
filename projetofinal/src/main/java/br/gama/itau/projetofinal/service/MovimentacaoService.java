package br.gama.itau.projetofinal.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal.exception.HttpMessageNotReadableExceptionMy;
import br.gama.itau.projetofinal.model.Movimentacao;
import br.gama.itau.projetofinal.repository.MovimentacaoRepo;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepo repo;

    // @Autowired
    // private TransacaoService service;

    public Movimentacao cadastrarMovimentacao(Movimentacao novaMovimentacao) {
        Movimentacao movimentacaoInserida = null;

        if (novaMovimentacao.getNum() == -1) {
            repo.save(novaMovimentacao);
            return null;
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

        try {
            // if (novaMovimentacao.getTipo() == 1) {
                
            //     service.depositar(novaMovimentacao.getConta().getId(), novaMovimentacao.valor);
            // } else {
            //     service.sacar(novaMovimentacao.getConta().getId(), novaMovimentacao.valor);
            // }

            movimentacaoInserida = repo.save(novaMovimentacao);

        } catch (Exception e) {
            throw new HttpMessageNotReadableExceptionMy("Data Inválida! tente: YYYY-MM-DD");
        }
        return movimentacaoInserida;

    }

    public List<Movimentacao> recuperarMovimentacaoPeriodo(LocalDate dataInicio, LocalDate datafinal) {
        List<Movimentacao> listaMov = repo.findByDataOperacaoBetween(dataInicio, datafinal);

        return listaMov;
    }

    // public List<Movimentacao> recuperarTodas(int id) {
    // List<Movimentacao> lista = (List<Movimentacao>) repo.findAll();
    // List<Movimentacao> moviConta = new ArrayList<>();
    // for (Movimentacao movimentacao : lista) {
    // if(movimentacao.getConta().getId() == id) {
    // moviConta.add(movimentacao);
    // }
    // }
    // return moviConta;

    // }

}
