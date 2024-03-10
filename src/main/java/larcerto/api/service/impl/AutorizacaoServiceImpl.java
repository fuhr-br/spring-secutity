package larcerto.api.service.impl;

import larcerto.api.dto.AutenticacaoDTO;
import larcerto.api.dto.RegistroDTO;
import larcerto.api.dto.TokenDTO;
import larcerto.api.model.Usuario;
import larcerto.api.service.AutorizacaoService;
import larcerto.api.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorizacaoServiceImpl implements AutorizacaoService {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsServiceImpl userDetailsService;

  private final TokenService tokenService;

  @Override
  public TokenDTO login(AutenticacaoDTO autenticacaoDTO) {

    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(autenticacaoDTO.nome(), autenticacaoDTO.senha());

    Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    String token = tokenService.gerarToken((Usuario) authenticate.getPrincipal());
    return new TokenDTO(token);
  }

  @Override
  public void cadastrar(RegistroDTO registroDTO) {

    userDetailsService.cadastrar(registroDTO);
  }
}
