val graph = Array(1003){Array(1003){0}}

class Solution {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        var answer: Int = 0
        
        val n = board.size
        val m = board[0].size
        
        for(s in skill){
            val type = s[0]
            val r1 = s[1]
            val c1 = s[2]
            val r2 = s[3]
            val c2 = s[4]
            var degree = s[5]
            
            if(type==1) degree*=-1
            
            graph[r1][c1] += degree
            graph[r2+1][c1] -= degree
            
            graph[r1][c2+1] -=degree
            graph[r2+1][c2+1] +=degree
        }
        
        // 상 -> 하
        for(j in 0 until m){
            for(i in 1 until n){
                graph[i][j]+= graph[i-1][j]
            }
        }
        
        
        // 좌 -> 우
        for(i in 0 until n){
            for(j in 1 until m){
                graph[i][j]+= graph[i][j-1]
            }
        }
        
        for(i in 0 until n){
            for(j in 0 until m){
                if(graph[i][j] + board[i][j] > 0) answer++
            }
        }
        
        return answer
    }
}
