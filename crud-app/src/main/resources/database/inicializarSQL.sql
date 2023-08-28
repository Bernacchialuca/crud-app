create database spring_database;
use spring_database;

CREATE TABLE Ciudad (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        ciudad VARCHAR(255)
);

INSERT INTO Ciudad (ciudad) VALUES
                                ('Madrid'),
                                ('Los Angeles'),
                                ('Paris'),
                                ('India'),
                                ('Mexico'),
                                ('Buenos Aires'),
                                ('Tokio'),
                                ('Londres');

CREATE TABLE Empleado (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(255),
                          apellido VARCHAR(255),
                          dni BIGINT,
                          email VARCHAR(255),
                          telefono VARCHAR(20),
                          salario INT,
                          genero VARCHAR(255),
                          puesto VARCHAR(255),
                          id_ciudad BIGINT
);

INSERT INTO Empleado (nombre, apellido, dni, email, telefono, salario, genero, puesto, id_ciudad)
VALUES
    ('Ana', 'López', '12345678', 'ana.lopez@example.com', '555-12367', 2500, 'Femenino', 'Frontend Developer', 1),
    ('Juan', 'García', '23456789', 'juan.garcia@example.com', '555-23478', 3000, 'Otro', 'Backend Developer', 2),
    ('María', 'Martínez', '34567890', 'maria.martinez@example.com', '555-34589', 5200, 'Femenino', 'Full Stack Developer', 1),
    ('Carlos', 'Rodríguez', '45678901', 'carlos.rodriguez@example.com', '555-45690', 7000, 'Masculino', 'Team Leader', 3),
    ('Laura', 'Hernández', '56789012', 'laura.hernandez@example.com', '555-56901', 5700, 'Otro', 'Project Manager', 2),
    ('Luis', 'Pérez', '67890123', 'luis.perez@example.com', '555-67892', 3200, 'Masculino', 'Frontend Developer', 4),
    ('Elena', 'Gómez', '78901234', 'elena.gomez@example.com', '555-78123', 2800, 'Otro', 'Backend Developer', 3),
    ('Miguel', 'Díaz', '89012345', 'miguel.diaz@example.com', '555-89234', 2400, 'Masculino', 'Full Stack Developer', 5),
    ('Sofía', 'López', '90123456', 'sofia.lopez@example.com', '555-90345', 2600, 'Femenino', 'Team Leader', 6),
    ('Diego', 'Martínez', '01234567', 'diego.martinez@example.com', '555-01456', 10000, 'Otro', 'Project Manager', 4),
    ('Paula', 'García', '12345678', 'paula.garcia@example.com', '555-12567', 12800, 'Femenino', 'Frontend Developer', 7),
    ('Javier', 'Fernández', '23456789', 'javier.fernandez@example.com', '555-23678', 7000, 'Masculino', 'Backend Developer', 8),
    ('Valentina', 'Hernández', '34567890', 'valentina.hernandez@example.com', '555-34789', 8300, 'Femenino', 'Full Stack Developer', 1),
    ('Andrés', 'Vargas', '45678901', 'andres.vargas@example.com', '555-45890', 3000, 'Masculino', 'Team Leader', 2),
    ('Julia', 'Pérez', '56789012', 'julia.perez@example.com', '555-56701', 2600, 'Femenino', 'Project Manager', 3),
    ('Roberto', 'Suárez', '67890123', 'roberto.suarez@example.com', '555-67812', 5800, 'Masculino', 'Frontend Developer', 4),
    ('Camila', 'Gómez', '78901234', 'camila.gomez@example.com', '555-789-0123', 8400, 'Femenino', 'Backend Developer', 5),
    ('Mateo', 'López', '89012345', 'mateo.lopez@example.com', '555-89034', 8700, 'Masculino', 'Full Stack Developer', 6),
    ('Isabella', 'Fernández', '90123456', 'isabella.fernandez@example.com', '555-90345', 3200, 'Femenino', 'Team Leader', 7),
    ('Samuel', 'Martínez', '01234567', 'samuel.martinez@example.com', '555-013456', 5200, 'Masculino', 'Project Manager', 8),
    ('Lucía', 'Hernández', '12345678', 'lucia.hernandez@example.com', '555-12567', 5000, 'Femenino', 'Frontend Developer', 1),
    ('Daniel', 'García', '23456789', 'daniel.garcia@example.com', '555-23678', 4500, 'Masculino', 'Backend Developer', 2),
    ('Regina', 'Pérez', '34567890', 'regina.perez@example.com', '555-34789', 9000, 'Femenino', 'Full Stack Developer', 3),
    ('Sebastián', 'López', '45678901', 'sebastian.lopez@example.com', '555-45690', 12300, 'Masculino', 'Team Leader', 4),
    ('Victoria', 'Fernández', '56789012', 'victoria.fernandez@example.com', '555-56901', 5600, 'Femenino', 'Project Manager', 5),
    ('Matías', 'Gómez', '67890123', 'matias.gomez@example.com', '555-67012', 2900, 'Masculino', 'Frontend Developer', 6),
    ('Emma', 'Martínez', '78901234', 'emma.martinez@example.com', '555-78123', 3100, 'Femenino', 'Backend Developer', 7),
    ('Dylan', 'Rodríguez', '89012345', 'dylan.rodriguez@example.com', '555-89934', 2200, 'Masculino', 'Full Stack Developer', 8),
    ('Renata', 'López', '90123456', 'renata.lopez@example.com', '555-90345', 2400, 'Femenino', 'Team Leader', 1);
