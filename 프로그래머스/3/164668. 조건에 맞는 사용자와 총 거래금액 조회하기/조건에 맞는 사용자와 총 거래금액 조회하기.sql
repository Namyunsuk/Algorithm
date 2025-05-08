select u.USER_ID, u.NICKNAME, sum(b.PRICE) as TOTAL_SALES
from USED_GOODS_USER as u left join USED_GOODS_BOARD as b on b.WRITER_ID = u.USER_ID
where (
    select sum(PRICE)
    from USED_GOODS_BOARD
    where u.USER_ID = WRITER_ID and STATUS = "DONE"
) >=700000 and b.STATUS = "DONE"
group by u.USER_ID
order by TOTAL_SALES ASC