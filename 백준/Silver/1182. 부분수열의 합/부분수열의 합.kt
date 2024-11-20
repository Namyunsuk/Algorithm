lateinit var arr: Array<Int>

var cnt = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, s) = readLine().split(" ").map { it.toInt() }
    val input = readLine().split(" ").map { it.toInt() }
    arr = Array(n) { 0 }

    input.forEachIndexed { i, v ->
        arr[i] = v
    }

    dfs(0, 0, n, s)

    if (s == 0) cnt--  // 공집합 제외(모든 숫자를 더하지 않는 경우)
    print(cnt)
}

fun dfs(i: Int, sum: Int, n: Int, s: Int) {
    if (i == n) {
        if (sum == s) cnt++
        return
    }

    dfs(i + 1, sum + arr[i], n, s)
    dfs(i + 1, sum, n, s)
}