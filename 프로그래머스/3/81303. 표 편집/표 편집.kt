import java.util.*

class Solution {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        var answer = StringBuilder()
        
        val stack = Stack<Int>()
        var size = n
        var cur = k
        
        
        for(c in cmd){
            val split = c.split(" ")
            val op = split[0]
            
            if(op=="U"){
                val X = split[1].toInt()
                cur-=X

            } else if(op=="D"){
                val X = split[1].toInt()
                cur+=X

            } else if(op=="C"){
                stack.push(cur)
                size--
                if(cur == size) cur-=1
            } else if(op=="Z"){
                val index = stack.pop()
                size++
                
                if(index<=cur) cur+=1
            }
        }
        
        answer.append("O".repeat(size))
        while (!stack.isEmpty()) {
            answer.insert(stack.pop(), "X")
        }
        
        
        return answer.toString()
    }
}