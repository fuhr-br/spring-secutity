package api.aplication.controller;

import api.aplication.dto.AutenticacaoDTO;
import api.aplication.fixture.AutenticacaoDTOFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static api.aplication.helper.SqlProvider.INSERT_USUARIO;
import static api.aplication.helper.SqlProvider.RESETAR_DB;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AutenticationControllerTest {

  @Autowired
  private MockMvc restClient;

  @Autowired
  private final ObjectMapper mapper = new ObjectMapper();

  AutenticacaoDTO autenticacaoDTO = AutenticacaoDTOFixture.builder();

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = RESETAR_DB)
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = INSERT_USUARIO)
  @DisplayName("Deve logar um usuario corretamente")
  void deveGerarUmTokenDeUsuario() throws Exception {

    String requisicao = mapper.writeValueAsString(autenticacaoDTO);

    this.restClient.perform(post("/v1/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requisicao))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.token").isString())
        .andExpect(jsonPath("$.token").isNotEmpty());

  }

  @Test
  @WithMockUser(username = "username", roles = {"USUARIO"})
  @DisplayName("Deve buscar um recurso")
  void deveBuscarUmRecurso() throws Exception {

    String requisicao = mapper.writeValueAsString(autenticacaoDTO);

    this.restClient.perform(get("/v1/api/auth")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requisicao))
        .andExpect(status().isOk())
        .andExpect(content().string("Busca com sucesso"));

  }

    @Test
    @WithMockUser(username = "username", roles = {"ADMINISTRADOR"})
    @DisplayName("Deve retornar código http  403 ao buscar um recurso com role invalida")
    void deveRecusarABuscaDeUmRecurso() throws Exception {

        String requisicao = mapper.writeValueAsString(autenticacaoDTO);

        this.restClient.perform(get("/v1/api/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requisicao))
            .andExpect(status().isForbidden());

    }

}