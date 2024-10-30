import kotlin.math.*

val dp = Array(3) { Array(1003) { Array(32) { 0 } } }
val arr = Array(1003) { 0 }

fun main() {
    val input = readln().split(" ").map { it.toInt() }
    val T = input[0]
    val W = input[1]

    for (i in 1..T) {
        arr[i] = readln().toInt()
    }

    if (arr[1] == 1) {
        dp[1][1][0] = 1
        dp[2][1][1] = 0
    } else {
        dp[1][1][0] = 0
        dp[2][1][1] = 1
    }

    for (t in 2..T) {
        if (arr[t] == 1) { // 1번 나무
            dp[1][t][0] = dp[1][t - 1][0] + 1
            dp[2][t][0] = dp[2][t - 1][0]

            for (w in 1..W) {
                dp[1][t][w] = max(dp[1][t - 1][w], dp[2][t - 1][w - 1]) + 1
                dp[2][t][w] = max(dp[2][t - 1][w], dp[1][t - 1][w - 1])
            }
        } else { // 2번 나무
            dp[1][t][0] = dp[1][t - 1][0]
            dp[2][t][0] = dp[2][t - 1][0] + 1

            for (w in 1..W) {
                dp[1][t][w] = max(dp[1][t - 1][w], dp[2][t - 1][w - 1])
                dp[2][t][w] = max(dp[2][t - 1][w], dp[1][t - 1][w - 1]) + 1
            }
        }
    }

    val ans = max(dp[1][T].maxOf { it }, dp[2][T].maxOf { it })

    println(ans)
}