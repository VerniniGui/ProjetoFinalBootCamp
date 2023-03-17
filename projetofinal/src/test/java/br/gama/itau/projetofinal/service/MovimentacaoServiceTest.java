package br.gama.itau.projetofinal.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.gama.itau.projetofinal.model.Movimentacao;
import br.gama.itau.projetofinal.repository.MovimentacaoRepo;
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

    

}
