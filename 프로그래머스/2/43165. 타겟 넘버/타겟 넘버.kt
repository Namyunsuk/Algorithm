import kotlin.math.*
import java.util.*

var cnt = 0
var n = 0
val vis = Array(21){false}

class Solution {
    fun solution(numbers: IntArray, target: Int): Int {
        var answer = 0
        n = numbers.size
        dfs(numbers, 0, 0, target)
        
        return cnt
    }
    
    fun dfs(numbers:IntArray, index:Int, sum:Int, target:Int){
        if(n == index){
            if(sum == target){
                cnt++
            }
            return   
        }
        
        dfs(numbers, index+1, sum+numbers[index], target)
        dfs(numbers, index+1, sum-numbers[index], target)
    }
}
