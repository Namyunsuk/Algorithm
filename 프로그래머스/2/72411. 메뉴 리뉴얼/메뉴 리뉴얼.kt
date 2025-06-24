var maxCnt = 0
var maxSelectedMenu = mutableListOf<String>()

class Solution {
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val answer = mutableListOf<String>()
        
//         for(order in orders){
//             val splittedOrder = order.split("").filter{it.isNotBlank()}
//             menus.addAll(splittedOrder)
//         }
//         menus.sort()
//         menus = menus.distinct().toMutableList()
        
        
        for(c in course){
            maxCnt = 0
            maxSelectedMenu = mutableListOf<String>()
            
            for(order in orders){
                val menus = order.split("").filter{it.isNotBlank()}
                dfs(0,0,c,mutableListOf(), orders, menus.sorted())    
            }
            
            
            answer.addAll(maxSelectedMenu.toList())
        }
        
        answer.sort()
        
        return answer.distinct().toTypedArray()
    }
    
    fun dfs(index:Int, cnt:Int, target:Int,selected:MutableList<String>, orders: Array<String>, menus:List<String>){
        if(cnt == target){
            val orderCnt = calCnt(selected, orders)
            if(orderCnt<2) return
            if(orderCnt > maxCnt){
                maxCnt = orderCnt
                maxSelectedMenu.clear()
                maxSelectedMenu.add(selected.joinToString(""))
            }else if(orderCnt == maxCnt){
                maxSelectedMenu.add(selected.joinToString(""))
            }
            
            return
        }
        
        for(i in index until menus.size){
            selected.add(menus[i])
            dfs(i+1, cnt+1,target, selected, orders, menus)
            selected.remove(menus[i])
        }
    }
    
    fun calCnt(selected:List<String>, orders: Array<String>):Int{
        var cnt = 0
        
        for(order in orders){
            val splittedOrder = order.split("").filter{it.isNotBlank()}
            if(splittedOrder.containsAll(selected)) cnt++
        }
        
        return cnt
    }
}