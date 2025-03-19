import java.util.*

const val INF = 1e9.toInt()
class Edge(val node:Int, val weight:Int)
val graph = Array(50002){mutableListOf<Edge>()}
val d = Array(50002){INF}
val pq = PriorityQueue<Edge>{e1, e2 -> (e1.weight - e2.weight).toInt()}

class Solution {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        
        paths.forEach{ path->
            val from = path[0]
            val to = path[1]
            val weight = path[2]
            
            if(from in gates || to in summits){
                graph[from].add(Edge(to, weight))    
            }else if(from in summits || to in gates){
                graph[to].add(Edge(from, weight))
            }else{
                graph[from].add(Edge(to, weight))
                graph[to].add(Edge(from, weight))   
            }
        }
        
        gates.forEach{
            d[it] = 0
            pq.offer(Edge(it, 0))
        }
        
        dijkstra()
        
        var summitNum = 0
        var intensity = Int.MAX_VALUE
        
        summits.sort()
        summits.forEach{
            if(d[it]<intensity){
                summitNum = it
                intensity  = d[it]
            }
        }
        
        answer.add(summitNum)
        answer.add(intensity)
        
        return answer.toIntArray()
    }
}

fun dijkstra(){
    while(!pq.isEmpty()){
        val cur = pq.poll()
        if(d[cur.node] < cur.weight) continue
        graph[cur.node].forEach{
            val weight = maxOf(it.weight, d[cur.node])
            if(weight < d[it.node]){
                d[it.node] = weight
                pq.offer(Edge(it.node, weight))
            }
        }
    }
}











