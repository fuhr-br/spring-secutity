package api.aplication.controller;

import api.aplication.dto.AutenticacaoDTO;
import api.aplication.dto.RegistroDTO;
import api.aplication.dto.TokenDTO;
import api.aplication.service.AutorizacaoService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/auth")
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
  @PreAuthorize("hasRole('USUARIO')")
  public ResponseEntity<String> buscar() {

    return ResponseEntity.ok("Busca com sucesso");

  }

}
