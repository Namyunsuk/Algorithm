val cases = mutableListOf<List<Int>>()
var answer = Int.MAX_VALUE
val newWeak = mutableListOf<Int>()
var weakSize = 0
val vis = Array(10){false}

class Solution {
    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        
        weakSize = weak.size
        weak.forEach{
            newWeak.add(it)
            newWeak.add(it + n)
        }
        
        newWeak.sort()
        
        dfs(mutableListOf(), dist, weak)
        
        if(answer == Int.MAX_VALUE) return -1
        
        return answer
    }
    
    fun isValid(dist:List<Int>, weak: IntArray):Boolean{
        for(i in 0 until weakSize){
            var valid = true
            var curIndex = i
            val endIndex = i + weakSize - 1
            val end = newWeak[endIndex]
            var distIndex = 0
            
            var cur = newWeak[curIndex] + dist[distIndex]
            
            while(curIndex <= endIndex){
                val weak = newWeak[curIndex]
                
                if(cur >= weak){
                    curIndex++
                    continue
                }
                
                distIndex++
                
                if(distIndex >= dist.size){
                    valid = false
                    break
                }
                cur = weak + dist[distIndex]
                   
            }
            if(valid) return true
        }
        
        return false
    }
    
    fun dfs(selected:MutableList<Int>, dist: IntArray, weak: IntArray){
        if(selected.size > 0){
            if(isValid(selected.toList(), weak)){
                if(selected.size < answer) answer = selected.size
            }
        }
        
        if(selected.size == dist.size) return
        
        for(i in dist.indices){
            if(vis[i]) continue
            
            vis[i] = true
            selected.add(dist[i])
            
            dfs(selected, dist, weak)
            
            selected.removeAt(selected.size - 1)
            vis[i] = false
        }
    }
}



