val dp = Array(1002) { Array(10) { 0 } }

fun main() {
    val n = readln().toInt()

    for (i in 0..9) {
        dp[1][i] = 1
    }

    for (i in 2..n) {
        for (j in 0..9) {
            for (k in 0..j) {
                dp[i][j] += dp[i - 1][k]
            }
            dp[i][j] %= 10007
        }
    }

    val ans = dp[n].sumOf { it }
    println(ans % 10007)
}