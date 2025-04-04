package dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ClienteResponseDTO {

    private HttpStatus httpStatus;
    private String mensagem;
}
