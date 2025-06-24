import java.util.*

data class Pos(val x:Int, val y:Int, val dist:Int = 0, val hasWall:Boolean = false)

val dx = listOf(-1,1,0,0)
val dy = listOf(0,0,-1,1)

class Solution {
    fun solution(places: Array<Array<String>>): IntArray {
        var answer = mutableListOf<Int>()
        
        for(place in places){
            val q = LinkedList<Pos>()
            
            var isValid = true
            for(i in 0 until 5){
                for(j in 0 until 5){
                    val vis = Array(5){Array(5){false}}

                    if(place[i][j]!='P') continue
                    q.offer(Pos(i,j))
                    vis[i][j] = true
                    
                    while(!q.isEmpty()){
                        val cur = q.poll()
                        
                        if(cur.dist >= 2) continue
                        
                        for(dir in 0 .. 3){
                            val nx = cur.x + dx[dir]
                            val ny = cur.y + dy[dir]
                            
                            if(nx<0 || nx>=5 || ny<0 || ny>=5) continue
                            if(vis[nx][ny]) continue
                            if(place[nx][ny] == 'P'){
                                if(!cur.hasWall){
                                    isValid = false
                                    vis[nx][ny] = true
                                    break
                                }
                            }
                            
                            vis[nx][ny] = true
                            val hasWall = if(place[nx][ny]=='X') true else cur.hasWall
                            q.offer(Pos(nx,ny,cur.dist+1,hasWall))
                        }
                        
                        if(!isValid) break
                    }
                    if(!isValid) break
                }
                if(!isValid) break
            }
            if(isValid) answer.add(1)
            else answer.add(0)
        }
        
        return answer.toIntArray()
    }
}