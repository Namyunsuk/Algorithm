import java.util.*

val graph = Array(1_000_001){mutableListOf<Int>()}
val vis = Array(1_000_001){false}
val outCount = Array(1_000_001){0}
val inCount = Array(1_000_001){0}

var edgeNums = mutableSetOf<Int>()
var createdEdge = 0
var doughnut = 0
var stick = 0
var eight = 0

class Solution {
    fun solution(edges: Array<IntArray>): IntArray {
        var answer = mutableListOf<Int>()
        
        edges.forEach{ edge->
            val s = edge[0]
            val e = edge[1]
            
            edgeNums.add(s)
            edgeNums.add(e)
            
            graph[s].add(e)
            outCount[s]++
            inCount[e]++
        }
        
        // 생성한 정점 찾기
        for(e in edgeNums){
            if(outCount[e]>=2 && inCount[e]==0){
                createdEdge = e
                break
            }
        }
        
        vis[createdEdge] = true
        
        graph[createdEdge].forEach{e->
            checkGraph(e)
        }
        
        answer.add(createdEdge)
        answer.add(doughnut)
        answer.add(stick)
        answer.add(eight)
        
        
        
        return answer.toIntArray()
    }
}

fun checkGraph(s:Int){
    val q = LinkedList<Int>()
    q.offer(s)
    vis[s] = true
    
    // 막대인 경우
    if(outCount[s] == 0){
        stick++
        return
    }

    if(inCount[s]==3 && outCount[s]==2){
        eight++
        return
    }
    
    while(!q.isEmpty()){
        val cur = q.poll()
        
        graph[cur].forEach{ e->
            // 8자
            if(inCount[e]==2 && outCount[e]==2){
                eight++
                return
            }
            // 도넛
            if(vis[e]){
                doughnut++
                return
            }
            
            // 막대인 경우
            if(outCount[e] == 0){
                stick++
                return
            }
            q.offer(e)
            vis[e] = true
        }
    }
    
}