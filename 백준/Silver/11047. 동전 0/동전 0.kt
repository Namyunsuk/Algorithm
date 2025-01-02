val coins = Array(11) { 0 }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    var (n, k) = readLine().split(" ").map { it.toInt() }
    repeat(n) { i ->
        coins[i] = readLine().toInt()
    }

    var result = 0
    for (i in n - 1 downTo 0) {
        if (coins[i] > k) continue
        result += (k / coins[i])
        k %= coins[i]
        if (k == 0) break
    }

    print(result)
}