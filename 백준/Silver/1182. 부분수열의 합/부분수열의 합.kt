lateinit var arr: Array<Int>
lateinit var vis: Array<Boolean>

var cnt = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, s) = readLine().split(" ").map { it.toInt() }
    val input = readLine().split(" ").map { it.toInt() }
    arr = Array(n) { 0 }
    vis = Array(n) { false }

    input.forEachIndexed { i, v ->
        arr[i] = v
    }

    dfs(0, 0, 0, n, s)

    print(cnt)
}

fun dfs(index: Int, sum: Int, size: Int, n: Int, s: Int) {
    if (sum == s && size != 0) {
        cnt++
    }
    if (size == n) return

    for (i in index..<n) {
        if (vis[i]) continue
        vis[i] = true
        dfs(i, sum + arr[i], size + 1, n, s)
        vis[i] = false
    }
}