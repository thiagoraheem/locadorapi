# Documentação da Classe AuthDetailsImpl

## Visão Geral

A classe `AuthDetailsImpl` é uma implementação da interface `UserDetails` do Spring Security, que serve como um adaptador entre o modelo de usuário da aplicação (`User`) e o modelo de usuário esperado pelo framework de segurança do Spring.

## Localização no Projeto

```
com.locador.api.security.impl.UserDetailsImpl
```

## Propósito

O principal propósito da classe `AuthDetailsImpl` é encapsular um objeto `User` do modelo de dados da aplicação e expor suas propriedades de uma maneira que o Spring Security possa entender e utilizar para realizar autenticação e autorização.

## Implementação

```java
public class AuthDetailsImpl implements UserDetails {
    private final User user;

    public AuthDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override public String getPassword() { return user.getPassword(); }
    @Override public String getUsername() { return user.getUsername(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
```

## Métodos Implementados

### Constructor

```java
public AuthDetailsImpl(User user) {
    this.user = user;
}
```

O construtor recebe um objeto `User` e o armazena para uso pelos métodos da interface `UserDetails`.

### getAuthorities()

```java
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
}
```

Este método retorna uma coleção de autoridades (permissões) que o usuário possui. Na implementação atual:

1. Obtém o papel (role) do usuário através de `user.getRole().name()`
2. Adiciona o prefixo "ROLE_" ao nome do papel (uma convenção do Spring Security)
3. Cria um objeto `SimpleGrantedAuthority` com esse valor
4. Retorna uma lista contendo essa autoridade

O Spring Security usa essas autoridades para verificar se o usuário tem permissão para acessar recursos protegidos por anotações como `@PreAuthorize("hasRole('ADMIN')")` ou configurações de segurança baseadas em roles.

### getPassword()

```java
@Override public String getPassword() { return user.getPassword(); }
```

Retorna a senha do usuário, que é usada pelo Spring Security durante o processo de autenticação para verificar as credenciais fornecidas.

### getUsername()

```java
@Override public String getUsername() { return user.getUsername(); }
```

Retorna o nome de usuário, que é usado como identificador principal durante a autenticação.

### Métodos de Estado da Conta

```java
@Override public boolean isAccountNonExpired() { return true; }
@Override public boolean isAccountNonLocked() { return true; }
@Override public boolean isCredentialsNonExpired() { return true; }
@Override public boolean isEnabled() { return true; }
```

Estes quatro métodos retornam informações sobre o estado da conta do usuário:

- `isAccountNonExpired()`: Indica se a conta do usuário está expirada (neste caso, sempre retorna `true`, indicando que a conta nunca expira)
- `isAccountNonLocked()`: Indica se a conta está bloqueada (neste caso, sempre retorna `true`, indicando que a conta nunca está bloqueada)
- `isCredentialsNonExpired()`: Indica se as credenciais (senha) estão expiradas (neste caso, sempre retorna `true`, indicando que as credenciais nunca expiram)
- `isEnabled()`: Indica se a conta está ativa (neste caso, sempre retorna `true`, indicando que a conta está sempre ativa)

Na implementação atual, todos esses métodos retornam `true`, o que significa que não há verificações adicionais de estado da conta. Em uma aplicação mais complexa, esses métodos poderiam verificar propriedades adicionais do objeto `User` para determinar o estado da conta.

## Onde é Utilizada

A classe `AuthDetailsImpl` é principalmente utilizada em dois contextos:

### 1. No AuthDetailsServiceImpl

```java
@Service
public class AuthDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new AuthDetailsImpl(user);
    }
}
```

O `AuthDetailsServiceImpl` é responsável por carregar os detalhes do usuário a partir do banco de dados quando o Spring Security precisa autenticar um usuário. Ele cria uma instância de `AuthDetailsImpl` a partir do objeto `User` recuperado do banco de dados.

### 2. No Processo de Autenticação por Token

No `TokenFilter`, o `AuthDetailsServiceImpl` é usado para carregar os detalhes do usuário a partir do nome de usuário extraído do token JWT. O objeto `UserDetails` retornado (que é uma instância de `AuthDetailsImpl`) é então usado para validar o token e configurar a autenticação no contexto de segurança do Spring.

```java
UserDetails user = userService.loadUserByUsername(username);
if (jwtService.isValid(token, user)) {
    UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(auth);
}
```

## Relação com o Modelo User

A classe `AuthDetailsImpl` depende do modelo `User`, que é definido como:

```java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Getters e Setters
}
```

O modelo `User` contém:
- Um identificador único (`id`)
- Um nome de usuário (`username`)
- Uma senha (`password`)
- Um papel (`role`) que é um valor da enumeração `Role` (ADMIN ou USER)

A classe `AuthDetailsImpl` acessa essas propriedades através dos métodos getters do objeto `User`.

## Papéis de Usuário (Roles)

Os papéis de usuário são definidos na enumeração `Role`:

```java
public enum Role {
    ADMIN, USER
}
```

Na aplicação atual, existem dois papéis possíveis:
- `ADMIN`: Usuários com privilégios administrativos
- `USER`: Usuários regulares com acesso limitado

A classe `AuthDetailsImpl` converte esses papéis em autoridades do Spring Security adicionando o prefixo "ROLE_" ao nome do papel, resultando em "ROLE_ADMIN" ou "ROLE_USER".

## Conclusão

A classe `AuthDetailsImpl` desempenha um papel crucial no sistema de segurança da aplicação, atuando como uma ponte entre o modelo de dados da aplicação e o framework de segurança do Spring. Ela permite que o Spring Security utilize as informações de usuário armazenadas no banco de dados para realizar autenticação e autorização de forma eficaz.