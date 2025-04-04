package dto;

import domain.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestDTO {
    private String nome;
    private String cpf;
    private String telefone;

    public Cliente toModel() {
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setTelefone(this.telefone);
        return cliente;
    }


}
