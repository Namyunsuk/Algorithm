import kotlin.math.*

var canRyanWin = false
var winningInfo = mutableListOf<Int>()
var maxDiff = 0

class Solution {
    fun solution(n: Int, info: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        
        dfs(n,mutableListOf<Int>(), info)
        
        println(maxDiff)
        
        if(!canRyanWin){
            answer.add(-1)
            return answer.toIntArray()
        }
        
        winningInfo.reverse()
        
        return winningInfo.toIntArray()
    }
    
    fun dfs(remainArrowCount:Int, ryanInfo:MutableList<Int>, info: IntArray){
        if(ryanInfo.size == 11){
            var apeach = 0
            var ryan = 0
            
            for(i in 0..10){
                if(ryanInfo[i]==0 && info[10-i]==0) continue
                else if(ryanInfo[i] > info[10-i]) ryan+=i
                else apeach+=i
            }
            
            if(ryan>apeach){
                canRyanWin = true
                val diff = ryan - apeach
                if(diff>maxDiff){
                    maxDiff = diff
                    winningInfo.clear()
                    winningInfo.addAll(ryanInfo.toList())
                }else if(diff == maxDiff){
                    for(i in 0..10){
                        if(ryanInfo[i] > winningInfo[i]){
                            winningInfo.clear()
                            winningInfo.addAll(ryanInfo.toList())
                            return
                        }
                    }
                }
            }
            
            return
        }
        
        if(ryanInfo.size == 10){
            ryanInfo.add(remainArrowCount)
            dfs(0, ryanInfo, info)
            ryanInfo.removeAt(ryanInfo.size - 1)
            
            return
        }
        
        for(i in 0..remainArrowCount){
            ryanInfo.add(i)
            dfs(remainArrowCount - i, ryanInfo, info)
            ryanInfo.removeAt(ryanInfo.size - 1)
        }
    }
}