val dp = Array(42) { 0 }
val vip = Array(42) { false }

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()

    for (i in 1..m) {
        vip[readln().toInt()] = true
    }

    dp[1] = 1
    dp[2] = if (vip[1] || vip[2]) 1 else 2

    for (i in 3..n) {
        if (vip[i - 1] || vip[i]) {
            dp[i] = dp[i - 1]
        } else {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
    }

    println(dp[n])
}