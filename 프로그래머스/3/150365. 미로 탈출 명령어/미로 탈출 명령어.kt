import java.util.*

class Pos(val x:Int, val y:Int, val path:String = "")

val vis = Array(2501){Array(51){Array(51){false}}}
val q = LinkedList<Pos>()
val dx = listOf(1,0,0,-1)
val dy = listOf(0,-1,1,0)

class Solution {
    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        var answer: String = ""
        
        q.offer(Pos(x,y))
        
        while(!q.isEmpty()){
            val cur = q.poll()
            
            for(i in 0 until 4){
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]
                val dist = cur.path.length + 1
                
                if(nx<1 || nx>n || ny<1 || ny>m) continue
                if(dist > k) continue
                if(vis[dist][nx][ny]) continue
                if(dist == k){
                    if(nx == r && ny == c){
                        return cur.path + dirToString(dx[i], dy[i])                    
                    }
                    continue
                }
                
                val newPath = cur.path + dirToString(dx[i], dy[i])
                
                q.offer(Pos(nx, ny, newPath))
                vis[dist][nx][ny] = true
            }
        }
        
        return "impossible"
    }
}

fun dirToString(dx:Int, dy:Int):String{
    if(dx==1&&dy==0){
        return "d"
    } else if(dx==0&&dy==-1){
        return "l"
    } else if(dx==0&&dy==1){
        return "r"
    }
    return "u"
}