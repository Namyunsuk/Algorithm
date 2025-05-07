select p.ID, count(c.ID) as CHILD_COUNT
from ECOLI_DATA as p
left join ECOLI_DATA as c on p.ID = c.PARENT_ID
group by p.ID
order by p.ID