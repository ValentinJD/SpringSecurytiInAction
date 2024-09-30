INSERT INTO authorities (username, authority)
VALUES ('jane', 'write');
INSERT INTO authorities (username, authority)
VALUES ('mary', 'read');

INSERT INTO users (username, password, enabled)
VALUES ('mary', '12345', '1');
INSERT INTO users (username, password, enabled)
VALUES ('jane', '1234', '1');

