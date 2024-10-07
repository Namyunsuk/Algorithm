import kotlin.math.*
import java.util.*

val sepNums = mutableListOf<String>()
val combNums = mutableListOf<Int>()
val vis = Array(8){false}

class Solution {
    fun solution(numbers: String): Int {
        var answer = 0
        
        numbers.split("").filter{!it.isEmpty()}.forEach{
            sepNums.add(it)
        }
        
        dfs(sepNums, "")
        
        answer = combNums.distinct().filter{isPrime(it)}.size
        
        return answer
    }
    
    fun dfs(numbers:MutableList<String>, combNum:String){
        if(!combNum.isEmpty()) combNums.add(combNum.toInt())
        for(i in numbers.indices){
            if(vis[i]) continue
            vis[i] = true
            dfs(numbers, combNum+numbers[i])
            vis[i] = false
        }
    }
    
    fun isPrime(num:Int):Boolean{
        if(num==0|| num==1) return false
        if(num==2) return true
        for(i in 2 .. sqrt(num.toDouble()).toInt()){
            if(num % i == 0) return false
        }
        return true
    }
}