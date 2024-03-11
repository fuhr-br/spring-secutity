package api.aplication.model.enums;

public enum UsuarioRole {
  ADMINISTRADOR("ADMINISTRADOR"),
  USUARIO("USUARIO");

  private String role;

  UsuarioRole(String role) {

    this.role = role;
  }

  public String getRole() {

    return this.role;
  }
}
