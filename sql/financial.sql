-- =========================
-- DOMÍNIOS E TABELAS AUXILIARES
-- =========================

CREATE TABLE invoice_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL -- 'ITEM', 'MEASUREMENT'
);

CREATE TABLE payment_status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL -- 'OPEN', 'PAID', 'OVERDUE', etc.
);

CREATE TABLE document_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- =========================
-- SEQUÊNCIA COMPARTILHADA ENTRE FATURAS E MEDIÇÕES
-- =========================

CREATE SEQUENCE invoice_sequence START 1000;

-- =========================
-- FATURAS (UNIFICANDO ITEM E MEDIÇÃO)
-- =========================

CREATE TABLE invoice (
    id INTEGER PRIMARY KEY DEFAULT nextval('invoice_sequence'),
    customer_id INTEGER NOT NULL,
    rental_id INTEGER,
    type_id INTEGER NOT NULL REFERENCES invoice_type(id),
    issue_date DATE NOT NULL,
    due_date DATE,
    total_amount NUMERIC(14,2) NOT NULL,
    status_id INTEGER NOT NULL REFERENCES payment_status(id),
    document_type_id INTEGER REFERENCES document_type(id),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

-- =========================
-- ITENS DA FATURA (produtos ou outras faturas agrupadas)
-- =========================

CREATE TABLE invoice_item (
    id SERIAL PRIMARY KEY,
    invoice_id INTEGER NOT NULL REFERENCES invoice(id),
    product_id INTEGER, -- NULL se for uma fatura de medição
    related_invoice_id INTEGER, -- Usado quando fatura de medição
    description TEXT,
    quantity INTEGER,
    unit_price NUMERIC(14,2),
    total_price NUMERIC(14,2)
);

-- =========================
-- CONTAS A RECEBER
-- =========================

CREATE TABLE accounts_receivable (
    id SERIAL PRIMARY KEY,
    invoice_id INTEGER NOT NULL REFERENCES invoice(id),
    customer_id INTEGER NOT NULL,
    issue_date DATE NOT NULL,
    due_date DATE,
    amount NUMERIC(14,2) NOT NULL,
    amount_paid NUMERIC(14,2) DEFAULT 0,
    status_id INTEGER REFERENCES payment_status(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

-- =========================
-- RATEIO DE CONTAS A RECEBER
-- =========================

CREATE TABLE cost_center (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE chart_of_accounts (
    id SERIAL PRIMARY KEY,
    code VARCHAR(20),
    description VARCHAR(100)
);

CREATE TABLE accounts_receivable_allocation (
    id SERIAL PRIMARY KEY,
    receivable_id INTEGER NOT NULL REFERENCES accounts_receivable(id),
    cost_center_id INTEGER REFERENCES cost_center(id),
    account_id INTEGER REFERENCES chart_of_accounts(id),
    amount NUMERIC(14,2) NOT NULL
);

-- =========================
-- CONTAS A PAGAR
-- =========================

CREATE TABLE accounts_payable (
    id SERIAL PRIMARY KEY,
    supplier_id INTEGER NOT NULL,
    issue_date DATE NOT NULL,
    due_date DATE,
    amount NUMERIC(14,2) NOT NULL,
    amount_paid NUMERIC(14,2) DEFAULT 0,
    document_number VARCHAR(50),
    status_id INTEGER REFERENCES payment_status(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

-- =========================
-- RATEIO DE CONTAS A PAGAR
-- =========================

CREATE TABLE accounts_payable_allocation (
    id SERIAL PRIMARY KEY,
    payable_id INTEGER NOT NULL REFERENCES accounts_payable(id),
    cost_center_id INTEGER REFERENCES cost_center(id),
    account_id INTEGER REFERENCES chart_of_accounts(id),
    amount NUMERIC(14,2) NOT NULL
);
