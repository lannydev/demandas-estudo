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

    public ClienteResponseDTO atualizarCliente(ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteExistente = repository.findAll()
                .stream()
                .filter(c -> c.getCpf().equals(clienteRequestDTO.getCpf()))
                .findFirst()
                .orElse(null);

        ClienteResponseDTO response = new ClienteResponseDTO();

        if (clienteExistente == null) {
            response.setHttpStatus(HttpStatus.NOT_FOUND);
            response.setMensagem("Cliente não encontrado para o CPF informado.");
            return response;
        }

        clienteExistente.setNome(clienteRequestDTO.getNome());
        clienteExistente.setTelefone(clienteRequestDTO.getTelefone());
        repository.save(clienteExistente);

        response.setHttpStatus(HttpStatus.OK);
        response.setMensagem("Cliente atualizado com sucesso");
        return response;
    }


}
