val dp = Array(102) { Array(11) { 0 } }

fun main() {
    val n = readln().toInt()

    for (i in 1..9) {
        dp[1][i] = 1
    }

    for (i in 2..n) {
        for (j in 0..9) {
            if (j != 0) dp[i][j] += dp[i - 1][j - 1]
            if (j != 9) dp[i][j] += dp[i - 1][j + 1]
            dp[i][j] %=1000000000
        }
    }

    var ans = 0L
    for(i in 0 .. 9){
        ans += dp[n][i]
    }

    println(ans%1000000000)
}