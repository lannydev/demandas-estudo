package domain;

import dto.ClienteRequestDTO;
import dto.ClienteResponseDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_id")
    private Long id;

    @Column(name = "co_nome")
    private String nome;

    @Column(name = "co_cpf")
    private String cpf;

    @Column(name = "co_telefone")
    private String telefone;


}
