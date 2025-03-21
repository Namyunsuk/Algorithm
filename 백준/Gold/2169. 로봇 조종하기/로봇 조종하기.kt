val dp = Array(1002) { Array(1002) { 0 } }
val arr = Array(1002) { Array(1002) { 0 } }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            arr[i + 1][j + 1] = v
        }
    }

    for (i in 1..m) {
        dp[1][i] = dp[1][i - 1] + arr[1][i]
    }


    for (i in 2..n) {
        val upLeft = Array(m + 2) { 0 }
        val upRight = Array(m + 2) { 0 }
        upLeft[1] = dp[i-1][1] + arr[i][1]
        for (j in 2..m) {
            upLeft[j] = maxOf(dp[i - 1][j], upLeft[j - 1]) + arr[i][j]
        }

        upRight[m] = dp[i-1][m] + arr[i][m]
        for (j in m-1 downTo 1) {
            upRight[j] = maxOf(dp[i - 1][j], upRight[j + 1]) + arr[i][j]
        }

        for (j in 1..m) {
            dp[i][j] = maxOf(upLeft[j], upRight[j])
        }
    }

    println(dp[n][m])
}

