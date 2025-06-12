# Documentação da Classe TokenFilter

## Visão Geral

A classe `TokenFilter` é um componente crucial do sistema de segurança da aplicação, responsável por interceptar requisições HTTP e validar tokens JWT (JSON Web Token). Ela estende a classe `OncePerRequestFilter` do Spring Security, garantindo que o filtro seja executado apenas uma vez por requisição.

## Localização no Projeto

```
com.locador.api.security.token.TokenFilter
```

## Propósito

O principal propósito da classe `TokenFilter` é:

1. Interceptar todas as requisições HTTP que chegam à aplicação
2. Extrair e validar tokens JWT do cabeçalho de autorização
3. Autenticar usuários com base nos tokens válidos
4. Configurar o contexto de segurança do Spring com as informações do usuário autenticado

Este filtro é essencial para implementar a autenticação baseada em tokens, permitindo que a aplicação seja stateless (sem estado) e escalável.

## Anotações

```java
@Component
public class TokenFilter extends OncePerRequestFilter {
```

- **@Component**: Esta anotação marca a classe como um componente gerenciado pelo Spring, permitindo que ela seja automaticamente detectada durante a varredura de componentes e injetada onde necessário.

## Herança

A classe `TokenFilter` estende `OncePerRequestFilter`, que é uma classe abstrata do Spring Security que garante que o filtro seja executado apenas uma vez por requisição, independentemente de como os filtros estão configurados. Isso é importante para evitar a execução duplicada do filtro em configurações complexas de servlet.

## Dependências Injetadas

```java
@Autowired private JwtService jwtService;
@Autowired private AuthDetailsServiceImpl userService;
```

- **JwtService**: Um serviço responsável por operações relacionadas a tokens JWT, como geração, validação e extração de informações.
- **AuthDetailsServiceImpl**: Um serviço que implementa `UserDetailsService` do Spring Security, responsável por carregar os detalhes do usuário a partir do banco de dados.

## Métodos Implementados

### shouldNotFilter

```java
@Override
protected boolean shouldNotFilter(HttpServletRequest request) {
    return request.getServletPath().startsWith("/api/auth/");
}
```

Este método determina se o filtro deve ser aplicado a uma requisição específica:

- Retorna `true` se o caminho da requisição começar com "/api/auth/", indicando que o filtro não deve ser aplicado
- Retorna `false` para todos os outros caminhos, indicando que o filtro deve ser aplicado

Isso é útil para excluir endpoints de autenticação (como login e registro) do processo de validação de token, já que esses endpoints são acessados antes que o usuário tenha um token.

### doFilterInternal

```java
@Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain chain)
        throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userService.loadUserByUsername(username);
            if (jwtService.isValid(token, user)) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
    }

    chain.doFilter(request, response);
}
```

Este é o método principal do filtro, que é executado para cada requisição (exceto aquelas excluídas por `shouldNotFilter`). O método realiza as seguintes operações:

1. Extrai o cabeçalho de autorização da requisição
2. Verifica se o cabeçalho existe e começa com "Bearer " (o prefixo padrão para tokens JWT)
3. Extrai o token removendo o prefixo "Bearer "
4. Usa o `JwtService` para extrair o nome de usuário do token
5. Verifica se o nome de usuário foi extraído com sucesso e se o usuário ainda não está autenticado
6. Carrega os detalhes completos do usuário usando o `AuthDetailsServiceImpl`
7. Verifica se o token é válido para o usuário usando o `JwtService`
8. Se o token for válido, cria um objeto de autenticação (`UsernamePasswordAuthenticationToken`) com os detalhes do usuário e suas autoridades
9. Configura detalhes adicionais da autenticação, como informações da requisição
10. Define a autenticação no contexto de segurança do Spring, efetivamente autenticando o usuário
11. Finalmente, passa a requisição para o próximo filtro na cadeia

## Fluxo de Autenticação

O fluxo de autenticação envolvendo o `TokenFilter` funciona da seguinte forma:

1. Um usuário faz login através de um endpoint em "/api/auth/" (que não passa pelo filtro)
2. O endpoint de login gera um token JWT para o usuário autenticado
3. O usuário inclui esse token no cabeçalho de autorização de requisições subsequentes
4. O `TokenFilter` intercepta essas requisições e valida o token
5. Se o token for válido, o usuário é autenticado para a duração da requisição
6. Se o token for inválido ou ausente, a requisição continua sem autenticação e provavelmente será rejeitada por outros componentes de segurança

## Configuração no SecurityConfig

O `TokenFilter` é configurado no `SecurityConfig` da seguinte forma:

```java
.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
```

Isso adiciona o `TokenFilter` à cadeia de filtros de segurança, posicionando-o antes do `UsernamePasswordAuthenticationFilter`. Isso é importante porque:

- O `UsernamePasswordAuthenticationFilter` é responsável pela autenticação baseada em formulário
- Ao posicionar o `TokenFilter` antes, garantimos que a autenticação baseada em token seja tentada primeiro
- Se a autenticação baseada em token for bem-sucedida, a autenticação baseada em formulário não será necessária

## Relação com Outras Classes

### JwtService

O `JwtService` fornece funcionalidades para trabalhar com tokens JWT:

- `extractUsername(String token)`: Extrai o nome de usuário do token
- `isValid(String token, UserDetails user)`: Verifica se o token é válido para um determinado usuário

### AuthDetailsServiceImpl

O `AuthDetailsServiceImpl` carrega os detalhes do usuário a partir do banco de dados:

- `loadUserByUsername(String username)`: Busca um usuário pelo nome de usuário e o converte em um objeto `UserDetails`

### SecurityContextHolder

O `SecurityContextHolder` é uma classe do Spring Security que mantém informações sobre o contexto de segurança atual:

- `getContext().getAuthentication()`: Obtém a autenticação atual
- `getContext().setAuthentication(auth)`: Define a autenticação para o contexto atual

## Considerações de Segurança

- O `TokenFilter` é uma parte crítica do sistema de segurança, pois é responsável por validar tokens e autenticar usuários
- A implementação atual verifica se o token não está expirado e se o nome de usuário no token corresponde ao usuário carregado do banco de dados
- O filtro não é aplicado a endpoints de autenticação ("/api/auth/"), o que é importante para evitar um loop de autenticação
- O filtro não interrompe a cadeia de filtros se a autenticação falhar, permitindo que outros mecanismos de autenticação sejam tentados

## Extensibilidade

A implementação atual é robusta, mas poderia ser estendida para incluir funcionalidades adicionais, como:

- Verificação de lista negra de tokens (para tokens revogados)
- Suporte a tokens de atualização (refresh tokens)
- Logging de tentativas de autenticação para fins de auditoria
- Tratamento de exceções mais detalhado para diferentes tipos de falhas de token

## Conclusão

A classe `TokenFilter` desempenha um papel crucial no sistema de segurança da aplicação, implementando a autenticação baseada em tokens JWT. Ela intercepta requisições, valida tokens e configura o contexto de segurança do Spring, permitindo que a aplicação seja stateless e escalável. Sua integração com o `JwtService` e o `AuthDetailsServiceImpl` forma uma solução de autenticação completa e segura.