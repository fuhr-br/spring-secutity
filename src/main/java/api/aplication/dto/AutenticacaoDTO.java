package api.aplication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AutenticacaoDTO(
    @NotBlank(message = "Nome não foi informado") String nome,
    @NotBlank(message = "Senha não foi informada") String senha
) {

}