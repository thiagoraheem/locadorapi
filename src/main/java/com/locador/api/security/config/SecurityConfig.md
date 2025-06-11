# Documentação da Classe SecurityConfig

## Visão Geral

A classe `SecurityConfig` é um componente central do sistema de segurança da aplicação, responsável por configurar e personalizar o comportamento do Spring Security. Ela define políticas de segurança, regras de autorização, gerenciamento de sessão, criptografia de senhas e outros aspectos fundamentais da segurança da aplicação.

## Localização no Projeto

```
com.locador.api.security.config.SecurityConfig
```

## Propósito

O principal propósito da classe `SecurityConfig` é:

1. Configurar o filtro de segurança do Spring Security
2. Definir políticas de acesso para diferentes endpoints da API
3. Configurar o gerenciamento de sessão
4. Configurar a proteção CSRF
5. Integrar componentes personalizados de segurança, como o filtro de token JWT
6. Configurar o gerenciador de autenticação
7. Definir o codificador de senha a ser utilizado

Esta classe é essencial para estabelecer um ambiente seguro para a aplicação, protegendo recursos sensíveis e permitindo acesso apenas a usuários autorizados.

## Anotações

```java
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
```

- **@Configuration**: Esta anotação marca a classe como uma fonte de definições de beans para o contexto de aplicação. Ela indica ao Spring que a classe contém métodos @Bean que devem ser processados pelo contêiner Spring para gerar definições e requisições de serviço de beans.

- **@EnableMethodSecurity**: Esta anotação habilita a segurança baseada em anotações em métodos, permitindo o uso de anotações como `@PreAuthorize`, `@PostAuthorize`, `@Secured`, etc. Isso possibilita a definição de regras de segurança em nível de método, oferecendo um controle granular sobre quem pode acessar quais funcionalidades.

## Dependências Injetadas

```java
@Autowired private TokenFilter tokenFilter;
@Autowired private AuthDetailsServiceImpl userService;
```

- **TokenFilter**: Um filtro personalizado que intercepta requisições HTTP para validar tokens JWT e autenticar usuários.
- **AuthDetailsServiceImpl**: Um serviço que implementa `UserDetailsService` do Spring Security, responsável por carregar os detalhes do usuário a partir do banco de dados.

## Métodos @Bean

