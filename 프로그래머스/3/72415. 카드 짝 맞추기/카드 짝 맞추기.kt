import java.util.*

data class Pos(val x:Int, val y:Int, val cnt:Int = 0)

val cardTypes = mutableListOf<Int>()
val cardPositions = Array(7){mutableListOf<Pos>()}
val cases = mutableListOf<List<Pos>>()

val n = 4

val dx = listOf(-1,1,0,0)
val dy = listOf(0,0,-1,1)

var answer = Int.MAX_VALUE

class Solution {
    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        
        for(i in 0 until n){
            for(j in 0 until n){
                val type = board[i][j]
                if(type == 0) continue
                if(!cardTypes.contains(type)) cardTypes.add(type)
                cardPositions[type].add(Pos(i, j))
            }
        }
        
        dfs(mutableListOf(), mutableListOf())
        
        
        for(case in cases){
            val newBoard = Array(n){Array(n){0}}
            
            for(i in 0 until n){
                for(j in 0 until n){
                    newBoard[i][j] = board[i][j]
                }
            }
            
            val cnt = bfs(case, Pos(r, c), newBoard)
            if(cnt < answer) answer = cnt
        }
        
        
        return answer
    }

fun bfs(case:List<Pos>, cur:Pos, board: Array<Array<Int>>):Int{
    var cnt = 0
    for(i in case.indices){
        val q = LinkedList<Pos>()
        val vis = Array(n){Array(n){false}}
        val des = case[i]
        if(i==0){
            q.offer(cur)
            vis[cur.x][cur.y] = true
        }else{
            val curPos = case[i-1]
            q.offer(curPos)
            vis[curPos.x][curPos.y] = true
        }
        
        while(!q.isEmpty()){
            val cur = q.poll()
            
            if(des.x == cur.x && des.y == cur.y){
                // 엔터 포함
                cnt += cur.cnt + 1
                board[cur.x][cur.y] = 0
                break
            }
            
            for(i in 0 until 4){
                // 그냥 이동
                var nx = cur.x + dx[i]
                var ny = cur.y + dy[i]
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue
                if(!vis[nx][ny]){
                    q.offer(Pos(nx, ny, cur.cnt + 1))
                    vis[nx][ny] = true
                }
                
                // ctrl 이동
                while(true){
                    if(nx + dx[i] < 0 || nx + dx[i] >= n || ny + dy[i] < 0 || ny + dy[i] >= n) break
                    if(board[nx][ny]!=0) break
                    
                    nx += dx[i]
                    ny += dy[i]
                }
                if(!vis[nx][ny]){
                    q.offer(Pos(nx, ny, cur.cnt + 1))
                    vis[nx][ny] = true
                }
            }
        }
    }
    
    return cnt
}

    fun dfs(selected:MutableList<Pos>, complete:MutableList<Int>){
        if(complete.size == cardTypes.size){
            cases.add(selected.toList())
            return
        }
        
        for(i in 0 until cardTypes.size){
            val cardType = cardTypes[i]
            if(complete.contains(cardType)) continue
            val pos1 = cardPositions[cardType][0]
            val pos2 = cardPositions[cardType][1]
            
            complete.add(cardType)
            
            selected.add(pos1)
            selected.add(pos2)
            
            dfs(selected, complete)
            
            selected.remove(pos1)
            selected.remove(pos2)
            
            selected.add(pos2)
            selected.add(pos1)
            
            dfs(selected, complete)
            
            selected.remove(pos2)
            selected.remove(pos1)
            
            
            complete.remove(cardType)
        }
    }
}


