select distinct e1.ID,  e1.GENOTYPE, p.GENOTYPE as PARENT_GENOTYPE
from ECOLI_DATA as e1, ECOLI_DATA as p
where e1.GENOTYPE & (
    select e2.GENOTYPE
    from ECOLI_DATA as e2
    where e1.PARENT_ID = e2.ID
) = (
    select e2.GENOTYPE
    from ECOLI_DATA as e2
    where e1.PARENT_ID = e2.ID
) and p.GENOTYPE = (
    select e2.GENOTYPE
    from ECOLI_DATA as e2
    where e1.PARENT_ID = e2.ID
)
order by e1.ID ASC