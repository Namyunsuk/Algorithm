select ANIMAL_TYPE, count(*) as "count"
from ANIMAL_INS
where ANIMAL_TYPE in ("Dog", "Cat")
group by ANIMAL_TYPE
order by ANIMAL_TYPE