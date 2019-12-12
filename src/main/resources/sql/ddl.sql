CREATE SCHEMA IF NOT EXISTS lab;

CREATE TABLE IF NOT EXISTS lab.person
(
    id         SERIAL       NOT NULL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL unique,
    category   TEXT
);

CREATE TABLE IF NOT EXISTS lab.notice
(
    id           SERIAL        NOT NULL PRIMARY KEY,
    person_id    INT           NOT NULL,
    header       VARCHAR(100)  NOT NULL,
    body         VARCHAR(1000) NOT NULL,
    tel_number   VARCHAR(100)  NOT NULL,
    created_date TIMESTAMP     NOT NULL,
    updated_date TIMESTAMP     NOT NULL,
    category     TEXT,
    FOREIGN KEY (person_id) REFERENCES lab.person (id)
);
