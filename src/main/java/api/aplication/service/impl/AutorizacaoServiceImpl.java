package api.aplication.service.impl;

import api.aplication.dto.AutenticacaoDTO;
import api.aplication.dto.RegistroDTO;
import api.aplication.service.TokenService;
import api.aplication.dto.TokenDTO;
import api.aplication.model.Usuario;
import api.aplication.service.AutorizacaoService;
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
