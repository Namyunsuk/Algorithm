import kotlin.math.*

val arr = Array(1002) { Array(1002) { 0 } }
val dp = Array(1002) { Array(1002) { 0 } }

fun main() {
    val input = readln().split(" ").map { it.toInt() }

    val n = input[0]
    val m = input[1]

    for (i in 0 until n) {
        val input2 = readln().map { it.toString().toInt() }
        for (j in 0 until m) {
            arr[i][j] = input2[j]
            if (i == 0 || j == 0) {
                dp[i][j] = arr[i][j]
            }
        }
    }

    for (i in 1 until n) {
        for (j in 1 until m) {
            if (arr[i][j] == 1) {
                dp[i][j] = minOf(dp[i][j - 1], dp[i - 1][j - 1], dp[i - 1][j]) + 1
            }
        }
    }

    var ans = 0

    for (i in 0 until n) {
        for (j in 0 until m) {
            ans = max(ans, dp[i][j])
        }
    }


    println(ans * ans)
}