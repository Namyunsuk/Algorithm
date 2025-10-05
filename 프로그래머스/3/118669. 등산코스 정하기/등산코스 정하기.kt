// 1.16
import java.util.*

const val INF = 1_000_000_000

class Node(val num:Int, val intensity:Int):Comparable<Node>{
    override fun compareTo(other:Node):Int{
        if(this.intensity < other.intensity) return -1
        else if(this.intensity > other.intensity) return 1
        return 0
    }
}

val graph = Array(50_001){mutableListOf<Node>()}
val d = Array(50_001){INF}
val pq = PriorityQueue<Node>()

class Solution {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        
        paths.forEach{ path->
            val i = path[0]
            val j = path[1]
            val w = path[2]
            
            graph[i].add(Node(j, w))
            graph[j].add(Node(i, w))
        }
        
        gates.forEach{ gate->
            d[gate] = 0
            pq.offer(Node(gate, 0))
        }
        
        dijkstra(summits)
        
        var minSummit = Int.MAX_VALUE
        var minIntensity = Int.MAX_VALUE
        for(summit in summits){
            if(d[summit] < minIntensity){
                minSummit = summit
                minIntensity = d[summit]
            }else if(d[summit] == minIntensity && summit < minSummit){
                minSummit = summit
            }
        }
        
        answer.add(minSummit)
        answer.add(minIntensity)
        
        return answer.toIntArray()
    }
}

fun dijkstra(summits: IntArray){
    while(!pq.isEmpty()){
        val cur = pq.poll()
        if(summits.contains(cur.num)) continue
        if(cur.intensity == d[cur.num]){
            graph[cur.num].forEach{ node->
                val intensity = maxOf(node.intensity, cur.intensity)
                
                if(intensity < d[node.num]){
                    d[node.num] = intensity
                    pq.offer(Node(node.num, intensity))   
                }
            }
        }
    }
}






