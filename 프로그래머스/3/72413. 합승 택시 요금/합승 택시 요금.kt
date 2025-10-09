const val INF = 1e9.toInt()
val graph = Array(203){Array(203){INF}}

class Solution {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        var answer: Int = 0
        
        fares.forEach{ fare->
            val st = fare[0]
            val e = fare[1]
            val w = fare[2]
            
            graph[st][e] = w
            graph[e][st] = w
        }
        
        for(i in 1 .. n){
            graph[i][i] = 0
        }
        
        for(v in 1 .. n){
            for(s in 1 .. n){
                for(e in 1 .. n){
                    if(graph[s][v] == INF || graph[v][e] == INF) continue
                    val totalWeight = graph[s][v] + graph[v][e]
                    if(totalWeight < graph[s][e]) graph[s][e] = totalWeight
                }
            }
        }
        
        // 각자 갈 경우
        answer = graph[s][a] + graph[s][b]
        
        for(v in 1 .. n){
            if(v == s) continue
            if(graph[s][v]==INF || graph[v][a]==INF ||graph[v][b] == INF) continue
            val totalWeight = graph[s][v] + graph[v][a] + graph[v][b]
            if(totalWeight < answer) answer = totalWeight
        }
        
        return answer
    }
}



