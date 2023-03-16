package br.gama.itau.projetofinal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (length = 100)
    private String nomeCliente;
    @Column (length = 20, unique = true)
    private String cpf;
    @Column (length = 20, unique = true)
    private String telefone;

    @OneToMany(mappedBy = "idCliente")
    @JsonIgnoreProperties("idCliente")
    private List<Conta> listaContas;

}