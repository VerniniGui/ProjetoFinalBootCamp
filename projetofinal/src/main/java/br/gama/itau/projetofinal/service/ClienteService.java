package br.gama.itau.projetofinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal.dto.ClienteDto;
import br.gama.itau.projetofinal.model.Cliente;
import br.gama.itau.projetofinal.repository.ClienteRepo;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepo repo;
    
    public Cliente cadastrarCliente(Cliente cliente){
        Cliente novoCliente = cliente;
        repo.save(novoCliente);
        return novoCliente;
    };

    public List<ClienteDto> recuperarTodos(){
        List<Cliente> list = (List<Cliente>) repo.findAll();
        List<ClienteDto> lClienteDTO = new ArrayList<>();

        if (list == null) {
            return null;
        }
        for (Cliente cliente : list) {
            lClienteDTO.add(new ClienteDto(cliente));

        }
        return lClienteDTO;
    };

    public Cliente recuperarPeloId(int id){
        Optional<Cliente> optional = repo.findById(id);

        if(optional.isPresent()){
            Cliente cliente = optional.get();
            return cliente;
        }
        return null;

    }
}
