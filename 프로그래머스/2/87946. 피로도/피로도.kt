val vis = Array(9){false}

var maxCnt = Int.MIN_VALUE

class Solution {
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        var answer: Int = -1
        
        dfs(dungeons, dungeons.size, k, 0)
        
        return maxCnt
    }
    
    fun dfs(dungeons: Array<IntArray>, n:Int, cur:Int, cnt:Int){
        maxCnt = maxOf(maxCnt, cnt)
        
        for(i in 0 until n){
            if(vis[i]) continue
            val dungeon = dungeons[i]
            if(dungeon[0]>cur) continue
            vis[i] = true
            dfs(dungeons, n, cur-dungeon[1], cnt+1)
            vis[i] = false
        }
    }
}