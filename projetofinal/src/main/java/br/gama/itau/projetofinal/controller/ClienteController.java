package br.gama.itau.projetofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gama.itau.projetofinal.dto.ClienteDto;
import br.gama.itau.projetofinal.model.Cliente;
import br.gama.itau.projetofinal.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;
    

    @GetMapping
    public ResponseEntity<List<ClienteDto>> recuperartodos(){
    List<ClienteDto> listaClientes = service.recuperarTodos();
    
    if(listaClientes.isEmpty()){
        return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(listaClientes);

    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteDto> recuperaPeloId(@PathVariable int id){
    return ResponseEntity.ok(service.recuperarPeloId(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarcliente(@RequestBody Cliente cliente){
        service.cadastrarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }
}