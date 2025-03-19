val dp = Array(151){Array(151){Int.MAX_VALUE}}

class Solution {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        var answer: Int = 0
        var maxAlp = Int.MIN_VALUE
        var maxCop = Int.MIN_VALUE
        
        problems.forEach{
            maxAlp = maxOf(maxAlp, it[0])
            maxCop = maxOf(maxCop, it[1])
        }
        
        maxAlp = maxOf(maxAlp, alp)
        maxCop = maxOf(maxCop, cop)
        
        dp[alp][cop] = 0
        
        
        for(i in alp .. maxAlp){
            for(j in cop .. maxCop){
                if(i+1<=maxAlp){
                    dp[i+1][j] = minOf(dp[i+1][j], dp[i][j] + 1)   
                }
                if(j+1<=maxCop){
                    dp[i][j+1] = minOf(dp[i][j+1], dp[i][j] + 1)   
                }
                
                for((alp_req, cop_req, alp_rwd, cop_rwd, cost) in problems){
                    if(i>=alp_req&&j>=cop_req){
                        val newI = minOf(maxAlp, i+alp_rwd)
                        val newJ = minOf(maxCop, j+cop_rwd)
                        dp[newI][newJ] = minOf(dp[newI][newJ], dp[i][j] + cost)
                    }
                }
            }
        }
        
        
        return dp[maxAlp][maxCop]
    }
}