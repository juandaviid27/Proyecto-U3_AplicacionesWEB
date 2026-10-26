IF DB_ID('TiendaJerseys') IS NULL
    CREATE DATABASE TiendaJerseys;
GO

USE TiendaJerseys;
GO

CREATE TABLE Jersey (
    id INT IDENTITY(1,1) PRIMARY KEY,
    equipo VARCHAR(100) NOT NULL,
    temporada VARCHAR(20) NOT NULL,
    talla VARCHAR(5) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    foto VARCHAR(300) NOT NULL
);
GO

INSERT INTO Jersey(equipo, temporada, talla, precio, foto) VALUES
('Club América', '2025-26', 'M', 1499.00, 'https://tursissoccer.com/cdn/shop/files/Club_America_25-26_Home_Jersey_Yellow_JN8612_01_laydown.jpg?v=1752339472'),
('Barcelona', '2025-26', 'L', 1799.00, 'https://www.voetbalshop.be/media/catalog/product/cache/d81c8dc66c69ceb69419c2e7e72e896d/3/2/322033_nike-fc-barcelona-thuisshirt-authentic-2025-2026_1.jpg'),
('Rayados', '2025-26', 'L', 1699.00, 'https://s.ecrater.com/stores/582586/68895e8fa2412_582586b.jpg');
GO

USE master;
GO
IF NOT EXISTS (SELECT 1 FROM sys.server_principals WHERE name = 'JavaUser')
    CREATE LOGIN JavaUser WITH PASSWORD = 'password123', CHECK_POLICY = OFF;
GO
USE TiendaJerseys;
GO
IF NOT EXISTS (SELECT 1 FROM sys.database_principals WHERE name = 'JavaUser')
    CREATE USER JavaUser FOR LOGIN JavaUser;
ALTER ROLE db_owner ADD MEMBER JavaUser;
GO

