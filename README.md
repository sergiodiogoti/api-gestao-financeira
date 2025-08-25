# 💰 API de Gestão Financeira Pessoal

Este projeto é um **microserviço de controle financeiro pessoal**, desenvolvido como trabalho acadêmico.  
A aplicação permite o gerenciamento de **usuários, contas, categorias e transações**, fornecendo uma API RESTful para CRUD completo.

---

## 🚀 Tecnologias Utilizadas

- **Java 17 (JDK 17)**
- **Spring Boot 3.5.4**
- **Maven 3.9.7**
- **Spring Data JPA**
- **Banco de dados H2** (em memória)
- **Postman** (para testes da API)

---

## 🗂️ Estrutura do Projeto

O sistema segue o padrão de **arquitetura em camadas**:

- **Model (Domain/Entity):** contém as entidades principais (`Pessoa`, `Usuario`, `Endereco`, `Conta`, `Categoria`, `Transacao`).
- **Repository:** interfaces que estendem `JpaRepository`, responsáveis pelo acesso ao banco de dados.
- **Service:** contém as regras de negócio e implementa operações de CRUD.
- **Controller:** expõe os endpoints REST para interação com clientes externos.
- **Exception:** exceções customizadas para regras de negócio (ex: `ContaNaoEncontradaException`).

---

## 🗄️ Banco de Dados H2

O projeto utiliza **H2 Database**, um banco em memória ideal para desenvolvimento e testes.

- Console H2 disponível em:  
  👉 [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

  - Configuração padrão (`application.properties`):
    ```properties
    # H2 Database config
      spring.datasource.driverClassName=org.h2.Driver
      spring.datasource.url=jdbc:h2:~/databaseSergio
      spring.datasource.username=sa
      spring.datasource.password=

      # JPA / Hibernate
      spring.jpa.database-plataform=org.hibernate.dialect.H2Dialect
      spring.jpa.hibernate.ddl-auto=create
      spring.jpa.show-sql=true

      # Console H2 (acesso em http://localhost:8080/h2-console)
      spring.h2.console.enabled=true
      spring.h2.console.path=/h2-console

