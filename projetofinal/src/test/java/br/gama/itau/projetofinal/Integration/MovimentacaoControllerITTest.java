package br.gama.itau.projetofinal.Integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import br.gama.itau.projetofinal.model.Movimentacao;
import br.gama.itau.projetofinal.repository.ClienteRepo;
import br.gama.itau.projetofinal.repository.ContaRepo;
import br.gama.itau.projetofinal.repository.MovimentacaoRepo;
import br.gama.itau.projetofinal.util.GenerateCliente;
import br.gama.itau.projetofinal.util.GenerateConta;
import br.gama.itau.projetofinal.util.GenerateMovimentacao;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovimentacaoControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MovimentacaoRepo movimentacaoRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private ContaRepo contaRepo;

    @BeforeEach
    public void setup() {
        movimentacaoRepo.deleteAll();
        clienteRepo.deleteAll();
        contaRepo.deleteAll();
    }

    // @Test
    // public void newMovimentacao_returnMovimentacao_whenDadosMovimentacaoValido() throws Exception {
    //     Cliente novoCliente = GenerateCliente.clienteValido();
    //     Cliente cliente = clienteRepo.save(novoCliente);

    //     Conta novaConta = GenerateConta.contaValidaCliente(cliente.getId());
    //     Conta contaCriada = contaRepo.save(novaConta);

    //     Movimentacao mov1 = GenerateMovimentacao.movimentacaoDataValida1(contaCriada.getId());
    //     Movimentacao movimentacaoCriada = movimentacaoRepo.save(mov1);

    //     ResultActions resposta = mockMvc.perform(post("/movimentacao")
    //                     .content(objectMapper.writeValueAsString(movimentacaoCriada))
    //                     .contentType(MediaType.APPLICATION_JSON));

    //     resposta.andExpect(status().isCreated());
    // }

}
