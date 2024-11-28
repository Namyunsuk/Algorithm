import kotlin.math.*

lateinit var arr: Array<Array<Int>>
lateinit var vis: Array<Array<Boolean>>

var maxSum = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = Array(n) { Array(m) { 0 } }
    vis = Array(n) { Array(m) { false } }
    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            arr[i][j] = v
        }
    }

    dfs(0, 0, n, m)

    print(maxSum)
}

fun dfs(idx: Int, sum: Int, n: Int, m: Int) {
    maxSum = max(maxSum, sum)

    for (k in idx..<n * m) {
        val i = k / m
        val j = k % m

        if (vis[i][j]) continue
        if (isAvailable(i, j + 1, i - 1, j, n, m)) {
            vis[i][j] = true
            vis[i][j + 1] = true
            vis[i - 1][j] = true

            val strength = arr[i][j] * 2 + arr[i][j + 1] + arr[i - 1][j]

            dfs(k + 1, sum + strength, n, m)

            vis[i][j] = false
            vis[i][j + 1] = false
            vis[i - 1][j] = false
        }
        if (isAvailable(i, j - 1, i - 1, j, n, m)) {
            vis[i][j] = true
            vis[i][j - 1] = true
            vis[i - 1][j] = true

            val strength = arr[i][j] * 2 + arr[i][j - 1] + arr[i - 1][j]

            dfs(k + 1, sum + strength, n, m)

            vis[i][j] = false
            vis[i][j - 1] = false
            vis[i - 1][j] = false
        }
        if (isAvailable(i, j + 1, i + 1, j, n, m)) {
            vis[i][j] = true
            vis[i][j + 1] = true
            vis[i + 1][j] = true

            val strength = arr[i][j] * 2 + arr[i][j + 1] + arr[i + 1][j]

            dfs(k + 1, sum + strength, n, m)

            vis[i][j] = false
            vis[i][j + 1] = false
            vis[i + 1][j] = false
        }
        if (isAvailable(i, j - 1, i + 1, j, n, m)) {
            vis[i][j] = true
            vis[i][j - 1] = true
            vis[i + 1][j] = true

            val strength = arr[i][j] * 2 + arr[i][j - 1] + arr[i + 1][j]

            dfs(k + 1, sum + strength, n, m)

            vis[i][j] = false
            vis[i][j - 1] = false
            vis[i + 1][j] = false
        }
    }
}

fun isAvailable(x1: Int, y1: Int, x2: Int, y2: Int, n: Int, m: Int): Boolean {
    if (x1 < 0 || x1 >= n || y1 < 0 || y1 >= m) return false
    if (x2 < 0 || x2 >= n || y2 < 0 || y2 >= m) return false
    if (!vis[x1][y1] && !vis[x2][y2]) return true
    return false
}