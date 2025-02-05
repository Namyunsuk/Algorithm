const val INF = 1e9.toInt()


val graph = Array(10) { Array(10) { INF } }
val vis = Array(10) { false }
var result = Int.MAX_VALUE


fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            graph[i][j] = v
        }
    }

    for (v in 0 until n) {
        for (s in 0 until n) {
            for (e in 0 until n) {
                val sum = graph[s][v] + graph[v][e]
                if (sum < graph[s][e]) {
                    graph[s][e] = sum
                }
            }
        }
    }

    dfs(k, n, 0, 1)

    print(result)
}

fun dfs(start: Int, n: Int, sum: Int, cnt: Int) {
    if (cnt == n) {
        result = minOf(result, sum)
    }

    for (i in 0 until n) {
        if (vis[i] || start == i) continue

        vis[start] = true
        dfs(i, n, sum + graph[start][i], cnt + 1)
        vis[start] = false
    }
}