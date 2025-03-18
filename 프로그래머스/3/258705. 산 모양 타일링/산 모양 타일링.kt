val dp = Array(200002){0}

class Solution {
    fun solution(n: Int, tops: IntArray): Int {
        val cnt = 2*n+1
        
        dp[0] = 1
        dp[1] = 1
        
        for(i in 2 .. cnt){
            dp[i] = dp[i-2] + dp[i-1]
            if(i%2==0 && tops[i/2 - 1]==1){
                dp[i] +=dp[i-1]
            }
            dp[i]%=10007
        }
        
        return dp[cnt]
    }
}