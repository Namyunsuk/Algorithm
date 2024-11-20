lateinit var vis: Array<Int>
var ans = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    vis = Array(n) { -1 }

    for (j in 0..<n) {
        vis[0] = j
        dfs(1, n)
    }
    print(ans)
}

fun dfs(row: Int, n: Int) {
    if (row == n) {
        ans++
        return
    }

    for (j in 0..<n) {
        if (!check(row, j)) continue
        vis[row] = j
        dfs(row + 1, n)
    }
}

fun check(x: Int, y: Int): Boolean {
    for (i in 0..<x) {
        if (vis[i] == y) return false // 위
        if (x - y == i - vis[i]) return false //좌측 위
        if (x + y == i + vis[i]) return false //우측 위
    }

    return true
}