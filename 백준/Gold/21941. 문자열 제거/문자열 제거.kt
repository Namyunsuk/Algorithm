val dp = Array(1002) { 0 }
val v = HashMap<String, Int>()
val words = mutableListOf<String>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val s = " " + readLine()
    val m = readLine().toInt()

    repeat(m) {
        val (a, x) = readLine().split(" ")
        v[a] = x.toInt()
        words.add(a)
    }

    for (i in 1..<s.length) {
        words.forEach { word ->
            val l = word.length
            if (i < l) {
                dp[i] = maxOf(dp[i - 1] + 1, dp[i])
            } else {
                val formattedStr = s.substring(i - l + 1, i + 1)
                val value = if (formattedStr == word) v[word]!! else 0
                dp[i] = maxOf(dp[i - 1] + 1, dp[i - l] + value, dp[i])
            }
        }
    }

    print(dp[s.length - 1])
}