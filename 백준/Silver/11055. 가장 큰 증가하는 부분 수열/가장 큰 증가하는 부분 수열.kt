import kotlin.math.*

val dp = Array(1002) { 0 }
val arr = Array(1002) { 0 }

fun main() {
    val n = readln().toInt()
    val input = readln().split(" ").map { it.toInt() }

    for (i in 1..n) {
        arr[i] = input[i - 1]
    }

    dp[1] = arr[1]

    for (i in 2..n) {
        for (j in 1..i - 1) {
            if (arr[j] < arr[i]) {
                dp[i] = max(dp[i], dp[j] + arr[i])
            }
        }
        if (dp[i] == 0) {
            dp[i] = arr[i]
        }
    }

    val ans = dp.maxOf { it }
    println(ans)
}