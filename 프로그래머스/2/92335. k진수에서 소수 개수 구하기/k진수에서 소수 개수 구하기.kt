import kotlin.math.*

class Solution {
    fun solution(n: Int, k: Int): Int {
        var answer: Int = 0
        
        val str = n.toString(k)
        
        var num = 0L
        for(s in str){
            if(s == '0'){
                // 소수인지 판별
                if(num!=0L && isPrime(num)) answer++
                num = 0L
                continue
            }
            num = num * 10L + (s.code - '0'.code).toLong()
        }
        
        if(num!=0L && isPrime(num)) answer++
        
        return answer
    }
    
    fun isPrime(num:Long):Boolean{
        if(num <= 1L) return false
        if(num  == 2L || num == 3L) return true
        val sqrtNum = sqrt(num.toDouble()).toLong()
        for(i in 2 .. sqrtNum){
            if(num % i == 0L) return false
        }
        return true
    }
}