fun main() {
    val t = readln().toInt()

    val dp = Array(1000003) { 0L }

    dp[1] = 1
    dp[2] = 2
    dp[3] = 4

    for (j in 4..1000000) {
        dp[j] = (dp[j - 1] + dp[j - 2] + dp[j - 3]) % 1000000009
    }

    repeat(t) {
        val n = readln().toInt()
        println(dp[n])
    }
}