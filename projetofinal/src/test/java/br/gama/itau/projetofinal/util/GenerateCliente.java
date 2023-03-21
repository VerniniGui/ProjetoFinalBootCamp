package br.gama.itau.projetofinal.util;

import java.util.ArrayList;
import java.util.List;

import br.gama.itau.projetofinal.model.Cliente;

public class GenerateCliente {

    public static Cliente clienteValido() {
        return Cliente.builder()
                .id(1)
                .nomeCliente("ClienteValido 1")
                .cpf("CpfValido 1")
                .telefone("TelefoneValido 1")
                .listaContas(GenerateConta.ListaDeContaValidaSemCliente())
                .build();
    }

    public static Cliente novoCliente() {
        return Cliente.builder()
                .nomeCliente("novoCliente 1")
                .cpf("novoCpf 1")
                .telefone("novoTelefone 1")
                .build();
    }

    public static Cliente novoCliente2() {
        return Cliente.builder()
            .nomeCliente("novoCliente 2")
            .cpf("novoCpf 2")
            .telefone("novoTelefone 2")
            .build();
    }
    

    public static Cliente clienteNovo() {
        return Cliente.builder()
                .id(1)
                .nomeCliente("ClienteValido 1")
                .cpf("CpfValido 1")
                .telefone("TelefoneValido 1")
                .listaContas(GenerateConta.ListaDeContaValidaSemCliente())
                .build();
    }

    public static Cliente clienteNovo2() {
        return Cliente.builder()
                
                .nomeCliente("ClienteValido 1")
                .cpf("CpfValido 1")
                .telefone("TelefoneValido 1")
                .listaContas(GenerateConta.ListaDeContaValidaSemCliente())
                .build();
    }

    public static Cliente clienteInvalido() {
        return Cliente.builder()
                .nomeCliente("ClienteValido 1")
                .telefone("TelefoneValido 1")
                .build();
    }

    public static List<Cliente> listaDeClienteValida() {
        List<Cliente> listaValida = new ArrayList<>();

        Cliente cliente1 = Cliente.builder()
                .nomeCliente("novoCliente 1")
                .cpf("novoCpf 1")
                .telefone("novoTelefone 1")
                .build();

        Cliente cliente2 = Cliente.builder()
                .nomeCliente("novoCliente2")
                .cpf("novoCpf2")
                .telefone("novoTelefone2")
                .build();


        listaValida.add(cliente1);
        listaValida.add(cliente2);

        return listaValida;
    }

    public static Cliente novoClienteNomeNull() {
        return Cliente.builder()
                .id(1)
                .cpf("CpfValido 1")
                .telefone("TelefoneValido 1")
                .listaContas(GenerateConta.ListaDeContaValidaSemCliente())
                .build();
    }

}
