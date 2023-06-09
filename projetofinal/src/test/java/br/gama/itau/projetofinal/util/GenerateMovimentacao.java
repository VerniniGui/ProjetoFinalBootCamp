package br.gama.itau.projetofinal.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.model.Movimentacao;

public class GenerateMovimentacao {
    public static List<Movimentacao> listaValida() {
        List<Movimentacao> listaValida = new ArrayList<>();

        Movimentacao novaMovi = new Movimentacao(1,
                LocalDate.of(2023, 03, 17),
                200.0,
                1,
                "Teste1",
                GenerateConta.contaValida());
        Movimentacao novaMov2 = new Movimentacao(2,
                LocalDate.of(2023, 03, 18),
                200.0,
                1,
                "Teste1",
                GenerateConta.contaValida());

        listaValida.add(novaMovi);
        listaValida.add(novaMov2);

        return listaValida;
    }

    public static List<Movimentacao> listaVazia(){
        List<Movimentacao> listaVazia = new ArrayList<>();

        return listaVazia;
    }


    public static Movimentacao movimentacaoValida() {
       
        Movimentacao novaMovi = new Movimentacao(1,
                LocalDate.now(),
                200.0,
                1,
                "Teste1",
                GenerateConta.contaValida());
        return novaMovi;
    }

    public static Movimentacao novaMovimentacao() {
       
        return Movimentacao.builder()
            .dataOperacao(LocalDate.of(2023, 03, 17))
            .valor(1)
            .tipo(1)
            .descricao("teste")
            .conta(GenerateConta.contaValida())
            .build();
    }


    public static Movimentacao movimentacaoIdMenosUm() {
       
        return Movimentacao.builder()
            .num(-1)
            .dataOperacao(LocalDate.of(2023, 03, 17))
            .valor(1)
            .tipo(1)
            .descricao("teste")
            .conta(GenerateConta.contaValida())
            .build();
    }

    public static Movimentacao movimentacaoValorMenosUm() {
       
        return Movimentacao.builder()
            .dataOperacao(LocalDate.of(2023, 03, 17))
            .valor(-1)
            .tipo(1)
            .descricao("teste")
            .conta(GenerateConta.contaValida())
            .build();
    }

    public static Movimentacao movimentacaoTipoInvalido() {
       
        return Movimentacao.builder()
            .dataOperacao(LocalDate.of(2023, 03, 17))
            .valor(1)
            .tipo(4)
            .descricao("teste")
            .conta(GenerateConta.contaValida())
            .build();
    }

    public static Movimentacao movimentacaoDataInvalida() {
       
        return Movimentacao.builder()
            .dataOperacao(LocalDate.of(20, 03, 17))
            .valor(1)
            .tipo(4)
            .descricao("teste")
            .conta(GenerateConta.contaValida())
            .build();
    }

    public static Movimentacao movimentacaoDataValida1(int id) {
       
        return Movimentacao.builder()
            .dataOperacao(LocalDate.of(2023, 03, 17))
            .valor(1)
            .tipo(1)
            .descricao("teste")
            .conta(Conta.builder().id(id).build())
            .build();
    }

    public static Movimentacao movimentacaoDataValida2(int id) {
       
        return Movimentacao.builder()
            .dataOperacao(LocalDate.of(2023, 03, 18))
            .valor(1)
            .tipo(1)
            .descricao("teste")
            .conta(Conta.builder().id(id).build())
            .build();
    }


}
