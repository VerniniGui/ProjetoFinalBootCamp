package br.gama.itau.projetofinal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import br.gama.itau.projetofinal.exception.MyDataIntegrityViolationException;
import br.gama.itau.projetofinal.model.Cliente;
import br.gama.itau.projetofinal.repository.ClienteRepo;
import br.gama.itau.projetofinal.repository.ContaRepo;
import br.gama.itau.projetofinal.util.GenerateCliente;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService service;

    @Mock
    private ClienteRepo repo;

    @Mock
    private ContaRepo contaRepo;

    @Test
    public void cadastrarCliente_returnNovoCliente_WhenDadosValidos() {
        BDDMockito.when(repo.save(ArgumentMatchers.any(Cliente.class)))
                .thenReturn(GenerateCliente.clienteNovo());

        Cliente novoCliente = GenerateCliente.novoCliente();
        Cliente clienteCriado = service.cadastrarCliente(novoCliente);

        assertThat(clienteCriado).isNotNull();
        assertThat(clienteCriado.getNomeCliente()).isEqualTo(novoCliente.getNomeCliente());

        verify(repo, Mockito.times(1)).save(novoCliente);
    }

    @Test // Exception
    public void cadastrarCliente_returnException_WhenDadosInvalidos() {

        Cliente novoCliente = GenerateCliente.clienteInvalido();    


        assertThrows(MyDataIntegrityViolationException.class, ()->{
            service.cadastrarCliente(novoCliente);
        });
    }

    @Test
    public void recuperarTodos_returnListaDeCliente_WhenExistemClientesCadastrados() {
        BDDMockito.when(repo.findAll())
                .thenReturn((GenerateCliente.listaDeClienteValida()));

        List<Cliente> listaValida = GenerateCliente.listaDeClienteValida();

        List<ClienteDto> listaClientesCriada = service.recuperarTodos();

        assertThat(listaClientesCriada).isNotNull();
        assertThat(listaClientesCriada.size()).isEqualTo(listaValida.size());
    }

    @Test
    public void recuperarPeloId_returnClienteValido_WhenIdValido() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Integer.class)))
                .thenReturn(Optional.of(GenerateCliente.clienteNovo()));

        ClienteDto clienteRecuperado = service.recuperarPeloId(1);

        assertThat(clienteRecuperado).isNotNull();
    }


    @Test
    public void recuperarContas_returnListaDeContas_whenIdValido() {
        BDDMockito.when(repo.findById(ArgumentMatchers.any(Integer.class)))
                .thenReturn(Optional.of(GenerateCliente.clienteValido()));
        

        List<ContaDto> ListaDeContas = service.recuperarContas(1);

        assertThat(ListaDeContas).isNotNull();

    }

}
