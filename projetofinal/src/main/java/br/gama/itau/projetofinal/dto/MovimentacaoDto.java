package br.gama.itau.projetofinal.dto;

import java.time.LocalDate;

import br.gama.itau.projetofinal.model.Movimentacao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovimentacaoDto {
    private LocalDate dataOperacao;
    private int tipo;
    private double valor;
    private String descricao;

    public MovimentacaoDto(Movimentacao movimentacao) {
        this.dataOperacao = movimentacao.getDataOperacao();
        this.tipo = movimentacao.getTipo();
        this.valor = movimentacao.getValor();
        if (movimentacao.getDescricao() != null) {
            this.descricao = movimentacao.getDescricao();
        }
    }

}
