val distCases = mutableListOf<List<Int>>()
val newWeaks = mutableListOf<Int>()
val vis = Array(10){false}

class Solution {
    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        var answer = Int.MAX_VALUE
        val weakSize = weak.size
        
        newWeaks.addAll(weak.toList())
        for(i in 0 until weak.size-1){
            newWeaks.add(weak[i]+n)
        }
        
        dfs(mutableListOf(), dist)
        
        for(case in distCases){
            val cnt = calMin(case, newWeaks, weak.size)
            answer = minOf(answer, cnt)
        }
        
        if(answer == Int.MAX_VALUE) answer = -1
        
        return answer
    }
}

fun calMin(case:List<Int>, newWeak:List<Int>, weakSize:Int):Int{
    var minCnt = Int.MAX_VALUE
    
    for(startIndex in 0 until weakSize){
        var caseIndex = 0
        var weakIndex = startIndex
        var isValid = true
        
        var cur = newWeak[weakIndex] + case[caseIndex]
        while(weakIndex < startIndex + weakSize){
            val weak = newWeak[weakIndex] 
            if(cur >= weak){
                weakIndex++
                continue
            }
            caseIndex++
            if(caseIndex == case.size) {
                isValid = false
                break
            }
            cur = weak + case[caseIndex]
        }
        if(isValid){
            val cnt = caseIndex+1
            minCnt = minOf(minCnt, cnt)    
        }
    }
    
    return minCnt
}

fun dfs(selected:MutableList<Int>, dist: IntArray){
    if(selected.size == dist.size){
        distCases.add(selected.toList())
        return
    }
    
    for(i in 0 until dist.size){
        if(vis[i]) continue
        
        vis[i] = true
        selected.add(dist[i])
        
        dfs(selected, dist)
        
        selected.remove(dist[i])
        vis[i] = false
    }
}






