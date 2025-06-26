import java.util.*

var answer = ""

class Solution {
    fun solution(p: String): String {
        
        
        sol(p)
        
        
        return answer
    }
    
    fun sol(p:String){
        if(p=="") return
        val u = makeU(p)
        val v = p.substring(u.length, p.length)
        if(isRight(u)){
            answer+=u
            sol(v)
        }else{
            answer+="("
            sol(v)
            answer+=")"
            for(i in 1 until u.length-1){
                if(u[i]=='('){
                    answer+=")"
                }else{
                    answer+="("
                }
            }
        }
    }
    
    fun makeU(w:String):String {
        for(i in 1 .. w.length){
            val splitted = w.substring(0,i)
            if(isBalanced(splitted)) return splitted
        }
        
        return w
    }
    
    fun isRight(str:String):Boolean{
        val stack = Stack<Char>()
        
        for(c in str){
            if(c=='('){
                if(stack.isNotEmpty() && stack.peek() == ')'){
                    stack.pop()
                    continue
                }
                stack.push(c)
            }else if(c==')'){
                if(stack.isEmpty() || stack.peek()==')') return false
                if(stack.peek() == '('){
                    stack.pop()
                    continue
                }
                stack.push(c)
            }
        }
        
        return stack.isEmpty()
    }
    
    fun isBalanced(s:String):Boolean{
        var preCnt = 0
        var postCnt = 0
        
        for(c in s){
            if(c == '(') preCnt++
            else postCnt++
        }
        return preCnt == postCnt
    }
}