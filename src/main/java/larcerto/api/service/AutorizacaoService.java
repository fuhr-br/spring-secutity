package larcerto.api.service;

import larcerto.api.dto.AutenticacaoDTO;
import larcerto.api.dto.RegistroDTO;
import larcerto.api.dto.TokenDTO;

public interface AutorizacaoService {

  TokenDTO login(AutenticacaoDTO autenticacaoDTO);

  void cadastrar(RegistroDTO registroDTO);
}
