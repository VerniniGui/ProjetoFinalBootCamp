package br.gama.itau.projetofinal.util;

import br.gama.itau.projetofinal.model.Cliente;

import java.util.ArrayList;
import java.util.List;

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
                .agencia("1234")
                .conta(1)
                .saldo(100)
                .idCliente(GenerateCliente.clienteNovo())
                .build();
    }

    public static Conta contaValida() {
        return Conta.builder()
                .id(1)
                .agencia("1234")
                .conta(1)
                .saldo(100)
                .idCliente(GenerateCliente.clienteNovo())
                .build();
    }

    public static Conta contaAgenciaInvalida() {
        return Conta.builder()
                .id(1)
                .agencia("123")
                .conta(1)
                .saldo(100)
                .idCliente(GenerateCliente.clienteNovo())
                .build();
    }

    public static Conta contaCodigoAgenciaInvalida() {
        return Conta.builder()
                .id(1)
                .agencia("0000")
                .conta(1)
                .saldo(100)
                .idCliente(GenerateCliente.clienteNovo())
                .build();
    }
    
    public static Conta contaTipoInvalida() {
        return Conta.builder()
                .id(1)
                .agencia("1234")
                .conta(5)
                .saldo(100)
                .idCliente(GenerateCliente.clienteNovo())
                .build();
    }

    public static Conta contaSaldoContaInvalido() {
        return Conta.builder()
                .id(1)
                .agencia("1234")
                .conta(5)
                .saldo(0)
                .idCliente(GenerateCliente.clienteNovo())
                .build();
    }

    public static Conta contaListaValida() {
        return Conta.builder()
                .id(1)
                .agencia("1234")
                .conta(5)
                .saldo(0)
                .idCliente(GenerateCliente.clienteNovo())
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
                .idCliente(GenerateCliente.clienteNovo())
                .build();
    }


    public static List<Conta> ListaDeContaValida() {
        List<Conta> listaContas = new ArrayList<>();
        
       Conta conta1 = Conta.builder()
                .id(1)
                .agencia("1234")
                .conta(1)
                .saldo(-1)
                .idCliente(GenerateCliente.clienteNovo())
                .build();

        Conta conta2 = Conta.builder()
                .id(2)
                .agencia("1234")
                .conta(1)
                .saldo(-1)
                .idCliente(GenerateCliente.clienteNovo())
                .build();

        listaContas.add(conta1);
        listaContas.add(conta2);        

        
        return listaContas;        
    }

    public static List<Conta> ListaDeContaValidaSemCliente() {
        List<Conta> listaContas = new ArrayList<>();
       Conta conta1 = Conta.builder()
                .id(1)
                .agencia("1234")
                .conta(1)
                .saldo(100)
                .idCliente(Cliente.builder()
                    .id(1)
                    .nomeCliente("ClienteValido 1").build())
                .build();

        Conta conta2 = Conta.builder()
                .id(2)
                .agencia("1234")
                .conta(1)
                .saldo(200)
                .idCliente(Cliente.builder()
                    .id(1)
                    .nomeCliente("ClienteValido 1").build())
                .build();

        listaContas.add(conta1);
        listaContas.add(conta2);        

        
        return listaContas;        
    }
}

