select ITEM_ID, ITEM_NAME, RARITY
from ITEM_INFO
where ITEM_ID in (
    select t.ITEM_ID
    from ITEM_INFO as i, ITEM_TREE as t
    where i.RARITY = "RARE" and i.ITEM_ID = t.PARENT_ITEM_ID
)
order by ITEM_ID desc