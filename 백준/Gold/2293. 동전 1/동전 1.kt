val dp = Array(10002) { 0 }
val units = Array(102) { 0 }

fun main() {
    val input = readln().split(" ").map { it.toInt() }
    val n = input[0]
    val k = input[1]

    for (i in 0 until n) {
        units[i] = readln().toInt()
    }

    dp[0] = 1

    for (i in 0 until n) {
        for (j in units[i]..k) {
            dp[j] += dp[j - units[i]]
        }
    }

    println(dp[k])
}