package api.aplication.service.impl;

import api.aplication.dto.RegistroDTO;
import api.aplication.exceptions.UsuarioCadastradoException;
import api.aplication.model.Usuario;
import api.aplication.repository.UsuarioRepository;
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

    if (usuarioRepository.findByLogin(registroDTO.nome()) != null) {
      throw new UsuarioCadastradoException("Usuário já esta cadastrado, faça login ou " +
          "entre em contado com o suporte para recuperar sua credencial!");
    }
    return usuarioRepository.save(Usuario.builder()
        .password(new BCryptPasswordEncoder().encode(registroDTO.senha()))
        .login(registroDTO.nome())
        .roles(registroDTO.roles())
        .dataExpiracaoCredencial(registroDTO.dataExperiacao())
        .build());
  }

}
