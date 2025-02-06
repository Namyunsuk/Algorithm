const val INF = 1e9.toInt()

val graph = Array(251) { Array(251) { INF } }

val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    repeat(n) {
        graph[it + 1][it + 1] = 0
    }

    repeat(m) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        if (w == 0) {
            graph[u][v] = 0
            graph[v][u] = 1
        } else {
            graph[u][v] = 0
            graph[v][u] = 0
        }
    }

    for (v in 1..n) {
        for (s in 1..n) {
            for (e in 1..n) {
                graph[s][e] = minOf(graph[s][e], graph[s][v] + graph[v][e])
            }
        }
    }

    val k = readLine().toInt()
    repeat(k) {
        val (s, e) = readLine().split(" ").map { it.toInt() }
        if (s == e) {
            sb.appendLine(0)
            return@repeat
        }
        sb.appendLine(graph[s][e])
    }

    print(sb)
}
