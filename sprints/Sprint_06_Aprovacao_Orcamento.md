# 🟣 Sprint 6 – Aprovação de Orçamento e Geração de Locação

## 🎯 Objetivo
Implementar o fluxo de aprovação de orçamentos, com geração automática da locação, movimentações de estoque e contas a receber.

---

## ✅ Tarefas e Detalhamento

### 1. Criar endpoint para aprovação de orçamento

**Descrição:**
Permitir que o orçamento seja aprovado, desde que esteja no status `PENDENTE`.

**Rota sugerida:**
```http
POST /orcamentos/{id}/aprovar
```

**Validações:**
- O orçamento deve existir
- O status deve ser `PENDENTE`
- Os equipamentos devem estar disponíveis no período informado

---

### 2. Validar disponibilidade dos itens no momento da aprovação

**Descrição:**
Mesmo que a disponibilidade já tenha sido verificada na criação do orçamento, ela deve ser **confirmada novamente**.

**Como fazer:**
- Verificar se os equipamentos ainda estão livres no período do orçamento
- Retornar erro caso haja conflito

---

### 3. Gerar entidade `Locacao` a partir do orçamento

**Descrição:**
Criar a locação com base nas informações do orçamento aprovado.

**Campos básicos:**
- cliente
- dataInicio, dataFim
- status: `EM_ANDAMENTO`
- itens locados (com quantidade e valores)
- vínculo com o orçamento aprovado

**Relação:**  
`Orcamento` → `Locacao`

---

### 4. Gerar registros de `MovimentacaoEstoque`

**Descrição:**
Apropriar ou reservar os equipamentos para o cliente.

**Detalhes:**
- Criar um registro de saída por equipamento
- Indicar que o item está alugado
- Relacionar com `Locacao`

**Possível estrutura:**
```java
@Entity
public class MovimentacaoEstoque {
    @ManyToOne
    private ProdutoRealEstate produto;
    private LocalDate data;
    private TipoMovimentacao tipo; // SAIDA, DEVOLUCAO
    @ManyToOne
    private Locacao locacao;
}
```

---

### 5. Gerar entidade `ContaReceber`

**Descrição:**
Automatizar a geração da cobrança financeira da locação.

**Campos:**
- cliente
- locacao
- data de vencimento (igual à data inicial ou negociada)
- valor total da locação
- status: `PENDENTE`

---

### 6. Atualizar status do orçamento para `APROVADO`

**Descrição:**
Após a criação da locação e das entidades associadas, o status do orçamento deve ser atualizado para `APROVADO`.

**Importante:**
- Esse status impede novas edições no orçamento.
- Pode ser interessante registrar a data de aprovação.

---

## 🧠 Observações finais

- Este processo marca a **transição oficial** de um orçamento para uma locação ativa.
- Todas as ações devem ocorrer de forma **transacional** — se alguma falhar, nada deve ser persistido.
- A lógica deve ser centralizada em um `OrcamentoService` ou `AprovacaoOrcamentoService`, se desejar separar responsabilidades.

---