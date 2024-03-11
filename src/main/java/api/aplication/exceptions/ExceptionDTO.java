package api.aplication.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Getter
@SuperBuilder
public class ExceptionDTO {

  private int status;
  private String titulo;
  private String mensagem;
  private LocalDateTime dataHora;
}
