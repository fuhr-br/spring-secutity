package api.aplication.service;

import api.aplication.dto.AutenticacaoDTO;
import api.aplication.dto.RegistroDTO;
import api.aplication.dto.TokenDTO;

public interface AutorizacaoService {

  TokenDTO login(AutenticacaoDTO autenticacaoDTO);

  void cadastrar(RegistroDTO registroDTO);
}
