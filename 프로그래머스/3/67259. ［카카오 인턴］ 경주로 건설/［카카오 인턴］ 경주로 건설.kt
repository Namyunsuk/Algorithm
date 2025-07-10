import java.util.*

data class Pos(val x:Int, val y:Int, val cost:Int=0, val dx:Int=-1, val dy:Int=-1)

val graph = Array(27){Array(27){Array(4){Int.MAX_VALUE}}}
val q = LinkedList<Pos>()

val dx = listOf(0,0,-1,1)
val dy = listOf(-1,1,0,0)

class Solution {
    fun solution(board: Array<IntArray>): Int {
        var answer = Int.MAX_VALUE
        
        val n = board.size
        
        for(i in 0 until 4){
            graph[0][0][i] = 0
        }
        
        q.offer(Pos(0,0))
        
        while(!q.isEmpty()){
            val cur = q.poll()
            
            for(i in 0 until 4){
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]
                val isVertical = isVertical(cur.dx, cur.dy,dx[i], dy[i])
                
                val cost = cur.cost + if(isVertical) 600 else 100
                
                
                if(nx<0 || nx>=n || ny<0 || ny>=n) continue
                if(board[nx][ny]==1) continue
                if(graph[nx][ny][i] <= cost) continue
                
                q.offer(Pos(nx, ny, cost, dx[i], dy[i]))
                graph[nx][ny][i]= cost
            }
        }
        
        for(i in 0 until 4){
            answer = minOf(answer, graph[n-1][n-1][i])
        }
        
        
        return answer
    }
    
    fun isVertical(dx1:Int, dy1:Int, dx2:Int, dy2:Int):Boolean{
        return (dx1*dx2 + dy1*dy2)== 0
    }
}

