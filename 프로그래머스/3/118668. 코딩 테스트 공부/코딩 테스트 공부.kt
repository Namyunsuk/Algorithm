val dp = Array(304){Array(304){Int.MAX_VALUE}}

class Solution {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        var answer: Int = 0
        
        for(i in 0 .. alp){
            for(j in 0 .. cop){
                dp[i][j] = 0
            }
        }
        
        var targetAlp = alp
        var targetCop = cop
        
        problems.forEach{
            val ra = it[0]
            val rc = it[1]
            
            targetAlp = maxOf(targetAlp, ra)
            targetCop = maxOf(targetCop, rc)
        }
        
        for(i in alp .. targetAlp){
            for(j in cop .. targetCop){
                if(i==targetAlp && j==targetCop) break
                // 문제  풀이
                for(problem in problems){
                    val alp_req = problem[0]
                    val cop_req = problem[1]
                    val alp_rwd = problem[2]
                    val cop_rwd = problem[3]
                    val cost = problem[4]
                    
                    if(i < alp_req || j < cop_req ) continue
                    val a = minOf(i+alp_rwd, targetAlp)
                    val c = minOf(j+cop_rwd, targetCop)
                    
                    dp[a][c] = minOf(dp[a][c], dp[i][j] + cost)
                }
                
                // 알고력 상승
                dp[i+1][j] = minOf(dp[i+1][j], dp[i][j] + 1)
                
                // 코딩력 상승
                dp[i][j+1] = minOf(dp[i][j+1], dp[i][j] + 1)
            }
        }
        
        return dp[targetAlp][targetCop]
    }
}