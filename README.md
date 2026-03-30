# 🛒 API Loja - Spring Boot

API REST desenvolvida com Java e Spring Boot para gerenciamento de produtos e usuários, com autenticação básica e segurança de senha.

---

## 🚀 Funcionalidades

* Cadastro de usuários
* Login de usuários
* Criptografia de senha com BCrypt
* CRUD completo de produtos
* CRUD de usuários
* Padronização de respostas (ApiResponse)

---

## 🛠️ Tecnologias

* Java
* Spring Boot
* Spring Data JPA
* Spring Security
* MySQL
* Maven

---

## 🔐 Segurança

* Senhas criptografadas com BCrypt
* Validação de login

---

## 📌 Endpoints principais

### Auth

* POST /auth/register
* POST /auth/login

### Produtos

* GET /produtos
* POST /produtos
* PUT /produtos/{id}
* DELETE /produtos/{id}

---
## 📷 Exemplos de uso

Use ferramentas como Postman para testar os endpoints.

## ▶️ Como rodar o projeto

1. Clonar o repositório
2. Rodar o projeto no IntelliJ
3. A API estará disponível em:
   http://localhost:8080
