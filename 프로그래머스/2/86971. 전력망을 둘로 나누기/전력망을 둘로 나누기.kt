import java.util.*
import kotlin.math.*

val q = LinkedList<Int>()

val removed = Array(101){Array(101){false}}

val graph = Array(101){HashSet<Int>()}

class Solution {
    fun solution(n: Int, wires: Array<IntArray>): Int {
        var answer: Int = Int.MAX_VALUE
        
        wires.forEach{
            val v1 = it[0]
            val v2 = it[1]
            
            graph[v1].add(v2)
            graph[v2].add(v1)
        }
        
        for(i in 1..n){
            graph[i].forEach{
                removed[i][it] = true
                removed[it][i] = true
                val cnt1 = bfs(i)
                val cnt2 = bfs(it)
                answer = minOf(answer, abs(cnt1-cnt2))
                removed[i][it] = false
                removed[it][i] = false
            }
        }
        
        return answer
    }
    
    fun bfs(start:Int):Int{
        val vis = Array(101){false}
        q.offer(start)
        vis[start] = true
        var cnt = 1
        
        while(!q.isEmpty()){
            val cur = q.poll()
            graph[cur].forEach{
                if(removed[cur][it]) return@forEach
                if(vis[it]) return@forEach
                cnt++
                q.offer(it)
                vis[it] = true
            }
        }
        return cnt
    }
    
    
    
    
}