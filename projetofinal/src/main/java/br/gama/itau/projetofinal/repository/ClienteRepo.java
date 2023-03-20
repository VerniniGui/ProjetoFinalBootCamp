package br.gama.itau.projetofinal.repository;

import org.springframework.data.repository.CrudRepository;

import br.gama.itau.projetofinal.model.Cliente;

public interface ClienteRepo extends CrudRepository<Cliente, Integer> {

    
}
