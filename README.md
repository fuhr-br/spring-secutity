<p align="center">
  <h1 align="center">Spring Security Sample API Repository</h1>
</p>

<p align="center">

[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
<img src="https://img.shields.io/badge/Version-1.0.0-brightgreen.svg"/>
<img src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg"/>
</p>

[![Java CI com Gradle](https://github.com/fuhr-br/spring-secutity/actions/workflows/gradle.yml/badge.svg)](https://github.com/fuhr-br/spring-secutity/actions/workflows/gradle.yml)


- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Como Rodar Este Projeto](#como-rodar-este-projeto)

## Sobre o Projeto
<p>  Este repositório contém uma amostra de uma API desenvolvida com Spring Security.</p>
<p>A aplicação tem como objetivo demonstrar a implementação de medidas de segurança utilizando o Spring Security framework.</p>

## Funcionalidades
Autenticação e Autorização: A aplicação gerencia a autenticação e autorização de usuários, fornecendo tokens de acesso seguros para serem utilizados em outras partes do sistema.


## Tecnologias

| Objetivo | Tecnologia |
| ------ | ------ |
| Linguagem de programação | Java 17 |
| Banco de Dados | PostgreSQL 16 |
| Framework | SpringBoot  |
| Documentação | Swagger  |
| Monitoramento | Actuator  |
| Versionamento Banco | Flyway  |

## Como Rodar Este Projeto
Você apenas precisa de um banco postgreSQL na versão 16.0, existe um YAML de um container docker na raiz do projeto,
basta ter o docker instalado no seu computador e executar o comando abaixo para criar o container docker do banco:
````docker
docker-compose up -d
````
Após rodar o projeto normalmente que ele ficará disponível em:

``` Swagger
http://localhost:8080/swagger-ui/index.html#/
```
