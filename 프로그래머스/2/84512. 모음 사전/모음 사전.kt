var cnt = 0
var flag = false

class Solution {
    fun solution(word: String): Int {
        var answer = 0
        
        val words = listOf("A","E","I","O","U")
        
        dfs(words, "", word)
        
        return cnt
    }
    
    fun dfs(words:List<String>, str:String, word:String){
        if(str == word) flag = true
        if(str.length == 5) return
        
        for(i in words){
            if(flag) return
            cnt++
            dfs(words, str.plus(i), word)
        }
    }
}