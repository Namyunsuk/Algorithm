const val INF = 1e9.toInt()

val graph = Array(202){Array(202){INF}}

class Solution {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        var answer: Int = Int.MAX_VALUE
        
        fares.forEach{
            val c = it[0]
            val d = it[1]
            val f = it[2]
            
            graph[c][d] = f
            graph[d][c] = f
        }
        
        for(i in 1..n){
            graph[i][i] = 0
        }
        
        for(v in 1 .. n){
            for(s in 1..n){
                for(e in 1..n){
                    if(graph[s][e] > graph[s][v] + graph[v][e])
                    graph[s][e] = graph[s][v] + graph[v][e]
                }
            }
        }
        


        
        for(i in 1..n){
            var sum = 0
            if(graph[s][i]==INF) continue
            sum+=graph[s][i]
            sum+=graph[i][a]
            sum+=graph[i][b]
            
            if(answer > sum) answer = sum
        }
        
        
        
        return answer
    }
}








