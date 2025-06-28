import kotlin.math.*

val answer = mutableListOf<Int>()
var maxCnt = 0

class Solution {
    fun solution(dice: Array<IntArray>): IntArray {
        
        
        dfs(0,mutableListOf(), dice.size/2, dice)
        
        println(maxCnt)
        
        
        
        return answer.map{it+1}.toIntArray()
    }
    
    fun dfs(index:Int, selected:MutableList<Int>, targetCnt:Int, dice: Array<IntArray>){
        if(selected.size == targetCnt){
            val dices = (0..(dice.size-1)).map{it}.toList()
            val aDices = selected.toList()
            val bDices = dices - aDices
            
            val aWinningCnt = calAwinningCnt(aDices, bDices, dice)
            if(maxCnt < aWinningCnt){
                maxCnt = aWinningCnt
                answer.clear()
                answer.addAll(selected.toList())
            }
            
            return
        }
        
        for(i in index until dice.size){
            selected.add(i)
            dfs(i+1, selected, targetCnt, dice)
            selected.removeAt(selected.size-1)
        }
    }
    
    fun calAwinningCnt(aDices:List<Int>, bDices:List<Int>, dice: Array<IntArray>):Int{
        val aDice = mutableListOf<List<Int>>()
        val bDice = mutableListOf<List<Int>>()
        
        for(i in aDices){
            aDice.add(dice[i].toList())
        }
        
        for(i in bDices){
            bDice.add(dice[i].toList())
        }
        
        val aCases = makeAllCases(aDice)
        val bCases = makeAllCases(bDice)
        
        var aWinningCnt = 0
        
        for(c in aCases){
            aWinningCnt+=lowerIdx(c, bCases)
        }
        
        if(aDices == listOf(0,3)){
            println(aDice)
            println(aCases)
        }
        
        return aWinningCnt
    }
    
    
    fun lowerIdx(target:Int, cases:List<Int>):Int{
        var s = 0
        var e = cases.size
        
        while(s<e){
            val mid = (s+e)/2
            if(cases[mid] >= target) e = mid
            else s = mid+1
        }
        
        return s
    }
    
    fun makeAllCases(dices:List<List<Int>>):List<Int>{
        val n = dices.size
        val cases = ((6.0).pow(n) - 1).toInt()
        var sums = mutableListOf<Int>()
        
        for(i in 0 .. cases){
            var case = i.toString(6)
            var sum = 0
            
            while(case.length < n){
                case = "0" + case
            }
            
            for(diceNum in 0 until dices.size){
                val surfaceIndex = case[diceNum].code - '0'.code
                sum += dices[diceNum][surfaceIndex]
            }
            sums.add(sum)
        }
        
        sums.sort()
        
        return sums
    }
}