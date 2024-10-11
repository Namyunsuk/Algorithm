import java.util.*
import kotlin.math.*

class Solution {
    fun solution(n: Int, wires: Array<IntArray>): Int {
        var answer: Int = 101
        val arr = Array<MutableList<Int>>(101){mutableListOf()}
        
        wires.forEach { wire ->
            arr[wire[0]].add(wire[1])
            arr[wire[1]].add(wire[0])
        }
        
        wires.forEach { wire ->
            val nodeSize1 = bfs(arr, wire[0], wire[1], Array(n + 1) { false })
            val nodeSize2 = bfs(arr, wire[1], wire[0], Array(n + 1) { false })
            answer = min(answer, abs(nodeSize1 - nodeSize2))
        }
        
        return answer
    }
    
    fun bfs(arr: Array<MutableList<Int>>, start: Int, except: Int, visited: Array<Boolean>): Int {
        var cnt = 0
        val q = LinkedList<Int>()
        q.offer(start)
        visited[start] = true
        cnt++
        while (!q.isEmpty()){
            val cur = q.poll()
            arr[cur].forEach{
                if (visited[it]) return@forEach
                if(start==cur &&it == except) return@forEach
                q.offer(it)
                visited[it] = true
                cnt++
            }
        }

        return cnt
    }
}

