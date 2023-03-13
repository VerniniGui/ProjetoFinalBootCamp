package br.gama.itau.projetofinal.dto;

import br.gama.itau.projetofinal.model.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDto {

    private String nomeCliente;
    private String cpf;
    private String telefone;

    public ClienteDto(Cliente cliente) {
        this.nomeCliente = cliente.getNomeCliente();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
    }

}
