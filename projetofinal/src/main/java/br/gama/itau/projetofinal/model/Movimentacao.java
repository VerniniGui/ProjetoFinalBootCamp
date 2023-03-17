package br.gama.itau.projetofinal.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int num;

    @Column
    public LocalDate dataOperacao;

    @Column
    public double valor;

    @Column
    public int tipo;

    @Column
    public String descricao;

    @ManyToOne
    @JoinColumn(name = "numero_conta")
    @JsonIgnoreProperties("listaMovimentacao")
    private Conta conta;

}