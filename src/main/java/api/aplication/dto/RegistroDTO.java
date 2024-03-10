package api.aplication.dto;

import jakarta.validation.constraints.NotEmpty;
import api.aplication.model.UsuarioRole;

public record RegistroDTO(  @NotEmpty(message = "Nome não foi informado") String nome,
                            @NotEmpty(message = "Senha não foi informada") String senha,
                            @NotEmpty UsuarioRole role) {

}
