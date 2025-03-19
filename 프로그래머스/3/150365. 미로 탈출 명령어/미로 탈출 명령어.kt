import java.util.*

class Pos(val x:Int, val y:Int, val path:String = "", val dist:Int = 0)

val q = LinkedList<Pos>()
val dx = listOf(1,0,0,-1)
val dy = listOf(0,-1,1,0)
val vis = Array(51){Array(51){Array(2501){false}}}

class Solution {
    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        var answer: String = "impossible"
        
        q.offer(Pos(x,y))
        while(!q.isEmpty()){
            val cur = q.poll()
            for(i in 0..3){
                val nx = cur.x+dx[i]
                val ny = cur.y+dy[i]
                val path = cur.path + convertToPath(dx[i], dy[i])
                
                if(nx<1||nx>n||ny<1||ny>m) continue
                if(vis[nx][ny][cur.dist+1]) continue
                if(cur.dist+1 > k) continue
                if(cur.dist+1 == k && nx==r&&ny==c){
                    return path
                }
                vis[nx][ny][cur.dist+1] = true
                q.offer(Pos(nx, ny, path, cur.dist+1))
            }
        }
        
        
        return answer
    }
}

fun convertToPath(dx:Int, dy:Int):String{
    return when(dx to dy){
        1 to 0 -> "d"
        0 to -1 -> "l"
        0 to 1 -> "r"
        else -> "u"
    }
}