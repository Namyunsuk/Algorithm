val dp = Array(152){Array(152){Int.MAX_VALUE}}

class Solution {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        var answer: Int = 0
        
        var maxAlp = alp
        var maxCop = cop
        
        problems.forEach{ problem->
            val alp_req = problem[0]
            val cop_req = problem[1]
            
            maxAlp = maxOf(alp_req, maxAlp)
            maxCop = maxOf(cop_req, maxCop)
        }
        
        for(i in 0 .. alp){
            for(j in 0 .. cop){
                dp[i][j] = 0
            }
        }
        
        for(i in alp .. maxAlp){
            for(j in cop .. maxCop){
                dp[i+1][j] = minOf(dp[i+1][j], dp[i][j] + 1)
                dp[i][j+1] = minOf(dp[i][j+1], dp[i][j] + 1)
                
                for(problem in problems){
                    val alp_req = problem[0]
                    val cop_req = problem[1]
                    
                    val alp_rwd = problem[2]
                    val cop_rwd = problem[3]
                    
                    val cost = problem[4]
                    
                    if(i >= alp_req && j >= cop_req){
                        val totalAlp = minOf(i+alp_rwd, maxAlp)
                        val totalCop = minOf(j+cop_rwd, maxCop)
                        
                        dp[totalAlp][totalCop] = minOf(dp[totalAlp][totalCop], dp[i][j] + cost)
                    }
                }
            }
        }
        
        
        return dp[maxAlp][maxCop]
    }
}