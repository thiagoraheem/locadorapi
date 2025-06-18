# 🟣 Sprint 5 – Módulo de Orçamento (Pré-locação)

## 🎯 Objetivo
Desenvolver o fluxo de criação e consulta de orçamentos de locação, garantindo a validação da disponibilidade dos equipamentos e preparando o processo para a futura aprovação.

---

## ✅ Tarefas e Detalhamento

### 1. Criar entidade Orcamento

**Descrição:**  
Modelar a entidade `Orcamento` representando a proposta de locação antes da aprovação.

**Campos sugeridos:**
- `id`: Long
- `cliente`: Cliente (relacionamento @ManyToOne)
- `itens`: List<OrcamentoItem>
- `prazoInicial`: LocalDate
- `prazoFinal`: LocalDate
- `valorPrevisto`: BigDecimal
- `status`: enum OrcamentoStatus (PENDENTE, APROVADO, REJEITADO)
- `observacoes`: String

---

### 2. Relacionar com Cliente, Equipamentos, PrazoInicial, PrazoFinal, ValorPrevisto

**Descrição:**  
Relacionar o orçamento com as entidades necessárias, utilizando `OrcamentoItem` como classe intermediária para os produtos.

**Detalhes técnicos:**
- Cliente: @ManyToOne
- ProdutoRealEstate: @ManyToOne dentro de OrcamentoItem
- Datas: LocalDate
- Valor: BigDecimal calculado

---

### 3. Criar DTOs para entrada e saída

**DTO de entrada (`OrcamentoRequest`):**
- clienteId
- prazoInicial
- prazoFinal
- List de itens `{ equipamentoId, quantidade }`

**DTO de saída (`OrcamentoResponse`):**
- id
- clienteNome
- periodo
- valorTotal
- status
- lista de equipamentos com nomes, quantidade, valor unitário

---

### 4. Implementar lógica de criação e edição do orçamento

**Fluxo:**
1. Validar cliente e equipamentos
2. Calcular valor total com base na quantidade e dias
3. Salvar `Orcamento` e `OrcamentoItem`
4. Status inicial: PENDENTE
5. Impedir edições após aprovação ou rejeição

---

### 5. Validar disponibilidade dos itens no momento do orçamento

**Como fazer:**
- Criar método para verificar conflitos de datas com locações existentes
- Retornar erro se houver conflito: `"Equipamento X indisponível entre DD/MM e DD/MM"`

---

### 6. Endpoint para consultar orçamentos por cliente/status/data

**Exemplo de uso:**
`GET /orcamentos?clienteId=1&status=PENDENTE&inicio=2025-07-01&fim=2025-07-31`

**Requisitos:**
- Suportar filtros e paginação
- Retornar DTO resumido
- Permitir ausência de filtros para trazer todos

---

### 7. Criar lógica para Aprovação de Orçamento

**Fluxo:**
1. Verificar status: PENDENTE
2. Validar disponibilidade novamente
3. Criar entidade `Locacao` vinculada ao orçamento
4. Gerar `MovimentacaoEstoque` e `ContaReceber`
5. Atualizar status do orçamento para APROVADO

**Sugestão de rota:**  
`POST /orcamentos/{id}/aprovar`

---

## 🧠 Observações finais

- Orçamentos rejeitados ou aprovados **não podem ser modificados**
- A aprovação do orçamento inicia o processo de locação
- Toda a lógica de negócios deve estar no `OrcamentoService`