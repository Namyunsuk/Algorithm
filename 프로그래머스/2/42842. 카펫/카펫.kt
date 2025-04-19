class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        var answer = mutableListOf<Int>()
        
        var m = 0
        
        for(n in 1..yellow){
            if(yellow%n!=0) continue
            val m = yellow/n
            
            if((n+2)*(m+2)==(brown+yellow)){
                answer.add(n+2)
                answer.add(m+2)
                break
            }
        }
        
        answer.sortByDescending{it}
        
        
        return answer.toIntArray()
    }
}