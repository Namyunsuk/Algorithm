import java.util.*

val networks = Array(201){mutableListOf<Int>()}
val vis = Array(200){false}
var cnt = 0

class Solution {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        var answer = 0
        
        for(i in computers.indices){
            for(j in computers[i].indices){
                if(i==j) continue
                if(computers[i][j]==1){
                    networks[i].add(j)
                }
            }
        }
        
        bfs(n)
        
        
        return cnt
    }
    
    fun bfs(n: Int){
        val q = LinkedList<Int>()
        for(i in 0..n-1){
            if(vis[i]) continue
            q.offer(i)
            vis[i] = true
            cnt++
            while(!q.isEmpty()){
                val cur = q.poll()
                networks[cur].forEach{
                    if(vis[it]) return@forEach
                    q.offer(it)
                    vis[it] = true
                }
            }
        }
    }
}