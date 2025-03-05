val dp = Array(51) { Array(1001) { 0 } }
val students = Array(51) { mutableListOf<Int>() }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m, h) = readLine().split(" ").map { it.toInt() }
    repeat(n) { i ->
        students[i + 1].addAll(readLine().split(" ").map { it.toInt() })
        students[i + 1].add(0)
    }

    students[1].forEach {
        dp[1][it] = 1
    }

    for (i in 2..n) {
        students[i].forEach {
            for (l in it..h) {
                dp[i][l] += dp[i - 1][l - it]
                dp[i][l]%=10007
            }
        }
    }

    print(dp[n][h])
}