import kotlin.math.*

val dp = Array(502) { Array(502) { 0 } }
val arr = Array(502) { Array(502) { 0 } }

fun main() {
    val n = readln().toInt()

    for (i in 1..n) {
        val input = readln().split(' ').map { it.toInt() }
        for (j in 1..i) {
            arr[i][j] = input[j - 1]
        }
    }

    dp[1][1] = arr[1][1]

    for (i in 2..n) {
        for (j in 1..n) {
            if (j == 1) {
                dp[i][j] = dp[i - 1][j] + arr[i][j]
                continue
            }
            dp[i][j] = max(dp[i - 1][j - 1], dp[i - 1][j]) + arr[i][j]
        }
    }

    val max = dp[n].maxOf { it }

    println(max)
}