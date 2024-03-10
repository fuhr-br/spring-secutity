package larcerto.api.service.impl;

import larcerto.api.dto.RegistroDTO;
import larcerto.api.model.Usuario;
import larcerto.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    return usuarioRepository.findByLogin(username);
  }

  protected UserDetails cadastrar(RegistroDTO registroDTO) {
    //TODO criar handler
    if (usuarioRepository.findByLogin(registroDTO.nome()) != null) {
      throw new RuntimeException("Erro login");
    }
    return usuarioRepository.save(Usuario.builder()
        .password(new BCryptPasswordEncoder().encode(registroDTO.senha()))
        .login(registroDTO.nome())
        .role(registroDTO.role())
        .build());
  }

}
