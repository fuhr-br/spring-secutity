CREATE TABLE USUARIO (
    id VARCHAR(255) PRIMARY KEY NOT NULL,
    login VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL,
    data_expiracao_credencial DATE,
    conta_esta_expirada BOOLEAN DEFAULT FALSE NOT NULL,
    conta_esta_bloqueada BOOLEAN DEFAULT FALSE NOT NULL,
    usuario_ativo BOOLEAN DEFAULT TRUE NOT NULL
);