select i.NAME, i.DATETIME
from ANIMAL_INS as i
left join ANIMAL_OUTS as o on i.ANIMAL_ID = o.ANIMAL_ID
where o.ANIMAL_ID is null
order by i.DATETIME
limit 3