package controller;

import dto.ClienteRequestDTO;
import dto.ClienteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ClienteService;

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



}
