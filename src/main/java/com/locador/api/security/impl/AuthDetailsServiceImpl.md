# Documentação da Classe AuthDetailsServiceImpl

## Visão Geral

A classe `AuthDetailsServiceImpl` é uma implementação da interface `UserDetailsService` do Spring Security. Ela serve como uma ponte entre o sistema de autenticação do Spring Security e o repositório de usuários da aplicação, permitindo que o framework de segurança acesse os dados de usuário armazenados no banco de dados.

## Localização no Projeto

```
com.locador.api.security.impl.AuthDetailsServiceImpl
```

## Propósito

O principal propósito da classe `AuthDetailsServiceImpl` é carregar os detalhes do usuário a partir do banco de dados quando o Spring Security precisa autenticar um usuário. Ela converte o modelo de usuário da aplicação (`User`) em um objeto `UserDetails` que o Spring Security pode entender e utilizar para realizar autenticação e autorização.

## Anotações

```java
@Service
public class AuthDetailsServiceImpl implements UserDetailsService {
```

- **@Service**: Esta anotação marca a classe como um componente de serviço no contexto do Spring. Isso permite que o Spring gerencie o ciclo de vida da classe e a injete em outros componentes que a necessitem.

## Dependências Injetadas

```java
@Autowired
private UserRepository repo;
```

- **UserRepository**: Um repositório Spring Data JPA que fornece métodos para acessar e manipular dados de usuário no banco de dados. A interface `UserRepository` estende `JpaRepository<User, Integer>` e define um método adicional `findByUsername(String username)` que retorna um `Optional<User>`.

## Implementação

```java
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    return new AuthDetailsImpl(user);
}
```

A classe implementa o método `loadUserByUsername` da interface `UserDetailsService`, que:

1. Recebe um nome de usuário como parâmetro
2. Utiliza o `UserRepository` para buscar o usuário correspondente no banco de dados
3. Se o usuário não for encontrado, lança uma exceção `UsernameNotFoundException`
4. Se o usuário for encontrado, cria e retorna um objeto `AuthDetailsImpl` que encapsula o usuário encontrado

## Exceções

- **UsernameNotFoundException**: Esta exceção é lançada quando não é possível encontrar um usuário com o nome de usuário fornecido. Ela é uma subclasse de `AuthenticationException` e é tratada pelo Spring Security como uma falha de autenticação.

## Onde é Utilizada

A classe `AuthDetailsServiceImpl` é utilizada em dois contextos principais:

### 1. Na Configuração de Segurança

No `SecurityConfig`, a classe é injetada e configurada como o serviço de detalhes de usuário para o Spring Security:

```java
@Autowired private AuthDetailsServiceImpl userService;

// ...

.userDetailsService(userService)
```

Isso configura o Spring Security para usar esta implementação ao carregar detalhes de usuário durante o processo de autenticação.

### 2. No Filtro de Token

No `TokenFilter`, a classe é injetada e utilizada para carregar os detalhes do usuário a partir do nome de usuário extraído do token JWT:

```java
@Autowired private AuthDetailsServiceImpl userService;

// ...

UserDetails user = userService.loadUserByUsername(username);
if (jwtService.isValid(token, user)) {
    // Configura a autenticação no contexto de segurança
}
```

O filtro extrai o nome de usuário do token, usa o `AuthDetailsServiceImpl` para carregar os detalhes completos do usuário, e então verifica se o token é válido para esse usuário.

## Fluxo de Autenticação

O fluxo de autenticação envolvendo o `AuthDetailsServiceImpl` funciona da seguinte forma:

1. Uma requisição chega ao servidor com um token JWT no cabeçalho de autorização
2. O `TokenFilter` intercepta a requisição e extrai o token
3. O `JwtService` extrai o nome de usuário do token
4. O `TokenFilter` chama `userService.loadUserByUsername(username)` para obter os detalhes completos do usuário
5. O `AuthDetailsServiceImpl` busca o usuário no banco de dados e o converte em um objeto `AuthDetailsImpl`
6. O `TokenFilter` verifica se o token é válido para esse usuário
7. Se for válido, o usuário é autenticado e a requisição continua

## Relação com Outras Classes

### UserRepository

```java
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
```

O `UserRepository` é uma interface Spring Data JPA que fornece acesso aos dados de usuário no banco de dados. O `AuthDetailsServiceImpl` utiliza o método `findByUsername` para buscar um usuário pelo nome de usuário.

### AuthDetailsImpl

A classe `AuthDetailsImpl` implementa a interface `UserDetails` do Spring Security e encapsula um objeto `User` do modelo de dados da aplicação. O `AuthDetailsServiceImpl` cria instâncias de `AuthDetailsImpl` a partir dos objetos `User` recuperados do banco de dados.

### User

A classe `User` é o modelo de dados que representa um usuário no banco de dados. Ela contém propriedades como `id`, `username`, `password` e `role`. O `AuthDetailsServiceImpl` busca objetos `User` no banco de dados e os converte em objetos `AuthDetailsImpl`.

## Considerações de Segurança

- O `AuthDetailsServiceImpl` é uma parte crítica do sistema de segurança, pois é responsável por carregar os detalhes do usuário que serão usados para autenticação e autorização.
- A implementação atual lança uma exceção quando um usuário não é encontrado, o que é uma prática recomendada de segurança, pois não revela se um nome de usuário existe ou não.
- A classe depende do `UserRepository` para acessar os dados de usuário, o que permite uma separação clara de responsabilidades entre o acesso aos dados e a lógica de autenticação.

## Extensibilidade

A implementação atual é simples e direta, mas poderia ser estendida para incluir funcionalidades adicionais, como:

- Verificação de status da conta (ativa, bloqueada, etc.)
- Carregamento de permissões adicionais além do papel básico
- Integração com sistemas de autenticação externos
- Cache de usuários para melhorar o desempenho

## Conclusão

A classe `AuthDetailsServiceImpl` desempenha um papel crucial no sistema de segurança da aplicação, atuando como uma ponte entre o repositório de usuários e o framework de segurança do Spring. Ela permite que o Spring Security autentique usuários com base nos dados armazenados no banco de dados da aplicação, fornecendo uma implementação simples mas eficaz da interface `UserDetailsService`.