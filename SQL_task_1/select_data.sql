-- 1. Count number of houses for each settlement. Result Set: town, count
select settlement.name as settlement_name, count(*) as number_of_houses
from settlement join public.street s on settlement.id = s.settlement_id join public.house h on s.id = h.street_id
group by settlement.name;

-- 2. count number of resident for each settlement. Result Set: town, count
select settlement.name as settlement_name, count(distinct house_tenant.tenant_id) as number_of_tenants
from settlement join public.street s on settlement.id = s.settlement_id
    join public.house h on s.id = h.street_id
    join house_tenant on h.id = house_id
group by settlement.name;



