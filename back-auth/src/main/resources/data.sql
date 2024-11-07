INSERT INTO authorities (username, authority)
VALUES ('bill', 'ROLE_USER');
INSERT INTO authorities (username, authority)
VALUES ('emma', 'ROLE_MANAGER');

INSERT INTO users (username, password, enabled)
VALUES ('bill', 'password', '1');
INSERT INTO users (username, password, enabled)
VALUES ('emma', '1234', '1');

