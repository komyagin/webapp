set schema

insert into lab.person (first_name, last_name, email, category)
VALUES ('Semyon', 'Komyagin', 'komyagin.s@gmail.com', 'computers'),
       ('Maxim', 'Shubin', 'shubin.m@gmail.com', 'mobiles'),
       ('Vitaliy', 'Voloshin', 'voloshin.v@gmail.com', 'housing');

insert into lab.notice (person_id, header, body, tel_number, created_date, updated_date, category)
VALUES (0, 'New computer for sale', 'New computer for sale', '+79111720124', current_date, current_date, 'computers'),
       (1, 'New smartphone for sale', 'New smartphone for sale', '+79111720124', current_date, current_date, 'mobiles'),
       (2, 'New house for sale', 'New house for sale', '+79111720124', current_date, current_date, 'housing');