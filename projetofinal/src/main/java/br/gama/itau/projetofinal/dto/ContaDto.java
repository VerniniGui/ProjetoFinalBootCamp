package br.gama.itau.projetofinal.dto;

import br.gama.itau.projetofinal.model.Conta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
public class ContaDto {

    private String agencia;
    private int conta;
    private double saldo;
    private String nomeCliente;

    public ContaDto (Conta conta){
        this.agencia = conta.getAgencia();
        this.conta = conta.getConta();
        this.saldo = conta.getSaldo();
        this.nomeCliente = conta.getIdCliente().getNomeCliente();
    }

    
}
