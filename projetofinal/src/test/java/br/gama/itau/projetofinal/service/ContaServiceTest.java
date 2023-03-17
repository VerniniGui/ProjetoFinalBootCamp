package br.gama.itau.projetofinal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

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

    @Mock
    private Conta conta;

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

        ContaDto contaCriada = service.adiconarConta(GenerateConta.contaAgenciaInvalida());

        assertThat(contaCriada).isNull();

    }

    @Test
    public void adicionarConta_returnNull_whenCodigoAgenciaInvalida() {

        ContaDto contaCriada = service.adiconarConta(GenerateConta.contaCodigoAgenciaInvalida());

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

        List<MovimentacaoDto> novaLista = service.recuperarMovimentacoes(1);

        assertThat(novaLista).isNull();
        verify(repo, Mockito.times(1)).findById(1);
    }

    // @Test
    // public void recuperarMovimentacoes_returnListaMovimentacao_whenIdValido() {
    // BDDMockito.when(repo.findById(ArgumentMatchers.any(Integer.class)))
    // .thenReturn(Optional.of(GenerateConta.contaValida()));
    // BDDMockito.when(conta.getListaMovimentacao())
    // .thenReturn((GenerateMovimentacao.listaValida()));

    // List<MovimentacaoDto> novaLista = service.recuperarMovimentacoes(1);

    // assertThat(novaLista).isNotNull();
    // verify(repo, Mockito.times(1)).findById(1);
    // }

    @Test
    public void sacar_returnTrue_WhenIdContaEValorValidos() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Integer.class)))
                .thenReturn(Optional.of(GenerateConta.contaValida()));
        BDDMockito.when(repo.save(ArgumentMatchers.any(Conta.class)))
                .thenReturn((GenerateConta.contaValida()));

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

}
