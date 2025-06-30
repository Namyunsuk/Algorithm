val dp = Array(2*100_000+2){0}

class Solution {
    fun solution(n: Int, tops: IntArray): Int {
        var answer: Int = 0
        
        dp[0] = 1
        dp[1] = 1
        
        for(i in 2..2*n+1){
            dp[i] = dp[i-1] + dp[i-2]
            if(i%2==0 && tops[i/2 - 1] == 1) dp[i]+=dp[i-1]
            dp[i] %=10007
        }
        
        return dp[2*n+1]
    }
}