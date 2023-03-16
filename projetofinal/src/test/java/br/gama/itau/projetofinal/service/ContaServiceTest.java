package br.gama.itau.projetofinal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.gama.itau.projetofinal.dto.ClienteDto;
import br.gama.itau.projetofinal.dto.ContaDto;
import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.repository.ClienteRepo;
import br.gama.itau.projetofinal.repository.ContaRepo;
import br.gama.itau.projetofinal.util.GenerateCliente;
import br.gama.itau.projetofinal.util.GenerateConta;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @InjectMocks
    private ContaService service;

    @Mock
    private ContaRepo repo;

    @Mock
    private ClienteService clienteService;

    @Mock
    private MovimentacaoService movimentacaoService;

    @Test
    public void adicionarConta_returnNovaConta_whenContaValida() {
        BDDMockito.when(repo.save(ArgumentMatchers.any(Conta.class)))
                .thenReturn(GenerateConta.contaValida());

        BDDMockito.when(clienteService.recuperarPeloId(ArgumentMatchers.any(Integer.class)))
                .thenReturn(new ClienteDto(GenerateCliente.clienteValido()));

        Conta novaConta = GenerateConta.novaConta();

        ContaDto contaCriada = service.adiconarConta(novaConta);

        assertThat(contaCriada).isNotNull();
        assertThat(contaCriada.getSaldo()).isEqualTo(novaConta.getSaldo());
        assertThat(contaCriada.getAgencia()).isEqualTo(novaConta.getAgencia());

        verify(repo, Mockito.times(1)).save(novaConta);
    }

    @Test
    public void adicionarConta_returnNull_whenAgenciaInvalida() {
        // BDDMockito.when(repo.save(ArgumentMatchers.any(Conta.class)))
        //         .thenReturn(null);
        ContaDto contaCriada = service.adiconarConta(GenerateConta.contaAgenciaInvalida());

        assertThat(contaCriada).isNull();

        // verify(repo, Mockito.times(1)).save(novaConta);
    }

}
