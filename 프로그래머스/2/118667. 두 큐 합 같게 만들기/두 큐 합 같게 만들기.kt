import java.util.*

class Solution {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer: Int = 0
        
        val q1 = LinkedList<Long>()
        val q2 = LinkedList<Long>()
        
        
        queue1.forEach{q1.offer(it.toLong())}
        queue2.forEach{q2.offer(it.toLong())}
        
        
        val limit = 4 * q1.size
        
        
        var q1Sum = q1.sum()
        var q2Sum = q2.sum()
        
        
        while(q1Sum!= q2Sum){
            if(answer > limit) return -1
            
            if(q1Sum > q2Sum){
                val e = q1.poll()
                q1Sum -=e
                
                q2.offer(e)
                q2Sum +=e
            }else{
                val e = q2.poll()
                q2Sum -=e
                
                q1.offer(e)
                q1Sum +=e
            }
            
            answer++
        }
        
        return answer
    }
}