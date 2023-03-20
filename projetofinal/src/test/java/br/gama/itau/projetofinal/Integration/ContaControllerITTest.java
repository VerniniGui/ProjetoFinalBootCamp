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
        contaRepo.deleteAll();
        clienteRepo.deleteAll();
        movimentacaoRepo.deleteAll();
    }

    @Test
    public void recuperaPeloNumero_returnContaDto_whenNumeroValido() throws Exception {
        Cliente novoCliente = GenerateCliente.clienteValido();
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
        Cliente novoCliente = GenerateCliente.clienteValido();
        Cliente cliente = clienteRepo.save(novoCliente);

        Conta novaConta = GenerateConta.novaConta(cliente.getId());
        Conta contaCriada = contaRepo.save(novaConta);

        ResultActions resposta = mockMvc.perform(post("/contas")
                        .content(objectMapper.writeValueAsString(contaCriada))
                        .contentType(MediaType.APPLICATION_JSON));

        resposta.andExpect(status().isOk());
                

    }

    // @Test
    // public void getTodasMovimentacao() throws Exception {
    //     Cliente novoCliente = GenerateCliente.clienteValido();
    //     Cliente cliente = clienteRepo.save(novoCliente);

    //     Conta novaConta = GenerateConta.novaConta(cliente.getId());
    //     Conta contaCriada = contaRepo.save(novaConta);

    //     movimentacaoRepo.save(GenerateMovimentacao.movimentacaoValida());

    //     ResultActions resposta = mockMvc.perform(get("/contas/movimentacao/{id}", contaCriada.getId())
    //             .contentType(MediaType.APPLICATION_JSON));

    //     resposta.andExpect(status().isOk());
    // }

    // @Test
    // public void getMovimentacaoByPeriodo_retornaListMovimentacao_whenPeriodoValido () throws Exception {
    //     Cliente novoCliente = GenerateCliente.clienteValido();
    //     Cliente cliente = clienteRepo.save(novoCliente);

    //     Conta novaConta = GenerateConta.contaListaValida2(cliente.getId());
    //     Conta contaCriada = contaRepo.save(novaConta);

    //     // Movimentacao mov1 = movimentacaoRepo.save(GenerateMovimentacao.movimentacaoDataValida1(contaCriada.getId()));
    //     // Movimentacao mov2 = movimentacaoRepo.save(GenerateMovimentacao.movimentacaoDataValida2(contaCriada.getId()));

    //     LocalDate data1 = LocalDate.of(2023, 03, 17);
    //     LocalDate data2 = LocalDate.of(2023, 03, 18);

    //     ResultActions resposta = mockMvc.perform(get("/conta/movimentacao/periodo/{id},{dataInicio},{dataFinal}", contaCriada.getId(), data1, data2)
    //             .contentType(MediaType.APPLICATION_JSON));

    //     resposta.andExpect(status().isOk());


    // }


}
