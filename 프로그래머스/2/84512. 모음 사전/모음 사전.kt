import java.util.*

val alphabets = listOf("A","E","I","O","U")
var ans = 0
var cnt = 0

class Solution {
    fun solution(word: String): Int {
        
        dfs(word, "", 5)
        
        return ans
    }
    
    fun dfs(target:String, str:String, size:Int){
        if(str==target){
            ans = cnt
            return
        }
        if(str.length == size) return
        
        for(i in 0 until 5){
            cnt++
            dfs(target, str+alphabets[i],size)
        }
    }
}