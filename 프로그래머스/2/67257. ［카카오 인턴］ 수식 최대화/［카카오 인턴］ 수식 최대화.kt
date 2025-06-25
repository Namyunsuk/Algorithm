import kotlin.math.*
import java.util.*

var answer = Long.MIN_VALUE
val op = listOf("+","-","*")

class Solution {
    fun solution(expression: String): Long {
        val splittedExpression = mutableListOf<String>()
        var numbers = 0
        for(e in expression){
            if(e=='+'||e=='-'||e=='*'){
                splittedExpression.add(numbers.toString())
                numbers = 0
                splittedExpression.add(e.toString())
                continue
            }
            val num = e.code - '0'.code
            numbers = numbers*10 + num
        }
        splittedExpression.add(numbers.toString())
        
        
        dfs(splittedExpression.toList(), mutableListOf())
        
        return answer
    }
    
    fun dfs(expression:List<String>, operations:MutableList<String>){
        if(operations.size == 3){
            val result = cal(expression, operations.toList())
            answer = max(answer, result)
            return
        }
        
        for(i in 0 until 3){
            if(operations.contains(op[i])) continue
            operations.add(op[i])
            dfs(expression, operations)
            operations.remove(op[i])
        }
    }
    
    fun cal(expression:List<String>, operations:List<String>):Long{
        val q = LinkedList<String>()
        val stack = Stack<String>()
        
        for(e in expression){
            q.offer(e)
        }
        println(operations)
        for(op in operations){
            while(!q.isEmpty()){
                if(q.first()!=op){
                    stack.push(q.poll())
                    continue
                }
                val operation = q.poll()
                val num1 = stack.pop().toLong()
                val num2 = q.poll().toLong()
                var result = 0L
                if(op=="+"){
                    result = num1 + num2
                } else if(op=="-"){
                    result = num1 - num2
                } else if(op=="*"){
                    result = num1 * num2
                }
                println(result)
                stack.push(result.toString())
            }
            
            stack.reverse()
            
            while(!stack.isEmpty()){
                q.offer(stack.pop())
            }
        }
        println()
        
        return abs(q.poll().toLong())
    }
}