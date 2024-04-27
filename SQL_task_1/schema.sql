-- create database settlements;
drop table if exists house_tenant;
drop table if exists tenant;
drop view if exists houses;
drop table if exists house;
drop table if exists street;
drop table if exists settlement;

create table if not exists settlement (
    id bigserial primary key,
    name text not null,
    type text check ( type in ('город', 'деревня', 'поселок', 'село', 'станица', 'населенный пункт')) not null,
    former_postal_code char(3) check (former_postal_code between '101' and '800')
);

create table if not exists street (
    id bigserial primary key,
    name text not null,
    type text check ( type in ('улица', 'аллея', 'бульвар', 'проспект', 'тракт', 'переулок') ) not null,
    latter_postal_code char(3) check (latter_postal_code between '000' and '999'),
    settlement_id bigserial not null,
    foreign key (settlement_id) references settlement(id)
);

create table if not exists house (
    id bigserial primary key,
    number text check ( number between '000' and '999') not null, -- только цифры
    street_id bigserial not null,
    foreign key (street_id) references street(id)
);

create table if not exists tenant (
    id bigserial primary key,
    full_name text not null
);

create table if not exists house_tenant (
    house_id bigserial not null,
    tenant_id bigserial not null,
    foreign key (house_id) references house(id),
    foreign key (tenant_id) references tenant(id)
);
