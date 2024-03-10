package larcerto.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDTO(
    @NotBlank(message = "Nome não foi informado") String nome,
    @NotBlank(message = "Senha não foi informada") String senha
) {

}