package api.aplication.service.impl;

import api.aplication.model.Usuario;
import api.aplication.model.enums.UsuarioRole;
import api.aplication.service.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImpl implements TokenService {

  @Value("${api.security.token.secret}")
  private String chaveSecreta;

  private static final String ISSUER = "auth-api";

  @Override
  public String gerarToken(Usuario usuario) {

    return JWT.create()
        .withIssuer(ISSUER)
        .withSubject(usuario.getUsername())
        .withExpiresAt(criarTempoExpericacaoToken())
        .withClaim("role", usuario.getRoles().stream().map(UsuarioRole::getRole).toList())
        .sign(gerarAlgorithm());
  }

  @Override
  public String validarToken(String token) {

    return JWT.require(gerarAlgorithm())
        .withIssuer(ISSUER)
        .build()
        .verify(token)
        .getSubject();
  }

  private Algorithm gerarAlgorithm() {

    return Algorithm.HMAC256(chaveSecreta);
  }

  private Instant criarTempoExpericacaoToken() {

    return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
  }

}