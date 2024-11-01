import kotlin.math.*

fun main() {
    var t = 1

    while (true) {
        val arr = Array(100002) { Array(4) { 0 } }
        val dp = Array(100002) { Array(4) { 0 } }
        val n = readln().toInt()

        if (n == 0) break

        for (i in 0 until n) {
            val input = readln().split(" ").map { it.toInt() }
            for (j in 0..2) {
                arr[i][j] = input[j]
            }
        }

        dp[0][0] = 1001
        dp[0][1] = arr[0][1]
        dp[0][2] = arr[0][1] + arr[0][2]

        for (i in 1 until n) {
            for (j in 0..2) {
                dp[i][0] = min(dp[i - 1][0], dp[i - 1][1]) + arr[i][0]
                dp[i][1] = minOf(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2], dp[i][0]) + arr[i][1]
                dp[i][2] = minOf(dp[i - 1][1], dp[i - 1][2], dp[i][1]) + arr[i][2]
            }
        }
        println("${t}. ${dp[n - 1][1]}")
        t++
    }

}