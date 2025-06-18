# 🟣 Sprint 7 – Módulo de Locação (Pós-aprovação)

## 🎯 Objetivo
Implementar o acompanhamento das locações ativas, permitindo ações como devolução, renovação de prazo e cancelamento, com controle de estoque e prazos.

---

## ✅ Tarefas e Detalhamento

### 1. Criar endpoints para consulta de locações

**Descrição:**
Permitir a listagem e filtragem das locações registradas.

**Filtros possíveis:**
- Cliente
- Status (EM_ANDAMENTO, DEVOLVIDA, CANCELADA)
- Período (data inicial e final)

**Requisitos:**
- Paginação
- Ordenação por data ou status
- Retorno em DTO

**Rota sugerida:**
```http
GET /locacoes?clienteId=1&status=EM_ANDAMENTO&inicio=2025-07-01&fim=2025-07-31
```

---

### 2. Criar endpoint para marcar locação como devolvida

**Descrição:**
Registrar a devolução dos itens locados e atualizar o estoque.

**Requisitos:**
- Atualizar status da locação para `DEVOLVIDA`
- Registrar data de devolução real
- Criar movimentações de `DEVOLUCAO` no estoque

**Rota sugerida:**
```http
POST /locacoes/{id}/devolver
```

---

### 3. Criar endpoint para renovação de prazo

**Descrição:**
Permitir que o prazo final de uma locação ativa seja estendido.

**Requisitos:**
- Validar se a nova data não gera conflito de disponibilidade
- Atualizar data final no registro da locação
- Recalcular valor (se aplicável)
- Registrar histórico (opcional)

**Rota sugerida:**
```http
PATCH /locacoes/{id}/renovar
```

**Corpo esperado:**
```json
{ "novaDataFinal": "2025-08-15" }
```

---

### 4. Criar endpoint para cancelamento de locação

**Descrição:**
Permitir o cancelamento antes do início efetivo (regra de negócio definida pelo sistema).

**Validações:**
- Apenas locações com data futura e status `EM_ANDAMENTO` podem ser canceladas
- Estornar movimentações de estoque (se houver)
- Atualizar status para `CANCELADA`

**Rota sugerida:**
```http
POST /locacoes/{id}/cancelar
```

---

### 5. Atualizar movimentação de estoque na devolução

**Descrição:**
Ao devolver itens, criar registros de movimentação com tipo `DEVOLUCAO`.

**Exemplo de estrutura:**
```java
new MovimentacaoEstoque(produto, LocalDate.now(), TipoMovimentacao.DEVOLUCAO, locacao)
```

---

### 6. Marcar data real de devolução

**Descrição:**
Registrar a data em que os itens foram efetivamente devolvidos.

**Campos sugeridos:**
- `dataDevolucaoPrevista`
- `dataDevolucaoReal`

**Dica:**
Essas datas podem ser usadas para cálculos de multa ou relatório de atraso.

---

### 7. Calcular ajustes financeiros por devolução ou prorrogação (opcional)

**Descrição:**
Se o sistema permitir, gerar cobrança adicional por prorrogação ou multa por atraso na devolução.

**Exemplo:**
- Acrescentar juros simples ao `ContaReceber`
- Gerar segunda parcela (nova conta) se necessário

---

## 🧠 Observações finais

- Essa sprint foca na gestão de locações que já estão ativas
- Todos os status devem ser tratados com cuidado para evitar inconsistências
- O controle de movimentações de estoque precisa refletir fielmente o estado físico dos equipamentos