-- DDL

CREATE TABLE IF NOT EXISTS subject (
  id INT PRIMARY KEY,
  name VARCHAR(250) UNIQUE NOT NULL,
  create_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS associate (
  cpf VARCHAR(11) PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS session (
  id INT PRIMARY KEY,
  subject_id INT NOT NULL,
  start DATE NOT NULL,
  finish DATE NOT NULL,
  foreign key (subject_id) references subject(id)
);

-- DML
-- Creating some associates, NERGE is being used instead of INSERT cause they can't be created everytime app restarts
MERGE INTO associate
  KEY(cpf)
VALUES ('19839091069', 'Associate A - able to Vote'),
  ('62289608068', 'Associate B - NOT able to Vote'),
  ('12345678910', 'Associate C - Test');
