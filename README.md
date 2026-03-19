# locadorapi
# 📦 API de Locação

Sistema backend desenvolvido em Java com Spring Boot para gerenciamento de locações, permitindo o controle de clientes, itens e contratos de aluguel.

---

## 🚀 Tecnologias utilizadas

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Banco de Dados (H2 / MySQL)
* Maven
* Git e GitHub

---

## 🧠 Arquitetura do Projeto

O projeto segue o padrão de arquitetura em camadas:

```
Controller → Service → Repository → Banco de Dados
```
organização dos pacotes:
├── config 
├── controller
├── service
├── repository
├── model
├── dto
├── security
├── enum
```

---

## 📌 Funcionalidades

* ✅ Cadastro de itens para locação
* ✅ Listagem de itens
* ✅ Atualização de dados
* ✅ Remoção de registros
* ✅ Busca de itens por nome
* ✅ Validação de dados
* ✅ Tratamento global de exceções

---

## 🔁 Endpoints principais

### 📥 Criar item

```
POST /itens
```

### 📄 Listar itens

```
GET /itens
```

### 🔍 Buscar item por nome

```
GET /itens/buscar?nome=xxx
```

### ✏️ Atualizar item

```
PUT /itens/{id}
```

### ❌ Deletar item

```
DELETE /itens/{id}
```

---

## 🧩 Conceitos aplicados

Este projeto foi desenvolvido com foco em boas práticas exigidas no mercado:

* Programação Orientada a Objetos (POO)
* Princípios SOLID:

  * Single Responsibility
  * Dependency Injection
* Separação de responsabilidades
* Uso de DTOs para comunicação
* Tratamento de exceções centralizado
* Código limpo e organizado (Clean Code)

---

## 🗄️ Banco de Dados

O projeto pode ser executado com:

* H2 (em memória, para testes)
* MySQL (produção)

Configuração feita no arquivo:

```
application.properties
```

---

## ▶️ Como executar o projeto

1. Clone o repositório:

```
git clone https://github.com/seu-usuario/seu-repositorio.git
```

2. Abra o projeto na sua IDE (IntelliJ ou Eclipse)

3. Execute a classe principal:

```
LocacaoApplication.java
```

4. A API estará disponível em:

```
http://localhost:8080
```

---

## 🧪 Testando a API

Você pode usar:

* Postman
* Insomnia

---

## 💡 Diferenciais do projeto

* Estrutura profissional em camadas
* Aplicação de boas práticas do mercado
* Código preparado para expansão
* Base sólida para sistemas maiores

---

## 📄 Autor

Desenvolvido por João Pedro
Estudante de Análise e Desenvolvimento de Sistemas
Foco em desenvolvimento backend com Java

---

## 📌 Observação

Este projeto foi desenvolvido com fins educacionais e para compor portfólio profissional.

