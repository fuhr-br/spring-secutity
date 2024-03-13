package api.aplication.fixture;

import api.aplication.dto.AutenticacaoDTO;

public interface AutenticacaoDTOFixture {
    static AutenticacaoDTO builder() {

        return builderDefault().build();
    }

    private static AutenticacaoDTO.AutenticacaoDTOBuilder builderDefault() {
        return AutenticacaoDTO.builder().nome("anderson").senha("teste123");

    }
}
