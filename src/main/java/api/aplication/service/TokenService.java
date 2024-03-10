package api.aplication.service;

import api.aplication.model.Usuario;

public interface TokenService {

  String gerarToken(Usuario usuario);

  String validarToken(String token);

}
