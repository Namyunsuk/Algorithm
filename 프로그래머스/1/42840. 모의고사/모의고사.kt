import java.util.*

val p1 = listOf(1,2,3,4,5) // 5
val p2 = listOf(2,1,2,3,2,4,2,5) // 8
val p3 = listOf(3,3,1,1,2,2,4,4,5,5) // 10

var p1Score = 0
var p2Score = 0
var p3Score = 0

class Solution {
    fun solution(answers: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        
        for(i in 0 until answers.size){
            val answer = answers[i]
            val p1Index = i%5
            val p2Index = i%8
            val p3Index = i%10
            
            if(answer == p1[p1Index]) p1Score++
            if(answer == p2[p2Index]) p2Score++
            if(answer == p3[p3Index]) p3Score++
        }
        
        val maxV = maxOf(p1Score, p2Score, p3Score)
        
        if(maxV==p1Score) answer.add(1)
        if(maxV==p2Score) answer.add(2)
        if(maxV==p3Score) answer.add(3)
        
        return answer.toIntArray()
    }
}