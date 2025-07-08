import java.util.*

data class Pos(val x:Int, val y:Int, val cnt:Int = 0)

val dx = listOf(-1, 0, 1, 0)
val dy = listOf(0, 1, 0, -1)

var cardTypes = mutableListOf<Int>()

var permutation = mutableListOf<List<Pos>>()

val cache = Array(7){mutableListOf<Pos>()}

class Solution {
    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        var answer: Int = Int.MAX_VALUE
        
        val newBoard = Array(4){Array(4){0}}
        
        for(i in 0 until 4){
            for(j in 0 until 4){
                if(board[i][j]!=0){
                    cache[board[i][j]].add(Pos(i,j))
                }
                cardTypes.add(board[i][j])
            }
        }
        
        cardTypes = cardTypes.distinct().toMutableList()
        
        cardTypes.remove(0)
        
        dfs(mutableListOf(), mutableListOf())
        
        
        for(seq in permutation){
            for(i in 0 until 4){
                for(j in 0 until 4){
                    newBoard[i][j] = board[i][j]
                }
            }
            
            var sum = 0
            var curX = r
            var curY = c
            for(pos in seq){
                sum+=bfs(newBoard, curX,curY, pos)+1
                
                newBoard[pos.x][pos.y] = 0
                curX = pos.x
                curY = pos.y
            }
            if(answer > sum){
                answer = sum
            } 
        }
        
        return answer
    }
    
    fun bfs(board:Array<Array<Int>>, x: Int, y: Int, des:Pos):Int{
        val q = LinkedList<Pos>()
        val vis = Array(4){Array(4){false}}
        
        q.offer(Pos(x, y))
        vis[x][y] = true
        
        while(!q.isEmpty()){
            val cur = q.poll()
            
            if(cur.x==des.x && cur.y == des.y) return cur.cnt
            
            for(i in 0 until 4){
                var nx = cur.x + dx[i]
                var ny = cur.y + dy[i]
                
                if(nx<0 || nx>=4 || ny<0 || ny>=4) continue
                if(!vis[nx][ny]){
                    q.offer(Pos(nx, ny, cur.cnt+1))
                    vis[nx][ny] = true
                }
                
                // Ctrl + 이동
                while(true){
                    if(nx + dx[i] < 0 || nx+dx[i] >= 4 || ny+dy[i]<0||ny+dy[i]>=4) break
                    if(board[nx][ny]!=0) break
                    nx += dx[i]
                    ny += dy[i]
                }
                if(!vis[nx][ny]){
                    q.offer(Pos(nx, ny, cur.cnt+1))
                    vis[nx][ny] = true
                }

            }
        }
        return -1
    }
    
    fun dfs(selected:MutableList<Pos>, complete:MutableList<Int>){
        if(complete.size == cardTypes.size){
            permutation.add(selected.toList())
            return
        }
        
        for(i in 0 until cardTypes.size){
            val cardType = cardTypes[i]
            if(complete.contains(cardType)) continue
            val pos1 = cache[cardType][0]
            val pos2 = cache[cardType][1]
            
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