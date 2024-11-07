val dp = Array(1002) { 0 }

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    dp[1] = 1
    dp[2] = 0
    dp[3] = 1
    dp[4] = 1

    for (i in 5..n) {
        if (dp[i - 1] == 0 || dp[i - 3] == 0 || dp[i - 4] == 0) {
            dp[i] = 1
        } else {
            dp[i] = 0
        }
    }

    if (dp[n] == 1) {
        print("SK")
    } else {
        print("CY")
    }
}