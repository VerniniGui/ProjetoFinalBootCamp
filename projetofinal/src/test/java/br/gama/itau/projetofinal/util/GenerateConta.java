package br.gama.itau.projetofinal.util;

import br.gama.itau.projetofinal.model.Cliente;
import br.gama.itau.projetofinal.model.Conta;

public class GenerateConta {

    public static Conta novaConta(int id) {
        return Conta.builder()
                .agencia("1234")
                .conta(1)
                .saldo(100)
                .idCliente(Cliente.builder().id(id).build())
                .build();
    }

    public static Conta contaValidaCliente(int id) {
        return Conta.builder()
                .agencia("1237")
                .conta(1)
                .saldo(100)
                .idCliente(Cliente.builder().id(id).build())
                .build();
    }

    public static Conta novaConta2() {
        return Conta.builder()
                .agencia("1235")
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

    public static Conta contaCodigoAgenciaInvalida() {
        return Conta.builder()
                .id(1)
                .agencia("0000")
                .conta(1)
                .saldo(100)
                .idCliente(GenerateCliente.clienteValido())
                .build();
    }
    
    public static Conta contaTipoInvalida() {
        return Conta.builder()
                .id(1)
                .agencia("1234")
                .conta(5)
                .saldo(100)
                .idCliente(GenerateCliente.clienteValido())
                .build();
    }

    public static Conta contaSaldoContaInvalido() {
        return Conta.builder()
                .id(1)
                .agencia("1234")
                .conta(5)
                .saldo(0)
                .idCliente(GenerateCliente.clienteValido())
                .build();
    }

    public static Conta contaListaValida() {
        return Conta.builder()
                .id(1)
                .agencia("1234")
                .conta(5)
                .saldo(0)
                .idCliente(GenerateCliente.clienteValido())
                .listaMovimentacao(GenerateMovimentacao.listaValida())
                .build();
    }
    public static Conta contaListaValida2( int id) {
        return Conta.builder()
                .id(1)
                .agencia("1234")
                .conta(5)
                .saldo(0)
                .idCliente(Cliente.builder().id(id).build())
                .listaMovimentacao(GenerateMovimentacao.listaValida())
                .build();
    }

    public static Conta contaSaldoContaMenorZero() {
        return Conta.builder()
                .id(1)
                .agencia("1234")
                .conta(1)
                .saldo(-1)
                .idCliente(GenerateCliente.clienteValido())
                .build();
    }
}
