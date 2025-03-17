import kotlin.math.*

var answer = mutableListOf<Int>()
var maxV = Int.MIN_VALUE
val aCase = mutableListOf<Int>()
val bCase = mutableListOf<Int>()

class Solution {
    fun solution(dice: Array<IntArray>): IntArray {
        val n = dice.size
        
        dfs(0,n,0,n/2,mutableListOf(), dice)
        
        
        answer = answer.map{it+1}.toMutableList()
        
        return answer.toIntArray()
    }
}

fun dfs(idx:Int, n:Int,cnt:Int, target:Int, dices:MutableList<Int>, dice: Array<IntArray>){
    if(cnt==target){
        val bDices = mutableListOf<Int>()
        for(i in 0..n-1){
            if(!dices.contains(i)) bDices.add(i)
        }
        
        val aWinningResult = calculate(dices, bDices, dice)

        if(aWinningResult>maxV){
            answer.clear()
            answer.addAll(dices)
            maxV = aWinningResult
        }
    }
    
    for(i in idx..n-1){
        dices.add(i)
        dfs(i+1, n,cnt+1,target, dices, dice)
        dices.remove(i)
    }
}

fun calculate(aDices:List<Int>, bDices:List<Int>, dice: Array<IntArray>):Int{
    aCase.clear()
    bCase.clear()
    
    makeCases(aDices, dice, aCase)
    makeCases(bDices, dice, bCase)
    
    var aWin = 0
    
    bCase.sort()
    
    for(a in aCase){
        aWin+=binarySearch(a,bCase)
    }
    
    return aWin
}

fun makeCases(dices:List<Int>, dice: Array<IntArray>, case:MutableList<Int>){
    val n = dices.size
    val cases = (6).toDouble().pow(n).toInt()-1
    
    for(i in 0..cases){
        var sum = 0
        var c = i.toString(6)
        while(c.length!=n){
            c = "0"+c
        }

        for(j in 0 until n){
            val diceNum = dices[j]
            val dotIdx = c[j].code - '0'.code
            val dot = dice[diceNum][dotIdx]
            sum+=dot
        }

        case.add(sum)
    }
}

fun binarySearch(target:Int,case:List<Int>):Int{
    var s = 0
    var e = case.size
    
    while(s<e){
        val mid = (s+e)/2
        if(case[mid]<target) s = mid+1
        else e = mid
    }
    
    return s
}

