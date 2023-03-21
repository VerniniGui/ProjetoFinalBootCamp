package br.gama.itau.projetofinal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.gama.itau.projetofinal.dto.ClienteDto;
import br.gama.itau.projetofinal.dto.ContaDto;
import br.gama.itau.projetofinal.dto.MovimentacaoDto;
import br.gama.itau.projetofinal.exception.MyNoSuchElementException;
import br.gama.itau.projetofinal.exception.MyNotFoundException;
import br.gama.itau.projetofinal.model.Conta;
import br.gama.itau.projetofinal.model.Movimentacao;
import br.gama.itau.projetofinal.repository.ContaRepo;

@Service

public class ContaService {

    @Autowired
    private ContaRepo repo;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MovimentacaoService movimentacaoService;

    //Cria uma conta e armazena em um banco de dados - retornando também o nome do Cliente responsavel pela conta
    //Se a Agência não for 0000
    //Se a Agencia não for diferente de 4
    //se a conta não for diferente de 1, 2 ou 3
    //se o saldo não for menor que 0
    //Se o lciente existir
    public ContaDto adiconarConta(Conta conta) {
        if (conta.getAgencia().equals("0000")) {
            return null;
        }

        if (conta.getAgencia().length() != 4) {
            return null;
        }

        if (conta.getConta() != 1 && conta.getConta() != 2 && conta.getConta() != 3) {
            return null;
        }

        if (conta.getSaldo() < 0) {
            return null;
        }

        if(conta.getIdCliente() == null){
            throw new DataIntegrityViolationException("Cliente invalido");
        }

        conta = repo.save(conta);
        ClienteDto cliente = clienteService.recuperarPeloId(conta.getIdCliente().getId());
        ContaDto contaDto = new ContaDto(conta);
        contaDto.setNomeCliente(cliente.getNomeCliente());

        return contaDto;
    }

    //Recupera a conta pelo id da conta
    //quando o id informado é valido
    public ContaDto recuperarPeloNumeroContaDto(int numero) {
        Conta conta = recuperarPeloNumero(numero);
        ContaDto contaDto = new ContaDto(conta);
        return contaDto;
    }

    //Recupera a conta pelo id da conta
    //quando o id informado é valido
    public Conta recuperarPeloNumero(Integer numero) {
        Optional<Conta>  optional = repo.findById(numero);
        Conta conta;
        try {
            conta = optional.get();
        } catch (NoSuchElementException e) {
            throw new MyNoSuchElementException("Conta não encontrada");
        }
        
        
        return conta;
    }

    // public Conta alterarDados(Conta conta, int id) {

    //     Optional<Conta> optional = repo.findById(id);
    //     if (optional.isEmpty()) {
    //         return null;

    //     }

    //     conta.setId(id);

    //     Conta contaAtualizada = repo.save(conta);

    //     return contaAtualizada;
    // }


    //Traz uma lista das movimentações referentes a uma conta específica
    //se o Id da conta existir
    public List<MovimentacaoDto> recuperarMovimentacoes(int id) {
        LocalDate dataInicio = LocalDate.of(1945, 01, 02); //Data quando o Itaú foi criado
        LocalDate dataFinal = LocalDate.now();

        return retornaHistoricoMovimentacaoPorData(id, dataInicio, dataFinal);
    }

    //Traz todas as movimentações de uma conta especifica em um período informado
    //Se o Id for valido
    public List<MovimentacaoDto> retornaHistoricoMovimentacaoPorData(int id, LocalDate dataInicio, LocalDate dataFinal) throws MyNotFoundException{

        Optional<Conta> contaOp = repo.findById(id);
        if(contaOp.isEmpty()){
            List<MovimentacaoDto> listaMoviDtoContaEmpty = new ArrayList<>();
            return listaMoviDtoContaEmpty;
        }

        Conta conta = contaOp.get();


        List<Movimentacao> listaMoviDto =  movimentacaoService.recuperarMovimentacaoPeriodo(conta, dataInicio, dataFinal);       
        List<MovimentacaoDto> listaMoviDtoConta = new ArrayList<>();

        for (Movimentacao movimentacao : listaMoviDto) {
            if(movimentacao.getConta().getId() == id){
                listaMoviDtoConta.add(new MovimentacaoDto(movimentacao));
            }
            
        }

        return listaMoviDtoConta;
    }

    //Ele recebe uma conta e um valor e realiza alteração no saldo da conta informada
    //Se a conta for encontrada e se o valor não for negativo
    //Se existir saldo suficiente na conta para realizar o saque.
    public boolean sacar (double valor, int id) {
        Optional<Conta> optional = repo.findById(id);

        if (optional.isPresent() && valor > 0) {
            Conta conta = optional.get();
            if (conta.getSaldo() <= valor) {
                return false;
            }
            conta.setSaldo(conta.getSaldo()-valor);
            repo.save(conta);
            return true;
        }
        return false;

    }

    //Ele recebe uma conta e um valor e realiza alteração no saldo da conta informada
    //Se a conta for encontrada e se o valor for maior do que 0   
    public boolean depositar (double valor, int id) {
        Optional<Conta> optional = repo.findById(id);
        if (optional.isPresent() && valor > 0) {
            Conta conta = optional.get();
            conta.setSaldo(conta.getSaldo()+valor);
            repo.save(conta);
            return true;
        }
        return false;

    }

}
