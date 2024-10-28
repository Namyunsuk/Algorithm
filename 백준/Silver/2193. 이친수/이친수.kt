val dp = Array(92) { Array(2) { 0L } }

fun main() {
    val n = readln().toInt()
    dp[1][1] = 1
    dp[1][0] = 0

    for (i in 2..n) {
        dp[i][0] = dp[i - 1][1] + dp[i - 1][0]
        dp[i][1] = dp[i - 1][0]
    }

    println(dp[n][0] + dp[n][1])
}