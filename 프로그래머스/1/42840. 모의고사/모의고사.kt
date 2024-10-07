import kotlin.math.*
import java.util.*

val p1 = mutableListOf(1,2,3,4,5) // 5
var p1_score = 0
val p2 = mutableListOf(2, 1, 2, 3, 2, 4, 2, 5) // 8
var p2_score = 0
val p3 = mutableListOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5) // 10
var p3_score = 0

class Solution {
    fun solution(answers: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        var max_score = 0
        
        for(i in answers.indices){
            if(answers[i] == p1[i%5]) p1_score++
            if(answers[i] == p2[i%8]) p2_score++
            if(answers[i] == p3[i%10]) p3_score++
        }
        
        max_score = max(p1_score, p2_score)
        max_score = max(max_score, p3_score)
        
        if(p1_score == max_score) answer.add(1)
        if(p2_score == max_score) answer.add(2)
        if(p3_score == max_score) answer.add(3)
        
        return answer.toIntArray()
    }
}