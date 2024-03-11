package api.aplication.dto;

import api.aplication.model.UsuarioRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record RegistroDTO(@NotEmpty(message = "Nome não foi informado") String nome,
                          @NotEmpty(message = "Senha não foi informada") String senha,
                          @NotNull LocalDate dataExperiacao,
                          @NotEmpty List<UsuarioRole> roles) {
}
