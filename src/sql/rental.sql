-- =========================
-- STATUS DE LOCAÇÃO, ITENS E CONTRATOS
-- =========================

CREATE TABLE rental_status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL -- 'OPEN', 'CLOSED', 'CANCELLED'
);

CREATE TABLE contract_status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL -- 'ACTIVE', 'CLOSED', 'CANCELLED'
);

-- =========================
-- LOCAÇÃO PRINCIPAL
-- =========================

CREATE TABLE rental (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL REFERENCES customer(id),
    construction_site_id INTEGER REFERENCES construction_site(id),
    rental_date DATE NOT NULL,
    expected_return_date DATE,
    payment_method_id INTEGER REFERENCES payment_method(id),
    company_id INTEGER,
    status_id INTEGER REFERENCES rental_status(id),
    total_value NUMERIC(14,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- ITENS DE LOCAÇÃO
-- =========================

CREATE TABLE rental_item (
    id SERIAL PRIMARY KEY,
    rental_id INTEGER NOT NULL REFERENCES rental(id),
    product_id INTEGER NOT NULL REFERENCES product(id),
    quantity INTEGER NOT NULL,
    unit_price NUMERIC(14,2),
    total_price NUMERIC(14,2),
    rental_start_date DATE,
    rental_end_date DATE,
    status VARCHAR(20),
    serial_number_id INTEGER REFERENCES product_serial(id)
);

-- =========================
-- CONTRATO DE LOCAÇÃO
-- =========================

CREATE TABLE rental_contract (
    id SERIAL PRIMARY KEY,
    rental_id INTEGER NOT NULL REFERENCES rental(id),
    contract_number VARCHAR(50),
    contract_date DATE,
    start_date DATE,
    end_date DATE,
    total_value NUMERIC(14,2),
    status_id INTEGER REFERENCES contract_status(id),
    document_url TEXT
);

-- =========================
-- TERMO DE ENTREGA
-- =========================

CREATE TABLE rental_delivery_term (
    id SERIAL PRIMARY KEY,
    rental_id INTEGER NOT NULL REFERENCES rental(id),
    delivery_date DATE NOT NULL,
    delivered_by VARCHAR(100),
    received_by VARCHAR(100),
    notes TEXT
);

CREATE TABLE rental_delivery_term_item (
    id SERIAL PRIMARY KEY,
    delivery_term_id INTEGER NOT NULL REFERENCES rental_delivery_term(id),
    product_id INTEGER NOT NULL REFERENCES product(id),
    quantity INTEGER NOT NULL,
    condition_notes TEXT
);

-- =========================
-- TERMO DE DEVOLUÇÃO
-- =========================

CREATE TABLE rental_return_term (
    id SERIAL PRIMARY KEY,
    rental_id INTEGER NOT NULL REFERENCES rental(id),
    return_date DATE NOT NULL,
    returned_by VARCHAR(100),
    received_by VARCHAR(100),
    notes TEXT
);

CREATE TABLE rental_return_term_item (
    id SERIAL PRIMARY KEY,
    return_term_id INTEGER NOT NULL REFERENCES rental_return_term(id),
    product_id INTEGER NOT NULL REFERENCES product(id),
    quantity INTEGER NOT NULL,
    condition_notes TEXT
);
