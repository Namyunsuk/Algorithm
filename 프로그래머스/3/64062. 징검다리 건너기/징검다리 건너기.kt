class Solution {
    fun solution(stones: IntArray, k: Int): Int {
        var answer = 0
        
        var s = 1
        var e = 200_000_002
        
        while(s<e){
            val mid = (s+e+1)/2
            
            if(isValid(mid, stones, k)) s = mid
            else e = mid-1
        }
        
        println(isValid(3,stones, k))
        
        return s
    }
    
    fun isValid(num:Int, stones: IntArray, k:Int):Boolean{
        var cnt = 0
        
        for(i in 0 until stones.size){
            if(stones[i] - num + 1 <=0) cnt++
            else cnt = 0
            
            if(cnt == k) return false
        }
        
        return true
    }
}