import java.util.*

class Pos(val x:Int, val y:Int)

class Robot(val pos1:Pos, val pos2:Pos, val time:Int = 0)


var n = 0

val q = LinkedList<Robot>()

val dx = listOf(0, 0, -1, 1)
val dy = listOf(-1, 1, 0, 0)

val vis = Array(101){Array(101){Array(101){Array(101){false}}}}

class Solution {
    fun solution(board: Array<IntArray>): Int {
        var answer = 0
        
        n = board.size
        
        q.offer(Robot(Pos(0,0), Pos(0,1)))
        vis[0][0][0][1] = true
        vis[0][1][0][0] = true
        
        
        while(!q.isEmpty()){
            val cur = q.poll()
            val pos1 = cur.pos1
            val pos2 = cur.pos2
            
            if((pos1.x==n-1&&pos1.y==n-1) || (pos2.x==n-1&&pos2.y==n-1)){
                return cur.time
            }
            
            turnCounterClockWise(pos1, pos2, cur.time, board)
            turnCounterClockWise(pos2, pos1, cur.time, board)
            
            turnClockWise(pos1, pos2, cur.time, board)
            turnClockWise(pos2, pos1, cur.time, board)
            
            for(i in 0 until 4){
                val nx1 = pos1.x + dx[i]
                val ny1 = pos1.y + dy[i]
                
                val nx2 = pos2.x + dx[i]
                val ny2 = pos2.y + dy[i]
                
                if(nx1<0||nx1>=n||ny1<0||ny1>=n) continue
                if(nx2<0||nx2>=n||ny2<0||ny2>=n) continue
                if(vis[nx1][ny1][nx2][ny2]) continue
                
                if(board[nx1][ny1] == 1 || board[nx2][ny2] == 1) continue
                
                q.offer(Robot(Pos(nx1, ny1), Pos(nx2, ny2), cur.time + 1))
                vis[nx1][ny1][nx2][ny2] = true
                vis[nx2][ny2][nx1][ny1] = true
            }
            
        }
        
        return answer
    }
}

fun turnCounterClockWise(axis:Pos,move:Pos, time:Int, board: Array<IntArray>) {
    // 로봇 세로 방향
    if(axis.y == move.y){
        // 축이 위
        if(axis.x < move.x){
            val nx1 = axis.x
            val ny1 = axis.y + 1
            
            val nx2 = move.x
            val ny2 = move.y + 1
            
            if(ny1 >= n || ny2 >= n) return
            if(board[nx1][ny1] == 1 || board[nx2][ny2] == 1) return
            
            if(vis[axis.x][axis.y][axis.x][axis.y+1]) return
            q.offer(Robot(Pos(axis.x, axis.y), Pos(axis.x, axis.y+1), time+1))
            vis[axis.x][axis.y][axis.x][axis.y+1] = true
            vis[axis.x][axis.y+1][axis.x][axis.y] = true
        } else{ // 축이 아래
            val nx1 = axis.x
            val ny1 = axis.y - 1
            
            val nx2 = move.x
            val ny2 = move.y - 1
            
            if(ny1 < 0 || ny2 < 0) return
            if(board[nx1][ny1] == 1 || board[nx2][ny2] == 1) return
            
            if(vis[axis.x][axis.y][axis.x][axis.y-1]) return
            q.offer(Robot(Pos(axis.x, axis.y), Pos(axis.x, axis.y-1), time+1))
            vis[axis.x][axis.y][axis.x][axis.y-1] = true
            vis[axis.x][axis.y-1][axis.x][axis.y] = true
        }
        
    } else{ // 로봇 가로 방향
        // 축이 왼쪽
        if(axis.y < move.y){
            val nx1 = axis.x - 1
            val ny1 = axis.y
            
            val nx2 = move.x - 1
            val ny2 = move.y
            
            if(nx1 < 0 || nx2 < 0) return
            if(board[nx1][ny1] == 1 || board[nx2][ny2] == 1) return
            
            if(vis[axis.x][axis.y][axis.x-1][axis.y]) return
            q.offer(Robot(Pos(axis.x, axis.y), Pos(axis.x-1, axis.y), time+1))
            vis[axis.x][axis.y][axis.x-1][axis.y] = true
            vis[axis.x-1][axis.y][axis.x][axis.y] = true
        } else{ // 축이 오른쪽
            val nx1 = axis.x + 1
            val ny1 = axis.y
            
            val nx2 = move.x + 1
            val ny2 = move.y
            
            if(nx1 >= n || nx2 >= n) return
            if(board[nx1][ny1] == 1 || board[nx2][ny2] == 1) return
            
            if(vis[axis.x][axis.y][axis.x+1][axis.y]) return
            q.offer(Robot(Pos(axis.x, axis.y), Pos(axis.x+1, axis.y), time+1))
            vis[axis.x][axis.y][axis.x+1][axis.y] = true
            vis[axis.x+1][axis.y][axis.x][axis.y] = true
        }
    }
}

