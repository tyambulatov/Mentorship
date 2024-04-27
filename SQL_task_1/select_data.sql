-- 1. count number of houses for each settlement. Result Set: town, count
select settlement.name as settlement_name, count(*) as number_of_houses
from settlement
         join public.street s on settlement.id = s.settlement_id
         join public.house h on s.id = h.street_id
group by settlement.name;

-- 2. count number of resident for each settlement. Result Set: town, count
select settlement.name as settlement_name, count(distinct house_tenant.tenant_id) as number_of_tenants
from settlement
         join public.street s on settlement.id = s.settlement_id
         join public.house h on s.id = h.street_id
         join house_tenant on h.id = house_id
group by settlement.name;

-- 3. show number of tenants for given city. Result set: full_name, number

drop function if exists show_number_of_namesakes(given_settlement_id bigint);

CREATE FUNCTION show_number_of_namesakes(given_settlement_id bigint)
    RETURNS table
            (
                full_name text,
                count     integer
            )
AS
$$
select t.full_name, count(distinct t.id) as qty_tenant
from public.street as s
         join public.house h on s.id = h.street_id
         join public.house_tenant ht on h.id = ht.house_id
         join public.tenant t on ht.tenant_id = t.id
where s.settlement_id = given_settlement_id
group by t.full_name
$$ LANGUAGE SQL;

SELECT full_name as namesakes, count
from show_number_of_namesakes(3);


-- 4. show top 3 streets by number of tenants
drop function if exists show_top3_streets(given_settlement_id bigint);

create function show_top3_streets(given_settlement_id bigint)
    returns table
            (
                street text,
                count  integer
            )
as
$$
select s.name, count(distinct t.id) as qty_tenant
from public.street as s
         left join public.house h on s.id = h.street_id
         left join public.house_tenant ht on h.id = ht.house_id
         left join public.tenant t on ht.tenant_id = t.id
where s.settlement_id = given_settlement_id
group by s.id
order by count(distinct t.id) desc
limit 3
$$
    language sql;

select street, count
from show_top3_streets(3);

-- 5. create view of house. Result set: house_id, address, postal_code
drop view if exists houses;

create view houses as
    select h.id as house_id,
           concat(stl.type, ' ', stl.name, ', ', s.type, ' ', s.name, ', д.', h.number) as adress,
           concat(lpad(stl.former_postal_code, 3, '0'), lpad(s.latter_postal_code, 3, '0')) as postal_code
    from settlement as stl join public.street s on stl.id = s.settlement_id
        join public.house h on s.id = h.street_id;

select * from houses;