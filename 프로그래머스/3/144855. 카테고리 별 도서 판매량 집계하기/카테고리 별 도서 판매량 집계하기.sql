select b.CATEGORY, sum(s.SALES) as TOTAL_SALES
from BOOK as b left join BOOK_SALES as s on b.BOOK_ID = s.BOOK_ID
where s.SALES_DATE like "2022-01%"
group by b.CATEGORY
order by b.CATEGORY ASC