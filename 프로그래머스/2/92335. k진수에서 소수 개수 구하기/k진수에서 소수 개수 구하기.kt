import kotlin.math.sqrt
import java.util.*

class Solution {
    fun solution(n: Int, k: Int): Int {
        var answer: Int = 0
        
        val strNum = calculate(n, k)
        val splittedNum = strNum.split("0").filter{
            it.isNotBlank()
        }.map{it.toLong()}
        .filter{isPrime(it)}
        
        answer = splittedNum.size
        
        return answer
    }
    
    fun calculate(num : Int, k:Int):String{
        return num.toString(k)
    }
    
    fun isPrime(num: Long) : Boolean{
        if(num==1L) return false
        if(num==2L) return true
        for(i in 3 .. sqrt(num.toDouble()).toInt()){
            if(num%i == 0L) return false
        }
        return true
    }
}