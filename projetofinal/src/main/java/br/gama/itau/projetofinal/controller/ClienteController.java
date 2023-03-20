package br.gama.itau.projetofinal.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gama.itau.projetofinal.dto.ClienteDto;
import br.gama.itau.projetofinal.dto.ContaDto;
import br.gama.itau.projetofinal.exception.MyNoSuchElementException;
import br.gama.itau.projetofinal.model.Cliente;
import br.gama.itau.projetofinal.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> recuperartodos() {
        List<ClienteDto> listaClientes = service.recuperarTodos();

        return ResponseEntity.ok(listaClientes);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> recuperaPeloId(@PathVariable int id) {
        ClienteDto cliente = service.recuperarPeloId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarcliente(@RequestBody Cliente cliente) {
        service.cadastrarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/contas/{id}")
    public ResponseEntity<List<ContaDto>> recuperaContasCliente(@PathVariable Integer id) {

        try {
            
            List<ContaDto> listaContas = service.recuperarContas(id);

            if(listaContas != null){
                return ResponseEntity.ok(listaContas);
            }
            
        } catch (NoSuchElementException e) {
            throw new MyNoSuchElementException("Nenhuma conta encontrada");
        }

        return ResponseEntity.notFound().build();
    }
}
