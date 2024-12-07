fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) { 0 }
    val input = readLine().split(" ").map { it.toInt() }
    input.forEachIndexed { i, v ->
        arr[i] = v
    }

    var s = 0
    var sum = 0
    var ans = 0
    for (e in 0..<n) {
        sum += arr[e]
        while (sum > m && s < e) {
            sum -= arr[s]
            s++
        }
        if (sum == m) ans++
    }
    print(ans)
}
