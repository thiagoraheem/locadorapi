# 🟣 Atualização da Sprint 5 – Inclusão da Entidade OrcamentoItem

## 🆕 Nova tarefa: Criar entidade `OrcamentoItem`

### 🎯 Objetivo
Permitir que um orçamento contenha múltiplos itens detalhados, cada um representando um equipamento com sua quantidade e valor estimado.

---

## ✅ Tarefas e Detalhamento

### 1. Criar entidade `OrcamentoItem`

**Descrição:**  
Modelar a entidade que representa os itens vinculados a um orçamento, separando os dados por produto.

**Classe:** `OrcamentoItem.java`

**Campos sugeridos:**
- `id`: Long
- `orcamento`: Orcamento (`@ManyToOne`)
- `equipamento`: ProdutoRealEstate (`@ManyToOne`)
- `quantidade`: Integer
- `valorUnitario`: BigDecimal
- `valorTotal`: BigDecimal (calculado = quantidade × valorUnitario)

---

### 2. Relacionar `OrcamentoItem` com `Orcamento`

**No lado do `Orcamento`:**
```java
@OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true)
private List<OrcamentoItem> itens;
```

**No lado do `OrcamentoItem`:**
```java
@ManyToOne
private Orcamento orcamento;
```

---

### 3. Criar DTOs de entrada e saída

**OrcamentoItemRequest:**
```java
private Long equipamentoId;
private Integer quantidade;
private BigDecimal valorUnitario;
```

**OrcamentoItemResponse:**
```java
private String nomeEquipamento;
private Integer quantidade;
private BigDecimal valorUnitario;
private BigDecimal valorTotal;
```

---

### 4. Validar consistência entre Orçamento e seus Itens

**Regras sugeridas:**
- O valor total do orçamento deve ser igual à **soma dos itens**
- Não permitir quantidade <= 0
- Verificar se o `ProdutoRealEstate` existe
- Validar se o produto não está repetido no mesmo orçamento

---

### 5. Criar service para manipulação dos Itens

**Funções esperadas:**
- Calcular automaticamente `valorTotal` do item
- Adicionar/remover item do orçamento
- Atualizar lista de itens no momento da edição do orçamento
- Recalcular `valorTotal` do orçamento

---

### 6. Criar controller (opcional)

Se desejar permitir manipulação dos itens separadamente, criar um `OrcamentoItemController`.  
Caso contrário, a manipulação pode ocorrer exclusivamente via `OrcamentoController`.

---

## 🧠 Observações finais

- A entidade `OrcamentoItem` é essencial para representar corretamente a composição de um orçamento com múltiplos produtos.
- A modelagem correta vai facilitar a futura geração de `LocacaoItem` no momento da aprovação.