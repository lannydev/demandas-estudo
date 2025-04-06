package service;

import domain.Cliente;
import dto.ClienteRequestDTO;
import dto.ClienteResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import repository.ClienteRepository;

@Service
public class ClienteService {

    private ClienteRepository repository;

    public ClienteResponseDTO salvarCliente(ClienteRequestDTO clienteRequestDTO) {
        if (repository.findAll().stream().anyMatch(c -> c.getCpf().equals(clienteRequestDTO.getCpf()))) {
            ClienteResponseDTO response = new ClienteResponseDTO();
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setMensagem("Já existe um cliente cadastrado com esse CPF.");
            return response;
        }

        Cliente cliente = clienteRequestDTO.toModel();
        repository.save(cliente);

        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setHttpStatus(HttpStatus.CREATED);
        response.setMensagem("Cliente cadastrado com sucesso");
        return response;
    }

}
