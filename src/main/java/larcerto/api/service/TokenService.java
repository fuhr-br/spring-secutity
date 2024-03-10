package larcerto.api.service;

import larcerto.api.model.Usuario;

public interface TokenService {

  String gerarToken(Usuario usuario);

  String validarToken(String token);

}
