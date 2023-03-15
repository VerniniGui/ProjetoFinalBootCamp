package br.gama.itau.projetofinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.model.Movimentacao;
import br.gama.itau.projetofinal.repository.ContaRepo;

@Service

public class ContaService {

    @Autowired
    private ContaRepo repo;

    public Conta adiconarConta(Conta conta) {
       Conta contaCadastrada = null;

        if (conta.getAgencia().equals("0000"))  {
            return null;
        }

        if (conta.getAgencia().length() != 4) {
            return null;
        }

        if (conta.getConta() != 1 && conta.getConta() != 2 && conta.getConta() != 3) {
            return null;
       }

        if (conta.getSaldo() < 0) {
            return null;
        }
       
       try {
         contaCadastrada = repo.save(conta);
        
       } catch (Exception e) {
        
       }
       
        
        return contaCadastrada;

        
    }

    
    public Conta recuperarPeloNumero(int numero) {

        Optional<Conta> optional = repo.findById(numero);
        Conta conta = optional.get();
        return conta;
    }

    public Conta AlterarDados(Conta conta, int id) {

        Optional<Conta> optional = repo.findById(id);
        if (optional.isEmpty()) {
            return null;

        }

        conta.setId(id);

        Conta contaAtualizada = repo.save(conta);

        return contaAtualizada;
    }

    // public List<Conta> recuperarContasPeloCliente(int idCliente) {
    // List<Conta> lista = (List<Conta>)repo.findAll();
    // List<Conta> listId = new ArrayList<>();
    // for (Conta conta : lista) {
    // if(conta.getIdCliente().equals(idCliente)) {
    // listId.add(conta);
    // }
    // }
    // return listId;
    // }

    public List<Movimentacao> recuperarMovimentacoes(int id) {
        Optional<Conta> optional = repo.findById(id);
        Conta conta = (Conta) optional.get();

        return conta.getListaMovimentacao();

    }

}
