package br.gama.itau.projetofinal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.List;
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
import br.gama.itau.projetofinal.dto.MovimentacaoDto;
import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.model.Movimentacao;
import br.gama.itau.projetofinal.repository.ContaRepo;
import br.gama.itau.projetofinal.util.GenerateCliente;
import br.gama.itau.projetofinal.util.GenerateConta;
import br.gama.itau.projetofinal.util.GenerateMovimentacao;

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

    @Mock
    private Conta conta;

    @Test
    public void adicionarConta_returnNovaConta_whenContaValida() {
        BDDMockito.when(repo.save(ArgumentMatchers.any(Conta.class)))
                .thenReturn(GenerateConta.contaValida());

        BDDMockito.when(clienteService.recuperarPeloId(ArgumentMatchers.any(Integer.class)))
                .thenReturn(new ClienteDto(GenerateCliente.clienteNovo()));

        Conta novaConta = GenerateConta.novaConta2();

        ContaDto contaCriada = service.adiconarConta(novaConta);

        assertThat(contaCriada).isNotNull();
        assertThat(contaCriada.getSaldo()).isEqualTo(novaConta.getSaldo());
        assertThat(contaCriada.getAgencia()).isEqualTo(novaConta.getAgencia());

        verify(repo, Mockito.times(1)).save(novaConta);
    }

    @Test
    public void adicionarConta_returnNull_whenAgenciaInvalida() {

        ContaDto contaCriada = service.adiconarConta(GenerateConta.contaAgenciaInvalida());

        assertThat(contaCriada).isNull();

    }

    @Test
    public void adicionarConta_returnNull_whenCodigoAgenciaInvalida() {

        ContaDto contaCriada = service.adiconarConta(GenerateConta.contaCodigoAgenciaInvalida());

        assertThat(contaCriada).isNull();

    }

    @Test
    public void adicionarConta_returnNull_whenSaldoMenorZero() {

        ContaDto contaCriada = service.adiconarConta(GenerateConta.contaSaldoContaMenorZero());

        assertThat(contaCriada).isNull();

    }

    @Test
    public void adicionarConta_returnNull_whenTipoContaInvalida() {

        ContaDto contaCriada = service.adiconarConta(GenerateConta.contaCodigoAgenciaInvalida());

        assertThat(contaCriada).isNull();

    }

    @Test
    public void adicionarConta_returnNull_whenSaldoContaInvalido() {

        ContaDto contaCriada = service.adiconarConta(GenerateConta.contaSaldoContaInvalido());

        assertThat(contaCriada).isNull();

    }

    @Test
    public void recuperarPeloNumeroContaDto_returnNovaConta_whenContaValida() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Integer.class)))
                .thenReturn(Optional.of(GenerateConta.contaValida()));

        Conta contaValida = GenerateConta.contaValida();

        ContaDto contaCriada = service.recuperarPeloNumeroContaDto(1);

        assertThat(contaCriada).isNotNull();
        assertThat(contaCriada.getSaldo()).isEqualTo(contaValida.getSaldo());
        assertThat(contaCriada.getAgencia()).isEqualTo(contaValida.getAgencia());

        verify(repo, Mockito.times(1)).findById(1);
    }

    @Test
    public void recuperarMovimentacoes_returnNull_whenNaoExisteLista() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Integer.class)))
                .thenReturn(Optional.of(GenerateConta.contaValida()));
        BDDMockito
                .when(movimentacaoService.recuperarMovimentacaoPeriodo(ArgumentMatchers.any(Conta.class),
                        ArgumentMatchers.any(LocalDate.class),
                        ArgumentMatchers.any(LocalDate.class)))
                .thenReturn(GenerateMovimentacao.listaVazia());

        List<MovimentacaoDto> novaLista = service.recuperarMovimentacoes(1);

        assertThat(novaLista).isEmpty();

    }

    @Test
    public void recuperarMovimentacoes_returnListaMovimentacao_whenIdValido() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Integer.class)))
                .thenReturn(Optional.of(GenerateConta.contaValida()));
        BDDMockito
                .when(movimentacaoService.recuperarMovimentacaoPeriodo(ArgumentMatchers.any(Conta.class),
                        ArgumentMatchers.any(LocalDate.class),
                        ArgumentMatchers.any(LocalDate.class)))
                .thenReturn(GenerateMovimentacao.listaValida());

        List<Movimentacao> listaValida = GenerateMovimentacao.listaValida();
        List<MovimentacaoDto> novaLista = service.recuperarMovimentacoes(1);

        assertThat(novaLista).isNotNull();
        assertThat(novaLista.size()).isEqualTo(listaValida.size());

    }

    @Test
    public void recuperarHistoricoMovimentacoesPorData_returnListaMovimentacao_whenIdAndDataInicioAndDataFinalValidos() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Integer.class)))
                .thenReturn(Optional.of(GenerateConta.contaValida()));
        BDDMockito
                .when(movimentacaoService.recuperarMovimentacaoPeriodo(ArgumentMatchers.any(Conta.class), ArgumentMatchers.any(LocalDate.class),
                        ArgumentMatchers.any(LocalDate.class)))
                .thenReturn(GenerateMovimentacao.listaValida());

        Conta conta = GenerateConta.contaValida();        

        List<Movimentacao> listaValida = GenerateMovimentacao.listaValida();
        LocalDate dataInicio = LocalDate.of(2023, 03, 15);
        LocalDate dataFinal = LocalDate.of(2023, 03, 15);

        List<MovimentacaoDto> novaLista = service.retornaHistoricoMovimentacaoPorData(conta.getId(), dataInicio, dataFinal);

        assertThat(novaLista).isNotNull();
        assertThat(novaLista.size()).isEqualTo(listaValida.size());
        // verify(movimentacaoService,
        //         Mockito.times(1)).retornaHistoricoMovimentacaoPorData(conta.getId(), dataInicio, dataFinal);
    }

    @Test
    public void sacar_returnTrue_WhenIdContaEValorValidos() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Integer.class)))
                .thenReturn(Optional.of(GenerateConta.contaValida()));

        boolean resposta = service.sacar(50, 1);

        assertThat(resposta).isTrue();
    }

    @Test
    public void sacar_returnFalse_WhenValorInvalido() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Integer.class)))
                .thenReturn(Optional.of(GenerateConta.contaValida()));

        boolean resposta = service.sacar(200, 1);

        assertThat(resposta).isFalse();
    }

    @Test
    public void sacar_returnFalse_WhenIdInvalido() {

        boolean resposta = service.sacar(200, 1);

        assertThat(resposta).isFalse();
    }

    @Test
    public void depositar_returnTrue_WhenIdEValorValidos() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Integer.class)))
                .thenReturn(Optional.of(GenerateConta.contaValida()));

        boolean resposta = service.depositar(200, 1);

        assertThat(resposta).isTrue();
    }

    @Test
    public void depositar_returnFalse_WhenValorInvalido() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Integer.class)))
                .thenReturn(Optional.of(GenerateConta.contaSaldoContaInvalido()));

        boolean resposta = service.depositar(0, 1);

        assertThat(resposta).isFalse();
    }

    @Test
    public void depositar_returnFalse_WhenIdInvalido() {

        boolean resposta = service.depositar(200, 1);

        assertThat(resposta).isFalse();
    }

}
