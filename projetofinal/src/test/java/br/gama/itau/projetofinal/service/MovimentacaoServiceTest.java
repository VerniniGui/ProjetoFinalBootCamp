package br.gama.itau.projetofinal.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.model.Movimentacao;
import br.gama.itau.projetofinal.repository.MovimentacaoRepo;
import br.gama.itau.projetofinal.util.GenerateConta;
import br.gama.itau.projetofinal.util.GenerateMovimentacao;

@ExtendWith(MockitoExtension.class)
public class MovimentacaoServiceTest {

    @InjectMocks
    private MovimentacaoService service;

    @Mock
    private MovimentacaoRepo repo;

    @Test
    public void cadastradaMovimentacao_returnMovimentacao_whenDadosvalidos() {
        BDDMockito.when(repo.save(ArgumentMatchers.any(Movimentacao.class)))
                .thenReturn(GenerateMovimentacao.movimentacaoValida());

        Movimentacao movimentacao = service.cadastrarMovimentacao(GenerateMovimentacao.novaMovimentacao());

        assertThat(movimentacao).isNotNull();

    }

    @Test
    public void cadastradaMovimentacao_returnMovimentacao_whenNumeroMovimentacaoIgualMenosUm() {
        BDDMockito.when(repo.save(ArgumentMatchers.any(Movimentacao.class)))
                .thenReturn(GenerateMovimentacao.movimentacaoValida());

        Movimentacao movimentacao = service.cadastrarMovimentacao(GenerateMovimentacao.movimentacaoIdMenosUm());

        assertThat(movimentacao).isNotNull();
    }

    @Test
    public void cadastradaMovimentacao_returnMovimentacao_whenValorMenorQueZero() {

        Movimentacao movimentacao = service.cadastrarMovimentacao(GenerateMovimentacao.movimentacaoValorMenosUm());

        assertThat(movimentacao).isNull();
    }

    @Test
    public void cadastradaMovimentacao_returnMovimentacao_whenTipoInvalido() {

        Movimentacao movimentacao = service.cadastrarMovimentacao(GenerateMovimentacao.movimentacaoTipoInvalido());

        assertThat(movimentacao).isNull();
    }

    @Test
    public void recuperarMovimentacaoPeriodo_returnlistaMovimentacao_whenQuandoAsDatasSaoValidas() {
        BDDMockito
                .when(repo.findByContaAndDataOperacaoBetween(ArgumentMatchers.any(Conta.class), ArgumentMatchers.any(LocalDate.class),
                        ArgumentMatchers.any(LocalDate.class)))
                .thenReturn(GenerateMovimentacao.listaValida());

        Conta conta = GenerateConta.contaValida();        
        LocalDate dataInicio = LocalDate.of(2023, 03, 15);
        LocalDate dataFinal = LocalDate.of(2023, 03, 15);
        List<Movimentacao> movimentacao = service.recuperarMovimentacaoPeriodo(conta, dataInicio, dataFinal);

        assertThat(movimentacao).isNotNull();
    }

}
