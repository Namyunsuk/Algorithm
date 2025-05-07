select i.id, n.FISH_NAME, i.LENGTH
from FISH_NAME_INFO as n, FISH_INFO as i
where n.FISH_TYPE = i.FISH_TYPE and
    i.LENGTH = (
        select max(LENGTH)
        from FISH_INFO
        where FISH_TYPE = n.FISH_TYPE 
    )


