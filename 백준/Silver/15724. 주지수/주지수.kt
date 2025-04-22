import java.util.*

val arr = Array(1026) { Array(1026) { 0 } }
val dp = Array(1026) { Array(1026) { 0 } }
val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    for (i in 1..n) {
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            arr[i][j + 1] = v
        }
    }

    for (i in 1..n) {
        for (j in 1..m) {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + arr[i][j]
        }
    }

    val k = readLine().toInt()
    for (i in 0 until k) {
        val (x1, y1, x2, y2) = readLine().split(" ").map { it.toInt() }
        sb.appendLine(solve(x1,y1,x2,y2))
    }
    print(sb)
}

fun solve(x1: Int, y1: Int, x2: Int, y2: Int): Int {
    return dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1]
}
