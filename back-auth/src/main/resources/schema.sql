CREATE TABLE IF NOT EXISTS users (
    id INT,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    enabled INT NOT NULL,
    PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS authorities (
  id INT,
  username VARCHAR(45) NOT NULL,
  authority VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
);