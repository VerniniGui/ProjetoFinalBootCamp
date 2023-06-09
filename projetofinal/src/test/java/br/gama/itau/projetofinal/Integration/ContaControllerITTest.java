package br.gama.itau.projetofinal.Integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import br.gama.itau.projetofinal.repository.MovimentacaoRepo;
import br.gama.itau.projetofinal.util.GenerateCliente;
import br.gama.itau.projetofinal.util.GenerateConta;
import br.gama.itau.projetofinal.util.GenerateMovimentacao;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ContaControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ContaRepo contaRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private MovimentacaoRepo movimentacaoRepo;

    @BeforeEach
    public void setup () {
        movimentacaoRepo.deleteAll();
        contaRepo.deleteAll();
        clienteRepo.deleteAll();
    }

    @Test
    public void recuperaPeloNumero_returnContaDto_whenNumeroValido() throws Exception {
        Cliente novoCliente = GenerateCliente.clienteNovo2();
        Cliente cliente = clienteRepo.save(novoCliente);

        Conta novaConta = GenerateConta.novaConta(cliente.getId());

        Conta contaCriada = contaRepo.save(novaConta);

        

        ResultActions resposta = mockMvc.perform(get("/contas/{id}", contaCriada.getId()).contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isOk())
                .andExpect(jsonPath("$.agencia", CoreMatchers.is(contaCriada.getAgencia())))
                .andExpect(jsonPath("$.conta", CoreMatchers.is(contaCriada.getConta())));
    }

    @Test
    public void adicionarConta_returnOk_wheContaValida() throws Exception {
        Cliente novoCliente = GenerateCliente.clienteNovo2();
        Cliente cliente = clienteRepo.save(novoCliente);

        Conta novaConta = GenerateConta.novaConta(cliente.getId());
        Conta contaCriada = contaRepo.save(novaConta);

        ResultActions resposta = mockMvc.perform(post("/contas")
                        .content(objectMapper.writeValueAsString(contaCriada))
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isOk());
                

    }

    
    @Test
    public void getTodasMovimentacao() throws Exception {
        Cliente novoCliente = GenerateCliente.clienteNovo2();
        Cliente cliente = clienteRepo.save(novoCliente);

        Conta novaConta = GenerateConta.novaConta(cliente.getId());
        Conta contaCriada = contaRepo.save(novaConta);

        movimentacaoRepo.save(GenerateMovimentacao.movimentacaoDataValida1(contaCriada.getId()));

        ResultActions resposta = mockMvc.perform(get("/contas/movimentacao/{id}", contaCriada.getId())
                .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isOk());
    }

    @Test
    public void getMovimentacaoByPeriodo_retornaListMovimentacao_whenPeriodoValido () throws Exception {
        Cliente novoCliente = GenerateCliente.clienteNovo2();
        Cliente cliente = clienteRepo.save(novoCliente);

        Conta novaConta = GenerateConta.novaConta(cliente.getId());
        Conta contaCriada = contaRepo.save(novaConta);

        movimentacaoRepo.save(GenerateMovimentacao.movimentacaoDataValida1(contaCriada.getId()));
        movimentacaoRepo.save(GenerateMovimentacao.movimentacaoDataValida2(contaCriada.getId()));

        ResultActions resposta = mockMvc.perform(get("/contas/movimentacao/periodo/{id},{dataInicio},{dataFinal}", contaCriada.getId(), "2023-03-16", "2023-03-19")
                .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isOk());


    }


}
