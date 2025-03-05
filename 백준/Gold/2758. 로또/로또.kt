val dp = Array(12) { Array(2001) { 0L } }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()

    for (i in 1..2000) {
        dp[1][i] = 1
    }

    for (i in 2..10) {
        for (j in 1..2000) {
            for (k in 1..j / 2) {
                dp[i][j] += dp[i - 1][k]
            }
        }
    }

    repeat(t) {
        val (n, m) = readLine().split(" ").map { it.toInt() }

        var result = 0L
        for (i in 1..m) {
            result+=dp[n][i]
        }
        println(result)
    }
}