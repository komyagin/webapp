create database notices;

create schema if not exists lab;

create table if not exists lab.person
(
    id         serial       not null primary key,
    first_name VARCHAR(100) not null,
    last_name  VARCHAR(100) not null,
    email      VARCHAR(100) not null unique,
    category   text
);

create table if not exists lab.notice
(
    id           serial        not null primary key,
    person_id    int           not null,
    header       VARCHAR(100)  not null,
    body         VARCHAR(1000) not null,
    tel_number   VARCHAR(100)  not null,
    created_date timestamp     not null,
    updated_date timestamp     not null,
    category     text,
    foreign key (person_id) references lab.person (id)
);
