select i2.ITEM_ID, i2.ITEM_NAME, i2.RARITY
from ITEM_INFO as i, ITEM_TREE as t, ITEM_INFO as i2
where i.RARITY = "RARE" and (i.ITEM_ID = t.PARENT_ITEM_ID ) and i2.ITEM_ID = t.ITEM_ID
order by i2.ITEM_ID desc
