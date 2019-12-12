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

INSERT INTO lab.person (id, first_name, last_name, email, category)
VALUES (1, 'Semyon', 'Komyagin', 'komyagin.s@gmail.com', 'computers'),
       (2, 'Maxim', 'Shubin', 'shubin.m@gmail.com', 'mobiles'),
       (3, 'Vitaliy', 'Voloshin', 'voloshin.v@gmail.com', 'housing');

INSERT INTO lab.notice (person_id, header, body, tel_number, created_date, updated_date, category)
VALUES (1, 'New computer for sale', 'New computer for sale', '+79111720124', current_timestamp, current_timestamp,
        'computers'),
       (2, 'New smartphone for sale', 'New smartphone for sale', '+79111720124', current_timestamp, current_timestamp,
        'mobiles'),
       (3, 'New house for sale', 'New house for sale', '+79111720124', current_timestamp, current_timestamp, 'housing');
