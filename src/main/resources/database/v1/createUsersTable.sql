CREATE sequence users_table_sequence start with 1;

CREATE TABLE users
(
    id         BIGINT PRIMARY KEY NOT NULL default nextval('users_table_sequence'),
    email      VARCHAR(255)       NOT NULL,
    first_name VARCHAR(50)        NOT NULL,
    last_name  VARCHAR(100)       NOT NULL,
    password   VARCHAR(255)       NOT NULL,
    role       VARCHAR(20)        NOT NULL default 'USER',
    status     VARCHAR(20)        NOT NULL default 'ACTIVE'
);

INSERT INTO users (email, first_name, last_name, password, role, status)
VALUES
       ('admin@admin.a', 'Admin', 'Min', '$2a$12$Zs8vf2Omw3PWQF6cKpgj1.F.k0V41uk.LywSNv/MpF7aFoNi6zkzi', 'ADMIN', 'ACTIVE'),
       ('user@user.u', 'User', 'Ser', '$2a$12$DDaLv3ZkK9YFenlsarmRYuC5x7XvFaMFPhMKEUAQlEPsSVM3ViGlK', 'USER', 'BANNED');