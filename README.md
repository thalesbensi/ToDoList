# 📋 To-Do List API

Uma API RESTful de gerenciamento de tarefas desenvolvida com **Java** e **Spring Boot**.  
Este projeto foi criado para consolidar conhecimentos em desenvolvimento Back-end, aplicando boas práticas e tecnologias amplamente utilizadas no mercado.

---

## 🚀 Funcionalidades

- **Criar Tarefa**: Registre uma nova tarefa no sistema.
- **Listar Tarefas**: Veja todas as tarefas cadastradas.
- **Buscar Tarefa por ID**: Encontre uma tarefa específica.
- **Atualizar Tarefa**: Modifique informações de uma tarefa existente.
- **Excluir Tarefa**: Remova uma tarefa do sistema.
- **Autenticação JWT**: Proteja rotas com autenticação por token.
- **Documentação Automática**: Endpoints documentados com **Swagger/OpenAPI**.
- **Validações**: Verificação de campos obrigatórios e consistência de dados.
- **Tratamento de Erros**: Respostas padronizadas para erros comuns.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot** (Spring Web, Spring Data JPA, Spring Security)
- **MySQL** (ou H2 para ambiente de desenvolvimento)
- **Lombok**
- **Swagger/OpenAPI**
- **Postman** (para testes)
- **Maven** (gerenciamento de dependências)

---

## ⚙️ Como Configurar o Projeto

### 1. Pré-requisitos
- **Java 17+** instalado
- **Maven** instalado
- **MySQL** configurado

### 2. Clone o Repositório
```bash
git clone https://github.com/seu-usuario/todo-list-api.git
cd todo-list-api
````
### 3. Configure o Banco de Dados
No arquivo `src/main/resources/application.properties`, configure as credenciais do banco de dados conforme o ambiente:  

**Exemplo de configuração para MySQL:**  
```properties
spring.datasource.url=jdbc:mysql://localhost:8080/todolist
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
````
### 4. Compile e Execute o Projeto 
```bash
mvn spring-boot:run
````
A aplicação estará disponível em: `http://localhost:8080`

---

## 🔧 Endpoints 

### Tarefas

  - **POST /tasks**: Cria uma nova tarefa.
    Exemplo de payload:
    
```json
{
    "title": "Estudar Spring Boot",
    "description": "Aprender mais sobre APIs RESTful"
}
```

- **GET /tasks**: Lista todas as tarefas.

- **GET /tasks/{id}**: Busca uma tarefa pelo ID.

- **PUT /tasks/{id}**: Atualiza uma tarefa existente.
Exemplo de payload:
```json
{
    "title": "Estudar Spring Boot",
    "description": "Aprender mais sobre APIs RESTful",
    "status": "COMPLETED"
}
```

- **PUT /tasks/done/{id}**: Atualiza o status da tarefa para **COMPLETED**

- **DELETE /tasks/{id}**: Exclui uma tarefa pelo ID.

### Autenticação

- **POST /auth/register**: Registra um usuário na base de dados.
    Exemplo de payload:
```json
{
  "login": "string",
  "password": "string",
  "role": "ADMIN"
}
```
#### Observação: 
 A "role", para fim de estudos, pode ser registrada como USER ou ADMIN, o usuário tipo USER só pode fazer requisições do tipo GET, já o usuário ADMIN pode fazer todo tipo de requisição.

- **POST /auth/login**: Faz login na API, assim recebendo um Token válido por 2 horas.
    Exemplo de payload:
```json
{
  "login": "string",
  "password": "string"
}
```

## 📝 Documentação da API

A API possui documentação automática gerada pelo Swagger.
Acesse em: `http://localhost:8080/swagger-ui.html` para visualizar todos os endpoints e suas funcionalidades.

---

## 🔐 Autenticação

A API utiliza JWT (JSON Web Token) para autenticação.
Inclua o token no cabeçalho das requisições protegidas:
```bash
Authorization: Bearer {seu_token_jwt}
````

---


## ✅ Próximos Passos
- **Implementar testes automatizados com JUnit e Mockito.**
- **Contêinerizar a aplicação com Docker.**

---
 
 ## 🤝 Contribuição

Contribuições são bem-vindas!
Sinta-se à vontade para abrir **issues** ou enviar um **pull request** com melhorias. 

---

## 💬 Vamos conversar!
Se você tiver dúvidas ou sugestões, entre em contato comigo pelo LinkedIn.



