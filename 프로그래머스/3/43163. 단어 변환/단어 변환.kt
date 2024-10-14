import kotlin.math.*

var minCnt = 100
val vis = mutableMapOf<String, Boolean>()

class Solution {
    fun solution(begin: String, target: String, words: Array<String>): Int {
        var answer = 0
        
        words.forEach{
            vis[it] = false
        }
        
        val refinedWords = words.filter{isValid(it, begin)}
        dfs(words, refinedWords, begin, target, 0)
        
        return if(minCnt==100) 0 else minCnt
    }
    
    fun dfs(words: Array<String>, refinedWords: List<String>, begin: String, target: String, cnt:Int) {
        if(begin == target) {
            minCnt = min(minCnt, cnt)
            return
        }
        
        for(i in refinedWords.indices){
            if(vis[refinedWords[i]]!!) continue
            vis[refinedWords[i]] = true
            val refinedWords2 = words.filter{isValid(it, refinedWords[i])}.filter{!vis[it]!!}
            dfs(words, refinedWords2, refinedWords[i], target, cnt+1)
            vis[refinedWords[i]] = false
        }
        
    }
    
    fun isValid(word:String, differWord:String): Boolean {
        var cnt = 0
        for(i in word.indices){
            if(word[i] != differWord[i]) cnt++
        }
        return cnt == 1
    }
}