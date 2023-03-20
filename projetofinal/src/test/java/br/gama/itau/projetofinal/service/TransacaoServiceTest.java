package br.gama.itau.projetofinal.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.repository.ContaRepo;
import br.gama.itau.projetofinal.util.GenerateConta;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService service;

    @Mock
    private ContaService contaService;

    @Mock
    private MovimentacaoService movimentacaoService;

    @Mock
    private ContaRepo contaRepo;

    @Test
    public void sacar_returnTrue_whenDadosValidos() {
        BDDMockito.when(contaService.sacar(ArgumentMatchers.any(Double.class), ArgumentMatchers.any(Integer.class)))
                .thenReturn(true);

        Conta conta = GenerateConta.contaValida();
        boolean resposta = service.sacar(conta.getId(), 100);

        assertThat(resposta).isTrue();

    }

    @Test
    public void sacar_returnTrue_whenDadosInvalidos() {
        BDDMockito.when(contaService.sacar(ArgumentMatchers.any(Double.class), ArgumentMatchers.any(Integer.class)))
                .thenReturn(false);

        Conta conta = GenerateConta.contaValida();
        boolean resposta = service.sacar(conta.getId(), 100);

        assertThat(resposta).isFalse();

    }
}
