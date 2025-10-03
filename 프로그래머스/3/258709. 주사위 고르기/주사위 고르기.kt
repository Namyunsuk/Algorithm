val answer = mutableListOf<Int>()
var maxCnt = Int.MIN_VALUE
val selectNumCases = mutableListOf<List<Int>>()

class Solution {
    fun solution(dice: Array<IntArray>): IntArray {
        
        sumDfs(mutableListOf(), dice.size/2)
        dfs(0, mutableListOf(), dice.size, dice)
        
        return answer.map{it+1}.toIntArray()
    }
}

fun sumDfs(selected:MutableList<Int>, n:Int){
    if(selected.size == n){
        selectNumCases.add(selected.toList())
        
        return
    }
    
    for(i in 0..5){
        selected.add(i)
        sumDfs(selected, n)
        selected.removeAt(selected.size-1)
    }
}

fun dfs(index:Int, aDices:MutableList<Int>, n:Int, dice: Array<IntArray>){
    if(aDices.size == n/2){
        val bDices = mutableListOf<Int>()
        
        for(i in 0 until n){
            if(!aDices.contains(i)) bDices.add(i)
        }
        
        val cnt = calAwinningCount(aDices, bDices, dice)
        if(cnt > maxCnt){
            answer.clear()
            answer.addAll(aDices.toList())
            maxCnt = cnt
        }
        
        return
    }
    
    for(i in index until n){
        if(aDices.contains(i)) continue
        aDices.add(i)
        
        dfs(i+1, aDices, n, dice)
        
        aDices.remove(i)
    }
}

fun calAwinningCount(aDices:List<Int>, bDices:List<Int>, dice: Array<IntArray>):Int{
    
    val aSums = mutableListOf<Int>()
    val bSums = mutableListOf<Int>()
    
    for(case in selectNumCases){
        var aSum = 0
        var bSum = 0
        for(i in 0 until case.size){
            val aDiceIndex = aDices[i]
            val bDiceIndex = bDices[i]
            
            val numIndex = case[i]
            
            aSum += dice[aDiceIndex][numIndex]
            bSum += dice[bDiceIndex][numIndex]
        }
        aSums.add(aSum)
        bSums.add(bSum)
    }

    bSums.sort()
    
    var cnt = 0
    
    for(sum in aSums){
        cnt+=lower(bSums, sum)
    }
    
    return cnt
}

fun lower(sums:List<Int>, target:Int):Int{
    var s = 0
    var e = sums.size
    
    while(s<e){
        val mid = (s+e)/2
        
        if(sums[mid] >= target) e = mid
        else s = mid+1
    }
    
    return s
}