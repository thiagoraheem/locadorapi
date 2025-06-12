# Documentação da Classe AuthController

## Visão Geral

A classe `AuthController` é um controlador REST responsável por gerenciar as operações de autenticação na aplicação. Ela expõe endpoints que permitem aos usuários se autenticarem e receberem tokens JWT (JSON Web Token) para acessar recursos protegidos da API. Este controlador é a porta de entrada para o sistema de segurança da aplicação, permitindo que usuários obtenham credenciais válidas para interagir com os demais endpoints protegidos.

## Localização no Projeto

```
com.locador.api.controller.AuthController
```

## Propósito

O principal propósito da classe `AuthController` é:

1. Receber e processar requisições de autenticação dos usuários
2. Validar credenciais de usuário através do Spring Security
3. Gerar tokens JWT para usuários autenticados
4. Retornar tokens JWT para clientes, permitindo acesso subsequente a recursos protegidos

Este controlador é essencial para implementar o fluxo de autenticação baseado em tokens, permitindo que a aplicação seja stateless (sem estado) e escalável.

## Anotações

```java
@RestController
@RequestMapping("/api/auth")
public class AuthController {
```

- **@RestController**: Esta anotação combina `@Controller` e `@ResponseBody`, indicando que a classe é um controlador cujos métodos retornam objetos que serão automaticamente serializados para o formato apropriado (geralmente JSON) na resposta HTTP. Isso elimina a necessidade de anotar cada método com `@ResponseBody`.

- **@RequestMapping("/api/auth")**: Esta anotação define o caminho base para todos os endpoints expostos por este controlador. Neste caso, todos os endpoints começarão com "/api/auth".

## Dependências Injetadas

```java
@Autowired private AuthenticationManager authManager;
@Autowired private JwtService jwtService;
```

- **AuthenticationManager**: Um componente do Spring Security responsável por processar requisições de autenticação. Ele delega a autenticação para um ou mais `AuthenticationProvider`s configurados.

- **JwtService**: Um serviço personalizado responsável por operações relacionadas a tokens JWT, como geração, validação e extração de informações.

## Endpoints

### login

```java
@PostMapping("/login")
public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
            )
    );

    UserDetails user = (UserDetails) authentication.getPrincipal();
    String token = jwtService.generateToken(user);
    return ResponseEntity.ok(new AuthResponse(token));
}
```

Este endpoint processa requisições de login:

- **Método HTTP**: POST
- **Caminho**: /api/auth/login
- **Corpo da Requisição**: Um objeto `AuthRequest` contendo nome de usuário e senha
- **Resposta**: Um objeto `AuthResponse` contendo o token JWT

O método realiza as seguintes operações:

1. Recebe um objeto `AuthRequest` contendo as credenciais do usuário (nome de usuário e senha)
2. Cria um token de autenticação (`UsernamePasswordAuthenticationToken`) com as credenciais fornecidas
3. Passa o token para o `AuthenticationManager` para validação
   - Se as credenciais forem inválidas, o `AuthenticationManager` lançará uma exceção (como `BadCredentialsException`)
   - Se as credenciais forem válidas, o `AuthenticationManager` retornará um objeto `Authentication` contendo os detalhes do usuário autenticado
4. Extrai o objeto `UserDetails` do resultado da autenticação
5. Usa o `JwtService` para gerar um token JWT para o usuário
6. Retorna o token JWT em um objeto `AuthResponse` com status HTTP 200 (OK)

## Fluxo de Autenticação

O fluxo de autenticação envolvendo o `AuthController` funciona da seguinte forma:

1. Um cliente (como um navegador ou aplicativo móvel) envia uma requisição POST para "/api/auth/login" com um nome de usuário e senha
2. O `AuthController` recebe a requisição e extrai as credenciais
3. O `AuthController` passa as credenciais para o `AuthenticationManager` para validação
4. O `AuthenticationManager` valida as credenciais contra o banco de dados (através de `UserDetailsService` e `PasswordEncoder`)
5. Se as credenciais forem válidas, o `AuthController` gera um token JWT usando o `JwtService`
6. O `AuthController` retorna o token JWT para o cliente
7. O cliente armazena o token JWT (geralmente em localStorage ou cookies)
8. O cliente inclui o token JWT no cabeçalho de autorização de requisições subsequentes
9. O `TokenFilter` valida o token JWT em requisições subsequentes e configura o contexto de segurança

## Integração com Outros Componentes

### AuthRequest

O `AuthRequest` é um DTO (Data Transfer Object) que encapsula as credenciais do usuário:

```java
public class AuthRequest {
    private String username;
    private String password;
    // getters e setters
}
```

### AuthResponse

O `AuthResponse` é um DTO que encapsula o token JWT retornado para o cliente:

```java
public class AuthResponse {
    private String token;
    // getters e setters
}
```

### JwtService

O `JwtService` fornece funcionalidades para trabalhar com tokens JWT:

- `generateToken(UserDetails user)`: Gera um token JWT para um usuário autenticado
- `extractUsername(String token)`: Extrai o nome de usuário de um token JWT
- `isValid(String token, UserDetails user)`: Verifica se um token JWT é válido para um determinado usuário
- `isExpired(String token)`: Verifica se um token JWT está expirado

### AuthenticationManager

O `AuthenticationManager` é um componente do Spring Security responsável por processar requisições de autenticação:

- `authenticate(Authentication auth)`: Valida as credenciais do usuário e retorna um objeto `Authentication` contendo os detalhes do usuário autenticado

## Considerações de Segurança

- O `AuthController` é uma parte crítica do sistema de segurança, pois é responsável por validar credenciais e emitir tokens JWT
- A implementação atual usa o `AuthenticationManager` do Spring Security para validar credenciais, o que é uma boa prática
- Os tokens JWT gerados pelo `JwtService` têm uma expiração de 1 hora, o que é um equilíbrio razoável entre segurança e conveniência
- O endpoint de login ("/api/auth/login") é acessível sem autenticação, o que é necessário para permitir que usuários não autenticados façam login
- O controlador não implementa proteção contra ataques de força bruta, o que poderia ser uma vulnerabilidade

## Extensibilidade

A implementação atual é focada no login básico, mas poderia ser estendida para incluir funcionalidades adicionais, como:

- Endpoint de registro para criar novos usuários
- Endpoint de atualização de token (refresh token) para renovar tokens expirados
- Endpoint de logout para invalidar tokens (requereria uma lista negra de tokens)
- Autenticação de dois fatores (2FA) para segurança adicional
- Integração com provedores de identidade externos (OAuth2, OpenID Connect)
- Limitação de taxa (rate limiting) para prevenir ataques de força bruta

## Conclusão

A classe `AuthController` desempenha um papel crucial no sistema de segurança da aplicação, fornecendo um endpoint para autenticação de usuários e emissão de tokens JWT. Ela integra-se com o Spring Security e com o `JwtService` para validar credenciais e gerar tokens, permitindo que a aplicação implemente autenticação baseada em tokens de forma segura e escalável. Sua simplicidade e foco em uma única responsabilidade (autenticação) estão alinhados com os princípios de design de API RESTful e com o princípio de responsabilidade única (SRP) do SOLID.