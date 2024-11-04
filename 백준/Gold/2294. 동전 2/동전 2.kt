import kotlin.math.*

val DEFAULT = 10000000
val dp = Array(100002) { DEFAULT }
val units = Array(102) { 0 }

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }

    for (i in 1..n) {
        units[i] = readLine().toInt()
    }

    for (i in 1..n) {
        for (j in units[i]..k) {
            if (j % units[i] == 0) {
                if (dp[j - units[i]] != DEFAULT) {
                    dp[j] = minOf(dp[j], j / units[i], dp[j - units[i]] + 1)
                } else {
                    dp[j] = minOf(dp[j], j / units[i])
                }
            } else {
                if (dp[j - units[i]] != DEFAULT) {
                    dp[j] = min(dp[j], dp[j - units[i]] + 1)
                }
            }
        }
    }

    if (dp[k] == DEFAULT) {
        print(-1)
        return
    }
    print(dp[k])
}