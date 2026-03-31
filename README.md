# 🛒 API Loja - Spring Boot

API REST desenvolvida com Java e Spring Boot para gerenciamento de produtos e usuários, com autenticação básica e segurança de senha.
a# 🛒 API Loja - Sistema Completo

## 📌 Visão Geral

O projeto **API Loja** tem como objetivo evoluir para um sistema completo de e-commerce, com separação entre área de clientes e área administrativa.

A aplicação será dividida em:

* Backend (Spring Boot)
* Frontend (HTML, CSS, JavaScript)

---

## 🎯 Objetivo

Criar uma plataforma de loja virtual onde:

* Clientes possam visualizar e comprar produtos
* Administradores possam gerenciar produtos, usuários e pedidos

---

## 🧩 Arquitetura

O sistema é dividido em duas partes principais:

### 🔹 Backend (api-loja)

Responsável por:

* Regras de negócio
* Autenticação de usuários
* Persistência de dados
* API REST

### 🔹 Frontend (site-loja)

Responsável por:

* Interface do usuário
* Consumo da API
* Navegação entre páginas

---

## 👥 Tipos de Usuário

### 🧑 Cliente

* Criar conta
* Fazer login
* Visualizar produtos
* Adicionar ao carrinho
* Finalizar compra

### 🛠️ Administrador

* Fazer login
* Cadastrar produtos
* Editar produtos
* Deletar produtos
* Visualizar usuários
* Gerenciar pedidos

---

## 🚀 Funcionalidades Atuais

* Cadastro de usuários
* Login de usuários
* Criptografia de senha com BCrypt
* CRUD de produtos
* Padronização de respostas (ApiResponse)

---

## 🔮 Funcionalidades Futuras

### 🔐 Autenticação

* Implementação de JWT
* Controle de acesso por perfil (ADMIN / CLIENTE)

### 🛒 Loja

* Carrinho de compras
* Sistema de pedidos
* Histórico de compras

### 📦 Produtos

* Upload de imagem
* Categorias
* Filtro e busca

### 📊 Administração

* Dashboard administrativo
* Relatórios básicos

---

## 🛠️ Tecnologias Utilizadas

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Spring Security

### Frontend

* HTML
* CSS
* JavaScript

---

## 🔐 Segurança

* Senhas criptografadas com BCrypt
* (Futuro) Autenticação com JWT
* (Futuro) Controle de permissões

---

## 📌 Endpoints Principais

### Auth

* POST /auth/register
* POST /auth/login

### Produtos

* GET /produtos
* POST /produtos
* PUT /produtos/{id}
* DELETE /produtos/{id}

---

## 📈 Evolução do Projeto

Este projeto está em evolução contínua, com foco em aprendizado e aplicação de boas práticas de desenvolvimento backend e frontend.

---

## 💡 Autor

Arthur Silva

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
