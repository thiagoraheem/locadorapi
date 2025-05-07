-- =========================
-- TABELAS AUXILIARES
-- =========================

CREATE TABLE person_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL -- 'INDIVIDUAL', 'COMPANY'
);

CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    street VARCHAR(100),
    number VARCHAR(10),
    complement VARCHAR(50),
    neighborhood VARCHAR(50),
    city VARCHAR(50),
    state CHAR(2),
    zip_code VARCHAR(10)
);

CREATE TABLE contact (
    id SERIAL PRIMARY KEY,
    contact_type VARCHAR(20), -- 'PHONE', 'EMAIL', etc.
    value VARCHAR(100),
    person_id INTEGER,
    person_type VARCHAR(20), -- 'CUSTOMER', 'SUPPLIER', 'EMPLOYEE'
    note TEXT
);

-- =========================
-- CLIENTES
-- =========================

CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    document VARCHAR(20),
    person_type_id INTEGER REFERENCES person_type(id),
    status_id INTEGER,
    address_id INTEGER REFERENCES address(id),
    registration_date DATE DEFAULT CURRENT_DATE
);

-- =========================
-- FORNECEDORES
-- =========================

CREATE TABLE supplier (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    document VARCHAR(20),
    person_type_id INTEGER REFERENCES person_type(id),
    address_id INTEGER REFERENCES address(id),
    registration_date DATE DEFAULT CURRENT_DATE
);

-- =========================
-- FUNCIONÁRIOS
-- =========================

CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cpf VARCHAR(14),
    email VARCHAR(100),
    phone VARCHAR(20),
    role_id INTEGER,
    username VARCHAR(30),
    password VARCHAR(100),
    is_active BOOLEAN DEFAULT TRUE
);

-- =========================
-- CATEGORIAS DE PRODUTO
-- =========================

CREATE TABLE product_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    status BOOLEAN DEFAULT TRUE
);

-- =========================
-- PRODUTOS
-- =========================

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    category_id INTEGER REFERENCES product_category(id),
    description TEXT NOT NULL,
    brand VARCHAR(50),
    model VARCHAR(50),
    cost_price NUMERIC(14,2),
    rental_price NUMERIC(14,2),
    status VARCHAR(20)
);

-- =========================
-- ESTOQUE
-- =========================

CREATE TABLE stock (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL REFERENCES product(id),
    quantity INTEGER NOT NULL,
    minimum_quantity INTEGER DEFAULT 0
);

-- =========================
-- ESTOQUE SERIALIZADO
-- =========================

CREATE TABLE product_serial (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL REFERENCES product(id),
    serial_number VARCHAR(100) UNIQUE NOT NULL,
    status VARCHAR(20),
    location VARCHAR(100)
);

-- =========================
-- OBRAS
-- =========================

CREATE TABLE construction_site (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES customer(id),
    name VARCHAR(100) NOT NULL,
    address_id INTEGER REFERENCES address(id),
    start_date DATE,
    end_date DATE,
    contact_name VARCHAR(100),
    contact_phone VARCHAR(20)
);

CREATE TABLE product_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL -- 'EQUIPMENT', 'VEHICLE', 'REAL_ESTATE', etc.
);

ALTER TABLE product
ADD COLUMN type_id INTEGER NOT NULL REFERENCES product_type(id);

CREATE TABLE product_real_estate (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL UNIQUE REFERENCES product(id),
    registration_number VARCHAR(50), -- matrícula do imóvel
    state_registration VARCHAR(50), -- inscrição estadual
    property_type VARCHAR(50), -- casa, galpão, terreno...
    useful_area NUMERIC(10,2),
    total_area NUMERIC(10,2),
    has_registry BOOLEAN DEFAULT FALSE
);

CREATE TABLE product_vehicle (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL UNIQUE REFERENCES product(id),
    plate VARCHAR(10),
    renavam VARCHAR(20),
    chassis VARCHAR(30),
    manufacturing_year INTEGER,
    model_year INTEGER,
    fuel_type VARCHAR(20),
    color VARCHAR(30),
    license_state CHAR(2),
    license_city VARCHAR(50)
);
