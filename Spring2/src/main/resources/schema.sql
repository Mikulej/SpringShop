--CREATE TABLE IF NOT EXISTS product (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    name VARCHAR(255) NOT NULL,
--    price DOUBLE NOT NULL,
--    amount INT NOT NULL
--);
--
---- Dodajemy przykładowe dane
--INSERT INTO product (name, price,amount) VALUES ('Kawa', 10.0,1);
--INSERT INTO product (name, price,amount) VALUES ('Herbata', 8.0,1);
--INSERT INTO product (name, price,amount) VALUES ('Ciastka', 15.0,1);
-- Dodajemy przykładowe dane
--INSERT INTO product (name, price) VALUES
--('Schabowy', 25.00),
--('Bigos', 18.00),
--('Kaszanka', 15.00),
--('Pierogi', 20.00),
--('Gołąbki', 22.00),
--('Żurek', 12.00),
--('Placki ziemniaczane', 16.00),
--('Kopytka', 14.00),
--('Smalec', 10.00),
--('Barszcz czerwony', 10.00),
--('Chłodnik', 12.00),
--('Gulasz', 18.00),
--('Sernik', 15.00),
--('Makowiec', 14.00),
--('Pączki', 5.00),
--('Kiełbasa', 20.00),
--('Łazanki', 17.00),
--('Racuchy', 12.00),
--('Fasolka po bretońsku', 15.00),
--('Szarlotka', 10.00),
--('Knedle', 16.00);
--
--INSERT INTO product (name, price) VALUES
--('Kompot', 6.00),
--('Kwas chlebowy', 8.00),
--('Oranżada', 5.00),
--('Sok jabłkowy', 7.00),
--('Sok wiśniowy', 7.00);

-- Drop table if it exists
DROP TABLE IF EXISTS users;

-- Create the users table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Insert default admin user with role ADMIN
INSERT INTO users (username, password, role) VALUES
('admin', '$2a$10$Y2ieaxZRLMbxRkBwM8TU1ubCC3/gTF2aoFVoxWic08iMbHOXmChJW', 'ADMIN'); -- password is 'admin'