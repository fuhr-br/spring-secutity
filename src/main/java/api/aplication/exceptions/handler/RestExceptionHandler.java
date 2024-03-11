package api.aplication.exceptions.handler;

import api.aplication.exceptions.ExceptionDTO;
import api.aplication.exceptions.MethodArgumentNotValidExceptionDTO;
import api.aplication.exceptions.NotFoundException;
import api.aplication.exceptions.UsuarioCadastradoException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<MethodArgumentNotValidExceptionDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

    String campos = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
    String mensagemCampos = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

    return new ResponseEntity<>(
        MethodArgumentNotValidExceptionDTO.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .titulo(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .mensagem("Campos Inválidos")
            .dataHora(LocalDateTime.now())
            .campos(campos)
            .mensagemCampos(mensagemCampos)
            .build(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionDTO> handlerNotFound(NotFoundException exception) {

    return construirExceptionDTO(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({UsuarioCadastradoException.class, JWTCreationException.class})
  public ResponseEntity<ExceptionDTO> handlerUsuarioJaCadastrado(UsuarioCadastradoException exception) {

    return construirExceptionDTO(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(JWTVerificationException.class)
  public ResponseEntity<ExceptionDTO> handlerUsuarioJaCadastrado(JWTVerificationException exception) {

    return construirExceptionDTO(exception.getMessage(), HttpStatus.FORBIDDEN);
  }

  private ResponseEntity<ExceptionDTO> construirExceptionDTO(String mensagem, HttpStatus status) {

    log.error("Exception: {}", mensagem);

    return new ResponseEntity<>(
        ExceptionDTO.builder()
            .status(status.value())
            .titulo(status.getReasonPhrase())
            .mensagem(mensagem)
            .dataHora(LocalDateTime.now())
            .build(),
        status
    );
  }

}
