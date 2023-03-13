package br.gama.itau.projetofinal.dto;

import br.gama.itau.projetofinal.model.Cliente;

public class ClienteDto {

    private String nomeCliente;    
    private String cpf;   
    private String telefone;


    public ClienteDto(Cliente cliente) {
        this.nomeCliente = cliente.getNomeCliente();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
    }

    
}
