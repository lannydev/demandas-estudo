package controller;

import dto.ClienteRequestDTO;
import dto.ClienteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO response = clienteService.salvarCliente(clienteRequestDTO);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }




}
