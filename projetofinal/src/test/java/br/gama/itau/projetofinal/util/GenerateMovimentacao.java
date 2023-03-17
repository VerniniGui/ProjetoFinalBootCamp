package br.gama.itau.projetofinal.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.gama.itau.projetofinal.model.Movimentacao;

public class GenerateMovimentacao {
    public static List<Movimentacao> listaValida() {
        List<Movimentacao> listaValida = new ArrayList<>();

        Movimentacao novaMovi = new Movimentacao(1,
                LocalDate.now(),
                200.0,
                1,
                "Teste1",
                GenerateConta.contaValida());
        Movimentacao novaMov2 = new Movimentacao(2,
                LocalDate.now(),
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
}
