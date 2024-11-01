val dp = Array(1000002) { 0 }

fun main() {
    val n = readln().toInt()
    dp[1] = 1
    dp[2] = 2

    for (i in 3..n) {
        dp[i] = dp[i - 1] + dp[i - 2]
        dp[i] %= 15746
    }

    println(dp[n] % 15746)
}