select o.ANIMAL_ID, o.NAME
from ANIMAL_OUTS as o
left join ANIMAL_INS as i on o.ANIMAL_ID = i.ANIMAL_ID
where i.ANIMAL_TYPE is null
order by o.ANIMAL_ID