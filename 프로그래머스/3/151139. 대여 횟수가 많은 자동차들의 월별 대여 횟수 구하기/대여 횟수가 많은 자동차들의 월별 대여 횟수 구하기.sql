select month(START_DATE) as MONTH, CAR_ID, count(CAR_ID) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where  DATE_FORMAT(START_DATE,"%Y-%m") between "2022-08" and "2022-10"
    and CAR_ID in (
        select CAR_ID
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where DATE_FORMAT(START_DATE,"%Y-%m") between "2022-08" and "2022-10"
        group by CAR_ID
        having count(CAR_ID) >= 5
    )
group by  MONTH, CAR_ID
having RECORDS>=1
order by MONTH ASC, CAR_ID DESC