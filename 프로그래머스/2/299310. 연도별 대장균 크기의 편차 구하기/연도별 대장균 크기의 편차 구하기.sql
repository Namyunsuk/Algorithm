select year(e2.DIFFERENTIATION_DATE) as YEAR,
   (
       select max(e.SIZE_OF_COLONY)
       from ECOLI_DATA as e
       where year(e2.DIFFERENTIATION_DATE) =  year(e.DIFFERENTIATION_DATE) 
   ) - SIZE_OF_COLONY as YEAR_DEV, e2.ID
from ECOLI_DATA as e2
order by YEAR, YEAR_DEV