# SGHSS - Sistema de Gestão Hospitalar e de Serviços de Saúde

API REST para um sistema de gestão hospitalar, permitindo o gerenciamento de médicos, pacientes, consultas e prescrições.

## Tecnologias Utilizadas

- **Java 21**: Versão mais recente do Java, garantindo performance e acesso a features modernas da linguagem.
- **Spring Boot 3.5**: Framework principal para a construção da aplicação, facilitando a configuração e o desenvolvimento.
- **Spring Data JPA**: Para persistência de dados, abstraindo o acesso ao banco de dados.
- **Spring Security**: Para controle de autenticação e autorização, garantindo a segurança da API.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar os dados da aplicação.
- **Flyway**: Ferramenta para versionamento e migração do banco de dados.
- **Maven**: Gerenciador de dependências e build do projeto.
- **JWT (JSON Web Token)**: Para autenticação stateless e segura.
- **Lombok**: Para reduzir código boilerplate em entidades e DTOs.
- **MapStruct**: Para mapeamento eficiente entre DTOs e entidades.

## Funcionalidades

- **Gerenciamento de Médicos**: Cadastro, atualização, listagem e inativação de médicos.
- **Gerenciamento de Pacientes**: Cadastro, atualização e inativação de pacientes.
- **Agendamento de Consultas**: Marcação e cancelamento de consultas.
- **Prescrições Médicas**: Criação de prescrições associadas a uma consulta.
- **Autenticação e Autorização**: Sistema de login com diferentes perfis de acesso (ADMIN, MEDICO, USUARIO).

## Como Executar o Projeto

### Pré-requisitos

- JDK 21 ou superior
- Maven 3.8 ou superior
- Docker e Docker Compose (Recomendado para o banco de dados) ou uma instância do PostgreSQL em execução.

### Configuração

1.  **Clone o repositório:**
    ```bash
    git clone <url-do-repositorio>
    cd sghss
    ```

2.  **Configure o Banco de Dados:**
    O projeto está configurado para se conectar a um banco de dados PostgreSQL. A forma mais fácil de subir um banco é usando o Docker.
    Crie um arquivo `docker-compose.yml` na raiz do projeto com o seguinte conteúdo:
    ```yaml
    version: '3.8'
    services:
      postgres:
        image: postgres:14.1
        container_name: sghss-db
        ports:
          - "5432:5432"
        environment:
          - POSTGRES_USER=postgres
          - POSTGRES_PASSWORD=123
          - POSTGRES_DB=sghss-uninter
        volumes:
          - postgres_data:/var/lib/postgresql/data

    volumes:
      postgres_data:
    ```
    Execute o comando para iniciar o container:
    ```bash
    docker-compose up -d
    ```

3.  **Configure as Variáveis de Ambiente:**
    O segredo do JWT pode ser configurado via variável de ambiente. Se não for definida, um valor padrão será usado (não recomendado para produção).
    ```bash
    export JWT_SECRET="seu-segredo-super-secreto"
    ```

### Execução

Utilize o Maven Wrapper para compilar e executar a aplicação:

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

## Endpoints da API

A seguir estão os principais endpoints disponíveis na API.

### Autenticação (`/auth`)

-   `POST /login`: Autentica um usuário e retorna um token JWT.
-   `POST /register`: Registra um novo usuário.

### Médicos (`/medicos`)

-   `GET /`: Lista todos os médicos ativos.
-   `GET /{id}`: Busca um médico pelo ID.
-   `POST /`: Cadastra um novo médico (Requer role `ADMIN`).
-   `PUT /{id}`: Atualiza as informações de um médico (Requer role `ADMIN` ou `MEDICO`).
-   `DELETE /{id}`: Inativa um médico (Requer role `ADMIN`).

### Pacientes (`/pacientes`)

-   `GET /{id}`: Busca um paciente pelo ID.
-   `POST /`: Cadastra um novo paciente.
-   `PUT /{id}`: Atualiza as informações de um paciente.
-   `DELETE /{id}`: Inativa um paciente (Requer role `ADMIN`).

### Consultas (`/consultas`)

-   `POST /`: Agenda uma nova consulta.
-   `GET /{id}`: Busca uma consulta pelo ID.
-   `DELETE /`: Cancela uma consulta.

### Prescrições (`/prescricoes`)

-   `POST /`: Cria uma nova prescrição para uma consulta (Requer role `ADMIN` ou `MEDICO`).

