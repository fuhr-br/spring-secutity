package larcerto.api.controller;

import jakarta.validation.Valid;
import larcerto.api.dto.AutenticacaoDTO;
import larcerto.api.dto.RegistroDTO;
import larcerto.api.dto.TokenDTO;
import larcerto.api.service.AutorizacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/api/auth")
@RestController
@RequiredArgsConstructor
public class AutenticationController {

  private final AutorizacaoService autorizacaoService;

  @PostMapping("/login")
  public ResponseEntity<TokenDTO> login(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {

    return ResponseEntity.ok(autorizacaoService.login(autenticacaoDTO));
  }

  @PostMapping("/registrar")
  public ResponseEntity<String> registrar(@RequestBody @Valid RegistroDTO registroDTO) {

    autorizacaoService.cadastrar(registroDTO);
    return ResponseEntity.ok("Usuario cadastrado com sucesso");

  }

  @GetMapping
  public ResponseEntity<String> buscar() {

    return ResponseEntity.ok("Usuario cadastrado com sucesso");

  }

}
