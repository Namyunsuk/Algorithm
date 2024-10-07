import kotlin.math.*

val 가로세로모음 = mutableListOf<Pair<Int, Int>>()

class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        var answer = mutableListOf<Int>()
        
        val width = brown + yellow
        
        for(i in 1 .. sqrt(width.toDouble()).toInt()){
            if(width%i==0){
                val 가로 = width/i
                val 세로 = i
                가로세로모음.add(가로 to 세로)
            }
        }
        
        val tmp = 가로세로모음.filter{
            해결책(it.first, it.second, brown)
        }
        
        answer.add(tmp[0].first)
        answer.add(tmp[0].second)
        
        return answer.toIntArray()
    }
    
    fun 해결책(가로: Int, 세로: Int, 갈색수:Int):Boolean{
        return 가로 + 가로 + (세로-2) + (세로-2) == 갈색수
    }
}

// ㅁ..ㅗ..ㄹ..ㅡ..ㄱ..ㅔ..ㅆ..ㄷ..ㅏ
// -^-^r  ~~^_^~~

// 가로 세로 길이 랜덤 돌돌(가로>=세로)
// 가로 + 가로 + (세로-1) + (세로-1) = 갈색 수
