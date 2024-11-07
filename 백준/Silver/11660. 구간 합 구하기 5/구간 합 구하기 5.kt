val dp = Array(1026) { Array(1026) { 0 } }

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    for (i in 1..n) {
        val input = readLine().split(" ").map { it.toInt() }
        for (j in 1..n) {
            dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + input[j - 1]
        }
    }

    val sb = StringBuilder()
    repeat(m) {
        val (x1, y1, x2, y2) = readLine().split(" ").map { it.toInt() }
        val ans = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1]
        sb.appendLine(ans)
    }
    print(sb)
}
