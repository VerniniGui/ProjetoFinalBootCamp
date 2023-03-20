package br.gama.itau.projetofinal.Integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gama.itau.projetofinal.model.Cliente;
import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.repository.ClienteRepo;
import br.gama.itau.projetofinal.repository.ContaRepo;
import br.gama.itau.projetofinal.util.GenerateCliente;
import br.gama.itau.projetofinal.util.GenerateConta;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteControllerITTest {
    

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private ContaRepo contaRepo;

    @BeforeEach
    public void setup() {
        clienteRepo.deleteAll();
        contaRepo.deleteAll();
    }


    @Test
    public void recuperarTodos_returnListClienteDto_whenSuccess() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        lista.add(GenerateCliente.novoCliente());
        lista.add(GenerateCliente.novoCliente2());

        clienteRepo.saveAll(lista);

        ResultActions resposta = mockMvc.perform(get("/clientes").contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",CoreMatchers.is(lista.size())))
                .andExpect(jsonPath("$[0].cpf", CoreMatchers.is(GenerateCliente.novoCliente().getCpf()))) ;
    }

    @Test
    public void recuperaPeloId_returnCliente_whenIdValido() throws Exception {
        Cliente novoCliente = GenerateCliente.novoCliente();

        Cliente clienteCriado = clienteRepo.save(novoCliente);

        ResultActions resposta = mockMvc.perform(get("/clientes/{id}", clienteCriado.getId())
                                        .contentType(MediaType.APPLICATION_JSON));
        
        resposta.andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf", CoreMatchers.is(clienteCriado.getCpf())));

    }

    @Test
    public void cadastrarCliente_returnOk_whenClienteValido() throws Exception {
        Cliente novoCliente = GenerateCliente.novoCliente();
        Cliente clienteCriado = clienteRepo.save(novoCliente);

        ResultActions resposta = mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(clienteCriado)));

        resposta.andExpect(status().isOk());

    }

    @Test
    public void recuperaContasClientes_returnListContas_whenIdValido() throws Exception {
        Cliente clienteValido = GenerateCliente.clienteNovo2();

        Cliente clienteCriado = clienteRepo.save(clienteValido);

        List<Conta> listaContas = new ArrayList<>();
        listaContas.add(GenerateConta.novaConta(clienteCriado.getId()));
        listaContas.add(GenerateConta.novaConta(clienteCriado.getId()));
        
        System.out.println(listaContas);

        contaRepo.saveAll(listaContas);


        ResultActions resposta = mockMvc.perform(get("/clientes/contas/{id}", clienteCriado.getId())
                                        .contentType(MediaType.APPLICATION_JSON));
        
        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(listaContas.size())));

    }







}
