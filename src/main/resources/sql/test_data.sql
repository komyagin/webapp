SET SCHEMA 'lab';

INSERT INTO lab.person (first_name, last_name, email, category)
VALUES ('Semyon', 'Komyagin', 'komyagin.s@gmail.com', 'INDIVIDUAL'),
       ('Maxim', 'Shubin', 'shubin.m@gmail.com', 'COMPANY'),
       ('Max', 'Pain', 'pain.m@gmail.com', 'COMPANY'),
       ('Vitaliy', 'Voloshin', 'voloshin.v@gmail.com', 'company');

INSERT INTO lab.notice (person_id, header, body, tel_number, created_date, updated_date, category)
VALUES (1, 'New computer for sale', 'New computer for sale', '+79111720124', current_timestamp, current_timestamp, 'INDIVIDUAL'),
       (2, 'New smartphone for sale', 'New smartphone for sale', '+79111720124', current_timestamp, current_timestamp, 'INDIVIDUAL'),
       (3, 'New house for sale', 'New house for sale', '+79111720124', current_timestamp, current_timestamp, 'COMPANY');
