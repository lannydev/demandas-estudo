package controller;

import domain.Cliente;
import dto.ClienteRequestDTO;
import dto.ClienteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ClienteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/salvar")
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO response = clienteService.salvarCliente(clienteRequestDTO);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarClientePorId(
            @PathVariable Long id,
            @RequestBody ClienteRequestDTO clienteRequestDTO) {

        ClienteResponseDTO response = clienteService.atualizarClientePorId(id, clienteRequestDTO);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> buscarClientePorCpf(@PathVariable String cpf) {
        Cliente cliente = clienteService.buscarPorCpf(cpf);
        if (cliente == null) {
            ClienteResponseDTO response = new ClienteResponseDTO();
            response.setHttpStatus(HttpStatus.NOT_FOUND);
            response.setMensagem("Não existe cadastro para o CPF informado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Map<String, String>>> listarClientes() {
        List<Cliente> clientes = clienteService.listarTodosClientes();
        List<Map<String, String>> response = clientes.stream().map(c -> {
            Map<String, String> mapa = new HashMap<>();
            mapa.put("idCliente", c.getId().toString());
            mapa.put("nome", c.getNome());
            mapa.put("telefone", c.getTelefone());
            return mapa;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<ClienteResponseDTO> deletarCliente(@PathVariable Long idCliente) {
        ClienteResponseDTO response = clienteService.deletarCliente(idCliente);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }




}
