SET SCHEMA 'lab';

INSERT INTO lab.person (id, first_name, last_name, email, category)
VALUES (1, 'Semyon', 'Komyagin', 'komyagin.s@gmail.com', 'computers'),
       (2, 'Maxim', 'Shubin', 'shubin.m@gmail.com', 'mobiles'),
       (3, 'Vitaliy', 'Voloshin', 'voloshin.v@gmail.com', 'housing');

INSERT INTO lab.notice (person_id, header, body, tel_number, created_date, updated_date, category)
VALUES (1, 'New computer for sale', 'New computer for sale', '+79111720124', current_timestamp, current_timestamp, 'computers'),
       (2, 'New smartphone for sale', 'New smartphone for sale', '+79111720124', current_timestamp, current_timestamp, 'mobiles'),
       (3, 'New house for sale', 'New house for sale', '+79111720124', current_timestamp, current_timestamp, 'housing');
