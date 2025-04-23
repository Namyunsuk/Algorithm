val coins = mutableListOf<Int>()
val dp = Array(10002) { 0 }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    repeat(n) {
        coins.add(readLine().toInt())
    }

    dp[0] = 1

    for (coin in coins) {
        for (i in coin..k) {
            dp[i] += dp[i - coin]
        }
    }

    print(dp[k])
}