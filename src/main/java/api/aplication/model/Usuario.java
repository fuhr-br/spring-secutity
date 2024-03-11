package api.aplication.model;

import api.aplication.model.enums.UsuarioRole;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Usuario implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(unique = true, nullable = false)
  private String login;
  @Column(nullable = false)
  private String password;
  @Column(nullable = false, name = "data_expiracao_credencial")
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  private LocalDate dataExpiracaoCredencial;
  @Builder.Default
  @Column(nullable = false)
  private Boolean contaEstaExpirada = Boolean.FALSE;
  @Builder.Default
  @Column(nullable = false)
  private Boolean contaEstaBloqueada = Boolean.FALSE;
  @Builder.Default
  @Column(nullable = false)
  private Boolean usuarioAtivo = Boolean.TRUE;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "text[]", nullable = false)
  private List<UsuarioRole> roles;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    roles.forEach(role ->
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()))
    );

    return authorities;
  }

  @Override
  public String getUsername() {

    return this.login;
  }

  @Override
  public boolean isAccountNonExpired() {

    return !contaEstaExpirada;
  }

  @Override
  public boolean isAccountNonLocked() {

    return !contaEstaBloqueada;
  }

  @Override
  public boolean isCredentialsNonExpired() {

    return dataExpiracaoCredencial != null && !dataExpiracaoCredencial.isBefore(LocalDate.now());
  }

  @Override
  public boolean isEnabled() {

    return usuarioAtivo;
  }
}
