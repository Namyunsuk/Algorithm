import kotlin.math.*

val dp = Array(1002) { 0 }
val arr = Array(1002) { 0 }

fun main() {
    val n = readln().toInt()
    val input = readln().split(" ").map { it.toInt() }
    for (i in 1..n) {
        arr[i] = input[i - 1]
        dp[i] = 1
    }

    dp[1] = 1

    for (i in 2..n) {
        for (j in 1..i - 1) {
            if (arr[j] < arr[i]) {
                dp[i] = max(dp[i], dp[j] + 1)
            }
        }
    }

    val ans = dp.maxOf{it}
    println(ans)
}