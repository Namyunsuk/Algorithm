val dp = Array(1001) { 0 }

fun main() {
    val n = readln().toInt()

    dp[1] = 1
    dp[2] = 3

    for (i in 3..n) {
        dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007
    }

    println(dp[n])
}