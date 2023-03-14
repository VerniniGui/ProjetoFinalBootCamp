package br.gama.itau.projetofinal.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Conta {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private int agencia;
private int conta;
private double saldo;

@OneToOne
@JoinColumn(name = "id_cliente")
@JsonIgnoreProperties ("listaContas")
private Cliente idCliente;

@OneToMany
@JsonIgnoreProperties("conta")
private List<Movimentacao> listaMovimentacao;

}
