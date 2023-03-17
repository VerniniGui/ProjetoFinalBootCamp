package br.gama.itau.projetofinal.util;

import br.gama.itau.projetofinal.model.Cliente;

public class GenerateCliente {

    public static Cliente novoCliente() {
        return Cliente.builder()
            .nomeCliente("novoCliente 1")
            .cpf("novoCpf 1")
            .telefone("novoTelefone 1")
            .build();
    }
    
    public static Cliente clienteValido() {
        return Cliente.builder()
            .id(1)
            .nomeCliente("ClienteValido 1")
            .cpf("CpfValido 1")
            .telefone("TelefoneValido 1")
            .build();
    }

}
