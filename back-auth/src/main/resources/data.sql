INSERT INTO authorities (username, authority)
VALUES ('john', 'ROLE_MANAGER');
INSERT INTO authorities (username, authority)
VALUES ('jane', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority)
VALUES ('jane', 'write');
INSERT INTO authorities (username, authority)
VALUES ('jane', 'delete');
INSERT INTO authorities (username, authority)
VALUES ('mary', 'read');

INSERT INTO users (username, password, enabled)
VALUES ('john', '12345', '1');
INSERT INTO users (username, password, enabled)
VALUES ('mary', '12345', '1');
INSERT INTO users (username, password, enabled)
VALUES ('jane', '1234', '1');

