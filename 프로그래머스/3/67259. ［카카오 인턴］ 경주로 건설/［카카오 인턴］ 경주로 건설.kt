class Pos(val x:Int, val y:Int, val dir:Pair<Int,Int> = 0 to 0, val cost:Int=0)
val dx = listOf(-1,1,0,0)
val dy = listOf(0,0,-1,1)
val costs = Array(26){Array(26){Array(4){Int.MAX_VALUE}}}
var minV = Int.MAX_VALUE


class Solution {
    fun solution(board: Array<IntArray>): Int {
        val n = board.size
        
        dfs(n,Pos(0,0),board)
        
        return minV
    }
}

fun dfs(n:Int, cur:Pos, board: Array<IntArray>){
    if(cur.x==n-1&&cur.y==n-1){
        if(minV>cur.cost) {
            minV = cur.cost
        }
        return
    }
    
    if(cur.cost>=minV) return
    
    for(i in 0..3){
        val dir = dx[i] to dy[i]
        val nx = cur.x + dx[i]
        val ny = cur.y + dy[i]
        if(nx<0||nx>=n||ny<0||ny>=n) continue
        if(board[nx][ny]==1) continue
        
        var cost = cur.cost + 100
        if(cur.x!=0 || cur.y!=0){
            cost +=isVertical(cur.dir, dir)
        }
        val dirIdx = makeDir(dir)
        if(costs[nx][ny][dirIdx]<=cost) continue
        
        costs[nx][ny][dirIdx] = cost
        dfs(n,Pos(nx, ny, dir, cost),board)
    }
}

fun isVertical(dir1:Pair<Int, Int>, dir2:Pair<Int, Int>):Int{
    return if((dir1.first*dir2.first+dir1.second*dir2.second ) == 0) 500 else 0
}

fun makeDir(dir:Pair<Int, Int>):Int{
    return when(dir){
        0 to 1 -> 0
        1 to 0 -> 1
        0 to -1 -> 2
        else -> 3
    }
}









