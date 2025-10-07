import java.util.*
import kotlin.math.*

data class Pos(val x:Int, val y:Int, val dist:Int = 0)

val dx = listOf(-1,1,0,0)
val dy = listOf(0,0,-1,1)

class Solution {
    fun solution(places: Array<Array<String>>): IntArray {
        var answer = mutableListOf<Int>()
        
        places.forEach{ place->
            if(isValid(place)) answer.add(1)
            else answer.add(0)
        }
        
        return answer.toIntArray()
    }
    
    fun isValid(place: Array<String>):Boolean{
        val q = LinkedList<Pos>()
        val n = place.size
        val m = place[0].length
        
        for(i in 0 until n){
            for(j in 0 until m){
                if(place[i][j] != 'P') continue
                
                q.offer(Pos(i, j))
                
                while(!q.isEmpty()){
                    val cur = q.poll()
                    
                    for(dir in 0 until 4){
                        val nx = cur.x + dx[dir]
                        val ny = cur.y + dy[dir]
                        val dist = cur.dist +  1
                        
                        // 본인은 제외
                        if(nx == i && ny == j) continue
                        if(dist > 2) continue
                        if(nx<0 || nx>=n || ny<0 || ny>=m) continue
                        if(place[nx][ny] == 'X') continue
                        if(place[nx][ny] == 'P'){
                            return false
                        }
                        
                        q.offer(Pos(nx, ny, dist))
                    }
                }
            }
        }
        
        return true
    }
}