package br.gama.itau.projetofinal.Integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import br.gama.itau.projetofinal.repository.MovimentacaoRepo;
import br.gama.itau.projetofinal.util.GenerateCliente;
import br.gama.itau.projetofinal.util.GenerateConta;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransacaoControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private ContaRepo contaRepo;

    @Autowired
    private MovimentacaoRepo movimentacaoRepo;

    @BeforeEach
    public void setup() {
        movimentacaoRepo.deleteAll();
        contaRepo.deleteAll();
        clienteRepo.deleteAll();

    }

    @Test
    public void sacarConta_returnIsAccepted_whenDadosValidos () throws Exception {
        Cliente novoCliente = GenerateCliente.clienteNovo2();
        Cliente cliente = clienteRepo.save(novoCliente);

        Conta novaConta = GenerateConta.novaConta(cliente.getId());
        Conta contaCriada = contaRepo.save(novaConta);

        ResultActions resposta = mockMvc.perform(post("/transacao/sacar/{id},{valor}", contaCriada.getId(), 50)
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isAccepted());
        
    }

    @Test
    public void depositarConta_returnIsAccepted_whenDadosValidos () throws Exception {
        Cliente novoCliente = GenerateCliente.clienteNovo2();
        Cliente cliente = clienteRepo.save(novoCliente);

        Conta novaConta = GenerateConta.novaConta(cliente.getId());
        Conta contaCriada = contaRepo.save(novaConta);

        ResultActions resposta = mockMvc.perform(post("/transacao/depositar/{id},{valor}", contaCriada.getId(), 50)
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isAccepted());
        
    }

    @Test
    public void transferirConta_returnIsAccepted_whenDadosValidos () throws Exception {
        Cliente novoCliente = GenerateCliente.clienteNovo2();
        Cliente cliente = clienteRepo.save(novoCliente);

        Conta novaConta = GenerateConta.novaConta(cliente.getId());
        Conta contaCriada = contaRepo.save(novaConta);

        Conta novaConta2 = GenerateConta.novaConta(cliente.getId());
        Conta contaCriada2 = contaRepo.save(novaConta2);

        ResultActions resposta = mockMvc.perform(post("/transacao/transferir/{idContaOrigem},{idContaDestino},{valor}", contaCriada.getId(),contaCriada2.getId(), 50)
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isAccepted());
        
    }




    
    
}
