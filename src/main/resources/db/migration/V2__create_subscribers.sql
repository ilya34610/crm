CREATE TABLE subscribers
(
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    birth_date DATE NOT NULL,
    balance NUMERIC(8,2) NOT NULL,
    id_tariff_plan INT NOT NULL,
    FOREIGN KEY (id_tariff_plan) REFERENCES tariff_plans(id)
);