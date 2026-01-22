# Geospatial

API REST para gerenciamento de pessoas com funcionalidades de cálculo de idade e salário.

## 📋 Sobre a API

A API Geospatial oferece endpoints para gerenciar pessoas, permitindo criar, consultar, atualizar e deletar registros. Além disso, fornece funcionalidades especiais para calcular a idade de uma pessoa em diferentes unidades (anos, meses ou dias) e calcular o salário baseado no tempo de contratação.

### Endpoints Principais

- **GET** `/persons` - Lista todas as pessoas (ordenadas por nome)
- **POST** `/persons` - Cria uma nova pessoa
- **GET** `/persons/{id}` - Busca uma pessoa por ID
- **PUT** `/persons/{id}` - Atualiza uma pessoa completamente
- **PATCH** `/persons/{id}` - Atualiza parcialmente uma pessoa
- **DELETE** `/persons/{id}` - Remove uma pessoa
- **GET** `/persons/{id}/age?output={years|months|days}` - Calcula a idade da pessoa
- **GET** `/persons/{id}/salary?output={min|full}` - Calcula o salário da pessoa

A documentação completa da API está disponível via Swagger UI quando o projeto estiver em execução em: `http://localhost:8080/swagger-ui.html`

## 🛠️ Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 4.0.1** - Framework para desenvolvimento de aplicações Java
- **Spring Web MVC** - Framework para construção de APIs REST
- **SpringDoc OpenAPI 3.0** - Documentação automática da API (Swagger)
- **JUnit 5** - Framework de testes unitários
- **Mockito** - Framework para criação de mocks em testes
- **Maven** - Gerenciador de dependências e build

## 🚀 Como Rodar o Projeto

### Pré-requisitos

- Java 21 ou superior
- Maven 3.6+ instalado

### Executando a Aplicação

1. Clone o repositório:
```bash
git clone git@github.com:ewertondias/geospatial.git
cd geospatial
```

2. Compile o projeto:
```bash
mvn clean install
```

3. Execute a aplicação:
```bash
mvn spring-boot:run
```

Ou execute diretamente o JAR:
```bash
java -jar target/geospatial-0.0.1-SNAPSHOT.jar
```

4. A aplicação estará disponível em: `http://localhost:8080`

## 🧪 Como Rodar os Testes

Para executar todos os testes do projeto:

```bash
mvn test
```