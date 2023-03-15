package br.gama.itau.projetofinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal.dto.ClienteDto;
import br.gama.itau.projetofinal.dto.ContaDto;
import br.gama.itau.projetofinal.dto.MovimentacaoDto;

import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.model.Movimentacao;
import br.gama.itau.projetofinal.repository.ContaRepo;

@Service

public class ContaService {

    @Autowired
    private ContaRepo repo;


    @Autowired
    private ClienteService clienteService;

    public ContaDto adiconarConta(Conta conta) {
        if (conta.getAgencia().equals("0000")) {
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
        conta = repo.save(conta);
        ClienteDto cliente = clienteService.recuperarPeloId(conta.getIdCliente().getId());
        ContaDto contaDto = new ContaDto(conta);
        contaDto.setNomeCliente(cliente.getNomeCliente());
        
        return contaDto;
    }

    public ContaDto recuperarPeloNumero(int numero) {

        Optional<Conta> optional = repo.findById(numero);
        Conta conta = optional.get();
        ContaDto contaDto = new ContaDto(conta);
        return contaDto;
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

    public List<MovimentacaoDto> recuperarMovimentacoes(int id) {
        Optional<Conta> optional = repo.findById(id);
        Conta conta = (Conta) optional.get();
        List<MovimentacaoDto> listaMovDto = new ArrayList<>();
        List<Movimentacao> listaMov = conta.getListaMovimentacao();

        for (Movimentacao x : listaMov) {
            listaMovDto.add(new MovimentacaoDto(x));
        }
        return listaMovDto;

    }

}
