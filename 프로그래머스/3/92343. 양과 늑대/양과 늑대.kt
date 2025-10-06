/**
참고 링크

https://velog.io/@thguss/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-L3-%EC%96%91%EA%B3%BC-%EB%8A%91%EB%8C%80-python

**/ 
val vis = Array(19){false}
var answer: Int = 0

class Solution {
    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        
        
        vis[0] = true
        dfs(1,0,info,edges)
        
        return answer
    }
    
    fun dfs(sheepCnt:Int, wolfCnt:Int, info: IntArray, edges: Array<IntArray>){
        if(sheepCnt <= wolfCnt) return
        answer = maxOf(answer, sheepCnt)
        
        for(edge in edges){
            val p = edge[0]
            val c = edge[1]
            
            if(vis[p] && !vis[c]){
                vis[c] = true
                if(info[c] == 0) dfs(sheepCnt+1, wolfCnt, info, edges)
                else dfs(sheepCnt, wolfCnt+1, info, edges)
                vis[c] = false
            }
        }
    }
    
}