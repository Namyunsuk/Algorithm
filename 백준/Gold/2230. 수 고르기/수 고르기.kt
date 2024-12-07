import kotlin.math.*

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) { 0 }

    repeat(n) {
        arr[it] = readLine().toInt()
    }

    var s = 0
    var ans = Int.MAX_VALUE

    arr.sort()

    for (e in 0..<n) {
        while (arr[e] - arr[s] >= m && s < e) {
            ans = min(ans, arr[e] - arr[s])
            s++
        }
    }
    print(ans)
}
