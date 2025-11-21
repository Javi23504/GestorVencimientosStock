CREATE DATABASE gvsdb;
USE gvsdb;
SHOW DATABASES;
CREATE TABLE categorias (
  idcategoria INT PRIMARY KEY AUTO_INCREMENT,
  nombrecategoria VARCHAR(50) NOT NULL
);

CREATE TABLE productos (
  idproducto INT PRIMARY KEY AUTO_INCREMENT,
  codigobarra VARCHAR(50) UNIQUE NOT NULL,
  nombreproducto VARCHAR(100) NOT NULL,
  marca VARCHAR(50),
  idcategoria INT,
  FOREIGN KEY (idcategoria) REFERENCES categorias(idcategoria)
);

CREATE TABLE lotes (
  idlote INT PRIMARY KEY AUTO_INCREMENT,
  idproducto INT NOT NULL,
  fechavencimiento DATE NOT NULL,
  fechaproduccion DATE,
  stockinicial INT NOT NULL,
  stockactual INT NOT NULL,
  fechaingreso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (idproducto) REFERENCES productos(idproducto)
);


INSERT INTO categorias (nombrecategoria) VALUES 
('Lácteos'),
('Abarrotes'),
('Congelados'),
('Bebidas');

INSERT INTO productos (codigobarra, nombreproducto, marca, idcategoria) VALUES 
('7804510000011', 'Leche Entera 1L', 'Colun', 1),
('7804510000028', 'Yogurt Natural 120g', 'Colun', 1),
('7804510000035', 'Jugo Naranja 1.5L', 'Néctar', 4),
('7804510000042', 'Arroz Grado 1 1kg', 'Miraflores', 2),
('7804510000059', 'Papas Fritas 500g', 'McCain', 3);

INSERT INTO lotes (idproducto, fechavencimiento, fechaproduccion, stockinicial, stockactual) VALUES 
(1, '2025-12-15', '2025-11-01', 100, 85),
(2, '2025-11-25', '2025-11-10', 50, 30),
(3, '2026-01-20', '2025-10-15', 200, 150),
(4, '2026-06-30', '2025-09-01', 80, 80),
(5, '2025-12-31', '2025-10-20', 120, 95);

SELECT 
    l.idlote,
    p.nombreproducto,
    p.marca,
    c.nombrecategoria,
    l.fechavencimiento,
    l.stockactual
FROM lotes l
INNER JOIN productos p ON l.idproducto = p.idproducto
INNER JOIN categorias c ON p.idcategoria = c.idcategoria
WHERE l.stockactual > 0
ORDER BY l.fechavencimiento ASC;
