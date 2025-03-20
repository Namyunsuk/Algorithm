val dp = Array(1002) { Array(2) { Array(3) { 0 } } }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var result = 0
    dp[1][0][0] = 1
    dp[1][0][1] = 1
    dp[1][0][2] = 0
    dp[1][1][0] = 1
    dp[1][1][1] = 0
    dp[1][1][2] = 0



    for (i in 2..n) {
        dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % 1_000_000
        dp[i][0][1] = dp[i - 1][0][0] % 1_000_000
        dp[i][0][2] = dp[i - 1][0][1] % 1_000_000
        dp[i][1][0] =
            (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2] + dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % 1_000_000
        dp[i][1][1] = dp[i - 1][1][0] % 1_000_000
        dp[i][1][2] = dp[i - 1][1][1] % 1_000_000
    }

    result = (dp[n][0][0] + dp[n][0][1]+dp[n][0][2] +dp[n][1][0] + dp[n][1][1] +dp[n][1][2])%1000000

    print(result)
}

