import java.io.*

val dp = Array(2002) { Array(2002) { 0 } }
val arr = Array(2002) { 0 }

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val input = readLine().split(" ").map { it.toInt() }

    for (i in 1..n) {
        arr[i] = input[i - 1]
        dp[i][i] = 1
    }

    val m = readLine().toInt()

    for (i in 2..n) {
        for (j in 1..i - 1) {
            if (arr[i] == arr[j]) {
                if (i - j <= 1) {
                    dp[j][i] = 1
                } else if (dp[j + 1][i - 1] == 1) {
                    dp[j][i] = 1
                }
            }
        }
    }

    val sb = StringBuilder()

    repeat(m) {
        val input2 = readLine().split(" ").map { it.toInt() }
        val s = input2[0]
        val e = input2[1]
        sb.appendLine(dp[s][e])
    }
    print(sb)
}