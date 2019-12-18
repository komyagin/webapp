CREATE SCHEMA IF NOT EXISTS lab;

CREATE TABLE IF NOT EXISTS lab.person
(
    id         SERIAL NOT NULL PRIMARY KEY,
    first_name TEXT   NOT NULL,
    last_name  TEXT   NOT NULL,
    email      TEXT   NOT NULL UNIQUE,
    category   TEXT
);

CREATE TABLE IF NOT EXISTS lab.notice
(
    id           SERIAL    NOT NULL PRIMARY KEY,
    person_id    INT       NOT NULL,
    header       TEXT      NOT NULL,
    body         TEXT      NOT NULL,
    tel_number   TEXT      NOT NULL,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NOT NULL,
    category     TEXT,
    FOREIGN KEY (person_id) REFERENCES lab.person (id)
);
