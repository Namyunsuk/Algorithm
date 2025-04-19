import java.util.*
import kotlin.math.*

val primes = HashSet<Int>()
val vis = Array(8){false}

class Solution {
    fun solution(numbers: String): Int {
        var answer = 0
        
        val nums = numbers.split("").filter{it.isNotBlank()}.map{it.toInt()}
        
        dfs(nums, 0, nums.size)
        
        answer = primes.size
        
        return answer
    }
}

fun dfs(nums:List<Int>, number:Int, n:Int){
    if(isPrime(number)){
        primes.add(number)
    }
    
    for(i in 0 until n){
        if(vis[i]) continue
        vis[i] = true
        val num = nums[i]
        dfs(nums, number*10+num, n)
        vis[i] = false
    }
}

fun isPrime(num:Int):Boolean{
    if(num<=1) return false
    if(num==2) return true
    val end = sqrt(num.toDouble()).toInt()
    for(i in 2..end){
        if(num%i==0) return false
    }
    return true
}