### securityFilterChain

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    // libere TUDO em /api/auth/**
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/api/ping/**").permitAll()
                    // todo o resto exige autenticação
                    .anyRequest().authenticated()
            )
            .userDetailsService(userService)
            .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}
```

Este método configura a cadeia de filtros de segurança do Spring Security:

- **csrf(csrf -> csrf.disable())**: Desativa a proteção CSRF (Cross-Site Request Forgery). Isso é comum em APIs RESTful que usam autenticação baseada em tokens, pois os tokens JWT já fornecem proteção contra ataques CSRF.

- **sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))**: Configura a política de criação de sessão como STATELESS, indicando que a aplicação não manterá estado de sessão. Isso é apropriado para APIs RESTful e aplicações que usam autenticação baseada em tokens JWT.

- **authorizeHttpRequests(...)**: Configura as regras de autorização para diferentes endpoints:
  - `.requestMatchers("/api/auth/**").permitAll()`: Permite acesso irrestrito a todos os endpoints sob "/api/auth/", que geralmente incluem endpoints de login, registro e outras operações de autenticação.
  - `.requestMatchers("/api/ping/**").permitAll()`: Permite acesso irrestrito a todos os endpoints sob "/api/ping/", que provavelmente são endpoints de verificação de saúde ou disponibilidade da aplicação.
  - `.anyRequest().authenticated()`: Exige que todas as outras requisições sejam autenticadas.

- **userDetailsService(userService)**: Define o serviço que será usado para carregar os detalhes do usuário durante a autenticação.

- **addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)**: Adiciona o filtro de token JWT à cadeia de filtros de segurança, posicionando-o antes do filtro de autenticação por nome de usuário e senha. Isso garante que a autenticação baseada em token seja tentada antes da autenticação baseada em formulário.

### authManager

```java
@Bean
public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
}
```

Este método expõe o `AuthenticationManager` como um bean Spring:

- O `AuthenticationManager` é responsável por processar requisições de autenticação.
- Ele é obtido a partir da configuração de autenticação fornecida pelo Spring Security.
- Este bean é utilizado por outros componentes que precisam autenticar usuários, como controladores de autenticação.

### passwordEncoder

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

Este método define o codificador de senha a ser utilizado pela aplicação:

- O `BCryptPasswordEncoder` é uma implementação do `PasswordEncoder` que usa a função de hash BCrypt para codificar senhas.
- BCrypt é considerado seguro porque incorpora um salt aleatório para cada senha e é resistente a ataques de força bruta devido ao seu fator de trabalho ajustável.
- Este bean é utilizado durante a autenticação para verificar se a senha fornecida pelo usuário corresponde à senha armazenada no banco de dados.

## Fluxo de Segurança

O fluxo de segurança configurado por esta classe funciona da seguinte forma:

1. Uma requisição HTTP chega à aplicação.
2. O `TokenFilter` intercepta a requisição e verifica se ela contém um token JWT válido no cabeçalho de autorização.
3. Se o token for válido, o usuário é autenticado e a requisição continua.
4. Se o token for inválido ou ausente, a requisição continua sem autenticação.
5. As regras de autorização são aplicadas:
   - Se a requisição for para um endpoint em "/api/auth/**" ou "/api/ping/**", ela é permitida independentemente da autenticação.
   - Para todos os outros endpoints, a requisição é rejeitada se o usuário não estiver autenticado.
6. Se a autenticação for necessária e o usuário não estiver autenticado, uma resposta de erro 401 (Unauthorized) é retornada.

## Integração com Outros Componentes

### TokenFilter

O `TokenFilter` é integrado à cadeia de filtros de segurança através do método `addFilterBefore`. Ele é responsável por extrair e validar tokens JWT do cabeçalho de autorização e autenticar usuários com base nesses tokens.

### AuthDetailsServiceImpl

O `AuthDetailsServiceImpl` é configurado como o serviço de detalhes do usuário através do método `userDetailsService`. Ele é responsável por carregar os detalhes do usuário a partir do banco de dados durante a autenticação.

## Considerações de Segurança

- A proteção CSRF está desativada, o que é apropriado para APIs RESTful que usam autenticação baseada em tokens JWT.
- A política de sessão é configurada como STATELESS, o que é apropriado para aplicações que usam autenticação baseada em tokens JWT.
- O codificador de senha BCrypt é utilizado, o que é considerado seguro para armazenamento de senhas.
- Endpoints de autenticação e verificação de saúde são acessíveis sem autenticação, o que é necessário para permitir que usuários não autenticados façam login ou verifiquem a disponibilidade da aplicação.
- Todos os outros endpoints exigem autenticação, o que protege recursos sensíveis.

## Extensibilidade

A implementação atual é robusta, mas poderia ser estendida para incluir funcionalidades adicionais, como:

- Configuração de CORS (Cross-Origin Resource Sharing) para permitir requisições de diferentes origens.
- Implementação de regras de autorização baseadas em roles ou permissões, utilizando métodos como `.hasRole()`, `.hasAuthority()`, etc.
- Configuração de handlers personalizados para diferentes tipos de exceções de segurança.
- Implementação de mecanismos de rate limiting para prevenir ataques de força bruta.
- Configuração de headers de segurança adicionais, como Content-Security-Policy, X-XSS-Protection, etc.

## Conclusão

A classe `SecurityConfig` desempenha um papel crucial no sistema de segurança da aplicação, configurando e personalizando o comportamento do Spring Security. Ela define políticas de segurança, regras de autorização, gerenciamento de sessão, criptografia de senhas e outros aspectos fundamentais da segurança da aplicação. Sua integração com o `TokenFilter` e o `AuthDetailsServiceImpl` forma uma solução de segurança completa e robusta para a aplicação.