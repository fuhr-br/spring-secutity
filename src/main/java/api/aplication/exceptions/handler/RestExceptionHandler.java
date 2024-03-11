package api.aplication.exceptions.handler;

import api.aplication.exceptions.MethodArgumentNotValidExceptionDTO;
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




}
