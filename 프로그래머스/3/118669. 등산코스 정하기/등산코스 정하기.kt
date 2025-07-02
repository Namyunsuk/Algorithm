import java.util.*

const val INF = 1e9.toInt()

data class Node(val num:Int, val dist:Int)

val graph = Array(50002){mutableListOf<Node>()}
val d = Array(500002){INF}

class Solution {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        
        paths.forEach{ path->
            val i = path[0]
            val j = path[1]
            val w = path[2]
            
            if(!graph[i].contains(Node(j,w))) graph[i].add(Node(j,w))
            if(!graph[j].contains(Node(i,w))) graph[j].add(Node(i,w))
        }
        
        dijkstra(gates, summits)
        
        var summit = 0
        var intensity = Int.MAX_VALUE
        
        val s = summits.sorted()
        
        s.forEach{
            if(d[it] < intensity){
                intensity = d[it]
                summit = it
            }
        }
        
        answer.add(summit)
        answer.add(intensity)
        
        return answer.toIntArray()
    }
    
    fun dijkstra(gates: IntArray, summits: IntArray){
        val pq = PriorityQueue<Node>{n1,n2->(n1.dist - n2.dist).toInt()}
        
        
        gates.forEach{
            d[it] = 0
            pq.offer(Node(it, 0))
        }
        
        
        while(!pq.isEmpty()){
            val cur = pq.poll()
            if(summits.contains(cur.num)) continue // 정상에서 더 이상 X
            if(cur.dist != d[cur.num]) continue
            
            graph[cur.num].forEach{ node ->
                val maxDist = maxOf(d[cur.num], node.dist)
                
                if(maxDist < d[node.num]){
                    d[node.num] = maxDist
                    pq.offer(Node(node.num, maxDist))
                }
            }
        }
    }
}