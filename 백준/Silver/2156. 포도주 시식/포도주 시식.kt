val dp = Array(10002) { 0 }
val arr = Array(10002) { 0 }

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    for (i in 1..n) {
        arr[i] = readLine().toInt()
        if (i == 1) {
            dp[i] = arr[1]
        } else if (i == 2) {
            dp[i] = arr[1] + arr[2]
        } else if (i == 3) {
            dp[i] = maxOf(dp[2], arr[1] + arr[3], arr[2] + arr[3])
        }
    }

    for (i in 4..n) {
        dp[i] = maxOf(dp[i - 1], dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i])
    }

    print(dp[n])
}