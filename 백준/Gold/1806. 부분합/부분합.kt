import kotlin.math.*

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, s) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) { 0 }
    val input = readLine().split(" ").map { it.toInt() }

    input.forEachIndexed { i, v ->
        arr[i] = v
    }

    var start = 0
    var ans = Int.MAX_VALUE
    var sum = 0
    for (end in 0..<n) {
        sum += arr[end]
        while (sum >= s) {
            ans = min(ans, end - start + 1)
            sum -= arr[start]
            if (start == end) break
            start++
        }
    }

    if(ans == Int.MAX_VALUE){
        print(0)
        return
    }
    print(ans)
}
