import java.util.*

class Pos(val x:Int, val y:Int, val path:String="", val dist:Int=0)

val vis = Array(52){Array(52){Array(2502){false}}}
val dx = listOf(1, 0, 0, -1)
val dy = listOf(0, -1, 1, 0)

val q = LinkedList<Pos>()

class Solution {
    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        var answer: String = ""
        
        q.offer(Pos(x,y))
        
        while(!q.isEmpty()){
            val cur = q.poll()
            
            for(i in 0 .. 3){
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]
                val dist = cur.dist + 1
                val path = cur.path + dToS(dx[i], dy[i])
                
                if(nx<1 || nx > n || ny < 1 || ny > m) continue
                if(dist>k) continue
                if(vis[nx][ny][dist]) continue
                if(nx == r && ny == c && dist == k) return path
                
                vis[nx][ny][dist] = true
                q.offer(Pos(nx, ny, path, dist))
            }
        }
        
        
        return "impossible"
    }
}

fun dToS(dx:Int, dy:Int):String{
    if(dx == 1 && dy == 0) return "d"
    else if(dx == 0 && dy == -1) return "l"
    else if(dx == 0 && dy == 1) return "r"
    
    return "u"
}