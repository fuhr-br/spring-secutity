package api.aplication.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class MethodArgumentNotValidExceptionDTO extends ExceptionDTO {

  private final String campos;
  private final String mensagemCampos;
}
