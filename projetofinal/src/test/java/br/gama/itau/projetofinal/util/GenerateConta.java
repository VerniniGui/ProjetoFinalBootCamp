package br.gama.itau.projetofinal.util;

import br.gama.itau.projetofinal.model.Conta;

public class GenerateConta {

    public static Conta novaConta() {
        return Conta.builder()
                .agencia("1234")
                .conta(1)
                .saldo(100)
                .idCliente(GenerateCliente.clienteValido())
                .build();
    }

    public static Conta contaValida() {
        return Conta.builder()
                .id(1)
                .agencia("1234")
                .conta(1)
                .saldo(100)
                .idCliente(GenerateCliente.clienteValido())
                .build();
    }

    public static Conta contaAgenciaInvalida() {
        return Conta.builder()
                .id(1)
                .agencia("123")
                .conta(1)
                .saldo(100)
                .idCliente(GenerateCliente.clienteValido())
                .build();
    }

}
