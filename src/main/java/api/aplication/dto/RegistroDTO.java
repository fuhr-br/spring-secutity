package api.aplication.dto;

import api.aplication.model.enums.UsuarioRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record RegistroDTO(@NotBlank(message = "Nome não foi informado") String nome,
                          @NotBlank(message = "Senha não foi informada") String senha,
                          @NotNull(message = "dataExperiacao não pode ser nulo") LocalDate dataExperiacao,
                          @NotEmpty(message = "Deve conter ao menos uma role") List<UsuarioRole> roles) {

}
