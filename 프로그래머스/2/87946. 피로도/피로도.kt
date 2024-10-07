import kotlin.math.*
import java.util.*

var max_cnt = 0
var vis = Array(9){false}

class Solution {
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        var answer: Int = -1
        
        dfs(k, 0, dungeons)
        answer = max_cnt
        
        return answer
    }
    
    fun dfs(k:Int, cnt:Int, dungeons: Array<IntArray>){
        max_cnt = max(max_cnt, cnt)
        
        for(i in dungeons.indices){
            if(k < dungeons[i][0] || vis[i]) continue
            vis[i] = true
            dfs(k-dungeons[i][1], cnt+1, dungeons)
            vis[i] = false
        }
    }
}