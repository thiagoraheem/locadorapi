-- =========================
-- LOCAÇÕES ATIVAS
-- =========================

CREATE OR REPLACE VIEW vw_active_rentals AS
SELECT 
    r.id AS rental_id,
    c.name AS customer_name,
    cs.name AS construction_site,
    r.rental_date,
    r.expected_return_date,
    rs.name AS status,
    r.total_value
FROM rental r
JOIN customer c ON c.id = r.customer_id
LEFT JOIN construction_site cs ON cs.id = r.construction_site_id
JOIN rental_status rs ON rs.id = r.status_id
WHERE rs.name = 'OPEN';

-- =========================
-- PRODUTOS DISPONÍVEIS PARA LOCAÇÃO
-- =========================

CREATE OR REPLACE VIEW vw_available_products AS
SELECT 
    p.id AS product_id,
    p.description,
    p.brand,
    p.model,
    s.quantity - COALESCE(ri.total_reserved, 0) AS available_quantity
FROM product p
JOIN stock s ON s.product_id = p.id
LEFT JOIN (
    SELECT 
        product_id, 
        SUM(quantity) AS total_reserved
    FROM rental_item
    WHERE status = 'ACTIVE'
    GROUP BY product_id
) ri ON ri.product_id = p.id;

-- =========================
-- CLIENTES INADIMPLENTES
-- =========================

CREATE OR REPLACE VIEW vw_overdue_accounts_receivable AS
SELECT 
    ar.id,
    c.name AS customer_name,
    ar.due_date,
    ar.amount,
    ar.amount_paid,
    ar.amount - ar.amount_paid AS open_amount
FROM accounts_receivable ar
JOIN customer c ON c.id = ar.customer_id
JOIN payment_status ps ON ps.id = ar.status_id
WHERE ps.name = 'OVERDUE';

-- =========================
-- RESUMO DE FATURAS POR MÊS
-- =========================

CREATE OR REPLACE VIEW vw_invoice_summary_by_month AS
SELECT 
    TO_CHAR(i.issue_date, 'YYYY-MM') AS period,
    it.name AS invoice_type,
    COUNT(*) AS invoice_count,
    SUM(i.total_amount) AS total_invoiced
FROM invoice i
JOIN invoice_type it ON it.id = i.type_id
GROUP BY period, it.name
ORDER BY period DESC;

-- =========================
-- MANUTENÇÕES EM ABERTO
-- =========================

CREATE OR REPLACE VIEW vw_open_maintenances AS
SELECT 
    m.id AS maintenance_id,
    p.description AS product_description,
    m.opening_date,
    m.expected_completion_date,
    m.status,
    m.notes
FROM maintenance m
JOIN product p ON p.id = m.product_id
WHERE m.status = 'OPEN';
