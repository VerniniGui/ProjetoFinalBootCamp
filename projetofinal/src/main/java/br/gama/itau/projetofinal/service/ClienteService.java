package br.gama.itau.projetofinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal.dto.ClienteDto;
import br.gama.itau.projetofinal.dto.ContaDto;
import br.gama.itau.projetofinal.exception.MyDataIntegrityViolationException;
import br.gama.itau.projetofinal.exception.MyNoSuchElementException;
import br.gama.itau.projetofinal.exception.MyNotFoundException;
import br.gama.itau.projetofinal.model.Cliente;
import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.repository.ClienteRepo;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepo repo;


    //Responsavel por cadastrar um novo cliente no banco de dados
    //quando o nome for null - "Nome não cadastrado"
    //quando o CPF não é null
    //quando o Telefone não é null
    //Caso o cliente já for cadastrado, não permite o cadastro
    public Cliente cadastrarCliente(Cliente cliente) {

        Cliente novoCliente = cliente;

        if (cliente.getNomeCliente() == null) {
            cliente.setNomeCliente("Nome não cadastrado");
        }

        if (cliente.getCpf() == null) {
            throw new MyDataIntegrityViolationException("O cpf Digitado é invalido");
        }

        if (cliente.getTelefone() == null) {
            throw new MyDataIntegrityViolationException("O telefone digitado é invalido");
        }

        try {
            repo.save(novoCliente);
        } catch (DataIntegrityViolationException e) {
            throw new MyDataIntegrityViolationException("O cliente já está cadastrado, verifique o cpf ou o telefone");
        }

        return novoCliente;

    };

    //Traz uma lista de clientes já cadastrados
    //Caso a lista for vazia - "Nenhum cliente encontrado"
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
    
    //Traz um cliente valido procurando por um id específico
    //Caso o Id procurado não existir no banco, "cliente não encontrado"
    public ClienteDto recuperarPeloId(int id) {
        Optional<Cliente> optional = repo.findById(id);

        if (optional.isEmpty()) {
            throw new MyNotFoundException("Cliente aão encontrado");
        }
        Cliente cliente = optional.get();
        ClienteDto clienteDto = new ClienteDto(cliente);
        return clienteDto;

    }

    // Retorna a lista de contas do cliente buscado pelo id
    public List<ContaDto> recuperarContas(int id) {

        try {
            Optional<Cliente> optional = repo.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            Cliente cliente = (Cliente) optional.get();

            List<Conta> listaContas = cliente.getListaContas();

            List<ContaDto> listaContasDto = new ArrayList<>();

            if (listaContas == null) {
                return null;
            }

            for (Conta conta : listaContas) {
                listaContasDto.add(new ContaDto(conta));
            }

            return listaContasDto;

        } catch (NullPointerException e) {
            throw new NullPointerException("Cliente não encontrado");
        }catch(NoSuchElementException e){
            throw new MyNoSuchElementException("Cliente não encontrado");
        }

        

    }

}
