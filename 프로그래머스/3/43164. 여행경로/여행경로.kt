val ans = mutableListOf<String>()
val tmp = mutableListOf<String>()

lateinit var vis:Array<Boolean>
var travelCnt: Int = 0

class Solution {
    fun solution(tickets: Array<Array<String>>): Array<String> {
        var answer = arrayOf<String>()
        vis = Array(tickets.size){false}
        travelCnt = tickets.size
        
        tickets.sortBy{it[1]}
        
        dfs(tickets, "ICN", 0)
        
        return ans.toTypedArray()
    }
    
    fun dfs(tickets: Array<Array<String>>, start:String, cnt: Int){
        if(cnt == travelCnt){
            if(ans.isEmpty()){
                ans.addAll(tmp)
                ans.add(start)
            }
            return
        }
        
        for(i in tickets.indices){
            if(vis[i]) continue
            if(tickets[i][0]!=start) continue
            tmp.add(start)
            vis[i] = true
            dfs(tickets, tickets[i][1], cnt+1)
            vis[i] = false
            tmp.removeLast()
        }
    }
}