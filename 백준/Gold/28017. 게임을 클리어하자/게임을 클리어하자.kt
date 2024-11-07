import kotlin.math.*

val dp = Array(502) { Array(10002) { 0 } }
val arr = Array(502) { Array(10002) { 0 } }


fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    for (i in 1..n) {
        val input = readLine().split(" ").map { it.toInt() }
        for (j in 1..m) {
            arr[i][j] = input[j - 1]
            if (i == 1) dp[i][j] = arr[i][j]
        }
    }

    for (i in 2..n) {
        for (j in 1..m) {
            for (k in 1..m) {
                if (j == k) continue
                if (dp[i][j] == 0) {
                    dp[i][j] = dp[i - 1][k] + arr[i][j]
                    continue
                }
                dp[i][j] = min(dp[i][j], dp[i - 1][k] + arr[i][j])
            }
        }
    }

    val ans = dp[n].filter { it != 0 }.minOf { it }

    print(ans)
}
