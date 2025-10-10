val cases = Array(15){mutableSetOf<String>()}
val orderMap = HashMap<String, Int>()

class Solution {
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        var answer = mutableListOf<String>()
        
        orders.forEach{
            val split = it.split("").filter{it.isNotBlank()}.toMutableList()
            val order = split.sorted().joinToString("")
            dfs(0, "", order)
        }
        
        course.forEach{ cnt->
            var order = mutableListOf<String>()
            var count = 0
            cases[cnt].forEach{
                if(orderMap[it]!! >=2){
                    if(orderMap[it]!! > count){
                        order.clear()
                        order.add(it)
                        count = orderMap[it]!!
                    }else if(orderMap[it]!! == count){
                        order.add(it)
                    }
                }
            }
            answer.addAll(order.toList())
        }
        
        answer.sort()
        
        return answer.toTypedArray()
    }
    
    fun dfs(idx:Int, selected:String, order:String){
        if(selected.length >= 2){
            cases[selected.length].add(selected)
            if(orderMap[selected] == null) orderMap[selected] = 1
            else orderMap[selected] = orderMap[selected]!! + 1
        }
        
        if(selected.length == order.length) return
        
        for(i in idx until order.length){
            dfs(i+1, selected + order[i].toString(), order)
        }
    }
}





