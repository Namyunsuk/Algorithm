import kotlin.math.*

val dp = Array(100001) { 0 }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    for (i in 1..n) {
        dp[i] = i
        for (j in 2..i) {
            if (j * j > i) break
            dp[i] = min(dp[i], dp[i - j * j] + 1)
        }
    }

    print(dp[n])
}
