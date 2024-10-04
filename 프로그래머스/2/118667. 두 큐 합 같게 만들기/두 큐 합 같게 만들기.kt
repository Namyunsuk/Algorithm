import java.util.*
import kotlin.math.*

val q1 = LinkedList<Long>()
val q2 = LinkedList<Long>()

class Solution {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer: Int = 0
        val limitSize = queue1.size * 4
        
        queue1.forEach{
            q1.offer(it.toLong())
        }
        
        queue2.forEach{
            q2.offer(it.toLong())
        }
        
        var q1Sum = q1.sum()
        var q2Sum = q2.sum()
        
        while(q1Sum != q2Sum){
            if(answer>limitSize) return -1
            if(q1Sum > q2Sum){
                q1Sum-=q1.peek()
                q2Sum+=q1.peek()
                q2.offer(q1.poll())
                answer++
                continue
            }
            q2Sum-=q2.peek()
            q1Sum+=q2.peek()
            q1.offer(q2.poll())
            answer++
        }
        
        return answer
    }
}

// 큰 -> 작으로 넣어줌

