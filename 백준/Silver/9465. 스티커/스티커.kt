import kotlin.math.*

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val dp = Array(2) { Array(100002) { 0 } }
        val arr = Array(2) { Array(100002) { 0 } }
        val n = readln().toInt()
        repeat(2) { index ->
            val input = readln().split(" ").map { it.toInt() }
            for (i in 0 until n) {
                arr[index][i] = input[i]
            }
        }
        dp[0][0] = arr[0][0]
        dp[1][0] = arr[1][0]

        for (i in 1 until n) {
            dp[0][i] = max(dp[0][i - 1], dp[1][i - 1] + arr[0][i])
            dp[1][i] = max(dp[1][i - 1], dp[0][i - 1] + arr[1][i])
        }

        val ans = max(dp[0][n - 1], dp[1][n - 1])
        println(ans)
    }
}