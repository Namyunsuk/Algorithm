import kotlin.math.*

val dp = Array(10002) { 0 }
val arr = Array(10002) { 0 }

fun main() {
    val n = readln().toInt()

    for (i in 1..n) {
        arr[i] = readln().toInt()
    }

    dp[1] = arr[1]
    dp[2] = arr[1] + arr[2]
    dp[3] = max(max(arr[1] + arr[2], arr[1] + arr[3]), arr[2] + arr[3])

    for (i in 4..n) {
        dp[i] = max(dp[i - 2], dp[i - 3] + arr[i - 1]) + arr[i]
        dp[i] = max(dp[i - 1], dp[i])
    }

    val ans = dp.maxOf { it }

    println(ans)
}