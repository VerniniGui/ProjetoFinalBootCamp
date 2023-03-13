package br.gama.itau.projetofinal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.repository.ContaRepo;

@Service

public class ContaService {

    @Autowired
    private ContaRepo repo;

    public Conta adiconarConta(Conta conta) {

        repo.save(conta);
        return conta;
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

    public Conta recuperarContasPeloCliente(int idCliente) {

        return null;
    }
}
