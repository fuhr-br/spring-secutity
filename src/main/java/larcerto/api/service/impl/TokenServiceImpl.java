package larcerto.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import larcerto.api.model.Usuario;
import larcerto.api.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {

  @Value("${api.security.token.secret}")
  private String chaveSecreta;

  private final static String ISSUER = "lar-certo-auth-api";

  @Override
  public String gerarToken(Usuario usuario) {

    return JWT.create()
        .withIssuer(ISSUER)
        .withSubject(usuario.getUsername())
        .withExpiresAt(criarTempoExpericacaoToken())
        .withClaim("role", List.of(usuario.getRole().name()))
        .sign(gerarAlgorithm());
    //TODO:tratar possivel JWTCreationException
  }

  @Override
  public String validarToken(String token) {

    return JWT.require(gerarAlgorithm())
        .withIssuer(ISSUER)
        .build()
        .verify(token)
        .getSubject();
    //TODO:tratar possivel JWTVerificationException
  }

  private Algorithm gerarAlgorithm() {

    return Algorithm.HMAC256(chaveSecreta);
  }

  private Instant criarTempoExpericacaoToken() {

    return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
  }

}