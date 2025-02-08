const val INF = 1e9.toInt()

val graph = Array(401) { Array(401) { INF } }
val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }

    repeat(n) {
        graph[it + 1][it + 1] = 0
    }

    repeat(k) {
        val (u, v) = readLine().split(" ").map { it.toInt() }
        graph[u][v] = 0
    }

    for (v in 1..n) {
        for (s in 1..n) {
            for (e in 1..n) {
                if (graph[s][e] > graph[s][v] + graph[v][e])
                    graph[s][e] = graph[s][v] + graph[v][e]
            }
        }
    }

    val m = readLine().toInt()
    repeat(m) {
        val (u, v) = readLine().split(" ").map { it.toInt() }
        if (graph[u][v] != INF) sb.appendLine(-1)
        else if (graph[v][u] != INF) sb.appendLine(1)
        else sb.appendLine(0)
    }

    print(sb)
}