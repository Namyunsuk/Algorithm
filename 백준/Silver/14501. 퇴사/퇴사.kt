import kotlin.math.*

val dp = Array(17) { 0 }
val T = Array(1002) { 0 }
val P = Array(1002) { 0 }

fun main() {
    val n = readln().toInt()
    for (i in 1..n) {
        val input = readln().split(" ").map { it.toInt() }
        T[i] = input[0]
        P[i] = input[1]
    }

    for (i in 1..n) {
        if (i + T[i] <= n + 1) {
            dp[i] = P[i]
            for (j in 1..i - 1) {
                if (j + T[j] <= i) {
                    dp[i] = max(dp[i], dp[j] + P[i])
                }
            }
        }
    }

    val ans = dp.maxOf { it }
    println(ans)
}