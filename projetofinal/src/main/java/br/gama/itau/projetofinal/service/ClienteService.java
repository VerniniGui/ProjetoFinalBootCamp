package br.gama.itau.projetofinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal.dto.ClienteDto;
import br.gama.itau.projetofinal.exception.MyDataIntegrityViolationException;
import br.gama.itau.projetofinal.exception.MyNotFoundException;
import br.gama.itau.projetofinal.model.Cliente;
import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.repository.ClienteRepo;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepo repo;

    public Cliente cadastrarCliente(Cliente cliente) {

        Cliente novoCliente = cliente;

        if (cliente.getNomeCliente() == null) {
            cliente.setNomeCliente("Nome não cadastrado");
        }

        try {
            repo.save(novoCliente);
        } catch (DataIntegrityViolationException e) {
            throw new MyDataIntegrityViolationException("Dados Invalidos");
        }

        return novoCliente;

    };

    public List<ClienteDto> recuperarTodos() {
        List<Cliente> list = (List<Cliente>) repo.findAll();
        List<ClienteDto> lClienteDTO = new ArrayList<>();

        if (list.isEmpty()) {
            throw new MyNotFoundException("Nenhum cliente encontrado");
        }
        for (Cliente cliente : list) {
            lClienteDTO.add(new ClienteDto(cliente));

        }
        return lClienteDTO;
    };

    public ClienteDto recuperarPeloId(int id) {
        Optional<Cliente> optional = repo.findById(id);

        if (optional.isEmpty()) {
            throw new MyNotFoundException("Cliente aão encontrado");
        }
        Cliente cliente = optional.get();
        ClienteDto clienteDto = new ClienteDto(cliente);
        return clienteDto;

    }

    public List<Conta> recuperarContas(int id) {
        Optional<Cliente> optional = repo.findById(id);
        Cliente cliente = (Cliente) optional.get();
        

        return cliente.getListaContas();

    }

}