fun turnClockWise(axis:Pos,move:Pos, time:Int, board: Array<IntArray>) {
    // 로봇 세로 방향
    if(axis.y == move.y){
        // 축이 위
        if(axis.x < move.x){
            val nx1 = axis.x
            val ny1 = axis.y - 1
            
            val nx2 = move.x
            val ny2 = move.y - 1
            
            if(ny1 < 0 || ny2 < 0) return
            if(board[nx1][ny1] == 1 || board[nx2][ny2] == 1) return
            
            if(vis[axis.x][axis.y][axis.x][axis.y-1]) return
            q.offer(Robot(Pos(axis.x, axis.y), Pos(axis.x, axis.y-1), time+1))
            vis[axis.x][axis.y][axis.x][axis.y-1] = true
            vis[axis.x][axis.y-1][axis.x][axis.y] = true
        } else{ // 축이 아래
            val nx1 = axis.x
            val ny1 = axis.y + 1
            
            val nx2 = move.x
            val ny2 = move.y + 1
            
            if(ny1 >= n || ny2 >= n) return
            if(board[nx1][ny1] == 1 || board[nx2][ny2] == 1) return
            
            if(vis[axis.x][axis.y][axis.x][axis.y+1]) return
            q.offer(Robot(Pos(axis.x, axis.y), Pos(axis.x, axis.y+1), time+1))
            vis[axis.x][axis.y][axis.x][axis.y+1] = true
            vis[axis.x][axis.y+1][axis.x][axis.y] = true
        }
        
    } else{ // 로봇 가로 방향
        // 축이 왼쪽
        if(axis.y < move.y){
            val nx1 = axis.x + 1
            val ny1 = axis.y
            
            val nx2 = move.x + 1
            val ny2 = move.y
            
            if(nx1 >= n || nx2 >= n) return
            if(board[nx1][ny1] == 1 || board[nx2][ny2] == 1) return
            
            if(vis[axis.x][axis.y][axis.x+1][axis.y]) return
            q.offer(Robot(Pos(axis.x, axis.y), Pos(axis.x+1, axis.y), time+1))
            vis[axis.x][axis.y][axis.x+1][axis.y] = true
            vis[axis.x+1][axis.y][axis.x][axis.y] = true
        } else{ // 축이 오른쪽
            val nx1 = axis.x - 1
            val ny1 = axis.y
            
            val nx2 = move.x - 1
            val ny2 = move.y
            
            if(nx1 < 0 || nx2 < 0) return
            if(board[nx1][ny1] == 1 || board[nx2][ny2] == 1) return
            
            if(vis[axis.x][axis.y][axis.x-1][axis.y]) return
            q.offer(Robot(Pos(axis.x, axis.y), Pos(axis.x-1, axis.y), time+1))
            vis[axis.x][axis.y][axis.x-1][axis.y] = true
            vis[axis.x-1][axis.y][axis.x][axis.y] = true
        }
    }
}
