insert into settlement (name, type, former_postal_code)
values ('Казань', 'город', 420),
       ('Москва', 'город', 101),
       ('Караганда', 'деревня', null),
       ('Кизнер', 'поселок', 427);

insert into street (name, type, latter_postal_code, settlement_id)
values ('Каспийская', 'улица', 088, 1),
       ('Губкина', 'улица', 088, 2);

insert into house (number, street_id)
values (91, 3),
       (11, 3),
       (51, 3),
       (24, 3),
       (31, 4),
       (1, 4),
       (21, 4);

insert into tenant (full_name)
values ('Иванов Иван Иванович'),
       ('Сидоров Сидор Сидорович'),
       ('Александров Александр Александрович'),
       ('Никитин Никита Никитович'),
       ('Краснов Красен Краснович');

insert into house_tenant (house_id, tenant_id)
values (8, 1),
       (8, 2),
       (8, 3),
       (9, 4),
       (9, 5),
       (9, 5);