import kotlin.math.*

val wallets = mutableListOf<IntArray>()

class Solution {
    fun solution(sizes: Array<IntArray>): Int {
        var answer: Int = 0
        var max_w = 0
        var max_h = 0
        
        sizes.forEach{
            if(it[0]<it[1]){ // 세로가 더 긴 경우
                it.reverse()
                wallets.add(it)
                return@forEach
            }
            wallets.add(it)
        }
        
        for(wallet in wallets){
            max_w = max(max_w, wallet[0])
            max_h = max(max_h, wallet[1])
        }
        
        answer = max_w * max_h
        
        return answer
    }
}