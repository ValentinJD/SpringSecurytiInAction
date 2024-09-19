INSERT INTO authorities (username, authority)
VALUES ('john', 'read');

INSERT INTO users (username, password, enabled)
VALUES ('john', '12345', '1');

INSERT INTO authorities (username, authority)
VALUES ('jane', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority)
VALUES ('jane', 'write');
INSERT INTO authorities (username, authority)
VALUES ('jane', 'delete');

INSERT INTO users (username, password, enabled)
VALUES ('jane', '1234', '1');