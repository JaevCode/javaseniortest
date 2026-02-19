CREATE TABLE IF NOT EXISTS interviewtest.employees (
                                                       id BIGINT NOT NULL AUTO_INCREMENT,
                                                       fist_name VARCHAR (100) NULL,
    middle_name VARCHAR (100) NULL,
    paternal_surname VARCHAR (100) NULL,
    maternal_surname VARCHAR (100) NULL,
    age INT NULL,
    genre VARCHAR (30) NULL,
    birth_date DATETIME NULL,
    position VARCHAR (100) NULL,
    registration_date DATETIME NULL,
    active TINYINT (1) NULL,
    PRIMARY KEY (id)
    ) ENGINE = InnoDB;

INSERT INTO interviewtest.employees
(fist_name, middle_name, paternal_surname, maternal_surname, age, genre, birth_date, position, registration_date, active)
VALUES
    ('Juan', 'Carlos', 'Perez', 'Lopez', 29, 'M', '1996-04-12 00:00:00', 'Backend Developer', NOW(), 1),
    ('Maria', 'Fernanda', 'Gonzalez', 'Ramirez', 34, 'F', '1991-09-03 00:00:00', 'QA Engineer', NOW(), 1),
    ('Luis', NULL, 'Hernandez', 'Torres', 41, 'M', '1984-01-25 00:00:00', 'Tech Lead', NOW(), 1);