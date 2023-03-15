package br.gama.itau.projetofinal.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

@Column(nullable = false)
private String agencia;


@Column(nullable = false)

private int conta;


private double saldo;

@ManyToOne
@JoinColumn(name = "id_cliente")
@JsonIgnoreProperties ("listaContas")
private Cliente idCliente;

@OneToMany(mappedBy = "conta")
@JsonIgnoreProperties("conta")
private List<Movimentacao> listaMovimentacao;

}
