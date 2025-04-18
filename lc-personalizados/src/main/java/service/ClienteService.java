package service;

import domain.Cliente;
import dto.ClienteRequestDTO;
import dto.ClienteResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import repository.ClienteRepository;

import java.util.List;

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


    public ClienteResponseDTO atualizarClientePorId(Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteExistente = repository.findById(id).orElse(null);
        ClienteResponseDTO response = new ClienteResponseDTO();

        if (clienteExistente == null) {
            response.setHttpStatus(HttpStatus.NOT_FOUND);
            response.setMensagem("Cliente com ID " + id + " não encontrado.");
            return response;
        }

        boolean cpfJaExiste = repository.findAll()
                .stream()
                .anyMatch(c -> !c.getId().equals(id) && c.getCpf().equals(clienteRequestDTO.getCpf()));

        if (cpfJaExiste) {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setMensagem("Já existe outro cliente cadastrado com esse CPF.");
            return response;
        }

        clienteExistente.setNome(clienteRequestDTO.getNome());
        clienteExistente.setCpf(clienteRequestDTO.getCpf());
        clienteExistente.setTelefone(clienteRequestDTO.getTelefone());
        repository.save(clienteExistente);

        response.setHttpStatus(HttpStatus.OK);
        response.setMensagem("Cliente atualizado com sucesso");
        return response;
    }

    public List<Cliente> listarTodosClientes() {
        return repository.findAll();
    }

    public Cliente buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf).orElse(null);
    }

    public ClienteResponseDTO deletarCliente(Long id) {
        ClienteResponseDTO response = new ClienteResponseDTO();

        if (!repository.existsById(id)) {
            response.setHttpStatus(HttpStatus.NOT_FOUND);
            response.setMensagem("Cliente não cadastrado.");
            return response;
        }

        repository.deleteById(id);
        response.setHttpStatus(HttpStatus.OK);
        response.setMensagem("Cliente deletado com sucesso.");
        return response;
    }




}
