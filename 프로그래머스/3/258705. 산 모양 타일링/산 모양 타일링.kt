val dp = Array(200_002){0}

class Solution {
    fun solution(n: Int, tops: IntArray): Int {
        var answer: Int = 0
        
        dp[0] = 1
        if(tops[0]==1) dp[1] = 3
        else dp[1] = 2
        
        for(i in 2 until 2*n+1){
            dp[i] = dp[i-1] + dp[i-2]
            
            if(i%2==1){
                val j = (i-1)/2
                if(tops[j]==1) dp[i]+=dp[i-1]
            }
            
            dp[i]%=10_007
        }
        
        return dp[2*n]
    }
}