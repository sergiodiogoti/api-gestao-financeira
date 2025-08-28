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

    
---

## 📑 Endpoints disponíveis

### Usuário
| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/usuarios` | Lista todos os usuários |
| GET | `/usuarios/{id}` | Consulta usuário por ID |
| POST | `/usuarios` | Cria um novo usuário |
| PUT | `/usuarios/{id}` | Atualiza um usuário existente |
| DELETE | `/usuarios/{id}` | Exclui usuário |
| PATCH | `/usuarios/inativar/{id}` | Inativa usuário |
| GET | `/usuarios/perfil/{perfil}` | Busca usuários por perfil (ignore case) |
| GET | `/usuarios/renda?min=X&max=Y` | Busca usuários por faixa de renda |

### Conta
| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/contas` | Lista todas as contas |
| GET | `/contas/{id}` | Consulta conta por ID |
| POST | `/contas` | Cria nova conta |
| PUT | `/contas/{id}` | Atualiza conta existente |
| DELETE | `/contas/{id}` | Exclui conta |
| PATCH | `/contas/marcar-principal/{id}` | Marca conta como principal |
| GET | `/contas/cpf/{cpf}/tipo/{tipo}` | Busca contas por CPF e tipo |
| GET | `/contas/cpf/{cpf}/saldo-maior/{valor}` | Busca contas com saldo maior que valor |

---

## 📄 Arquivos de dados (Loaders)
- `dataFile/usuario.txt`  
- `dataFile/conta.txt`


---

## ⚡ Instruções para rodar
1. Clonar o repositório:
```bash
git clone <URL_DO_REPOSITORIO>

mvn spring-boot:run


