const val INF = 1e9.toInt()

val graph = Array(201) { Array(201) { INF } }
val pre = Array(201) { Array(201) { INF } }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    repeat(n) {
        graph[it + 1][it + 1] = 0
    }

    repeat(m) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u][v] = minOf(graph[u][v], w)
        graph[v][u] = minOf(graph[v][u], w)

        pre[u][v] = v
        pre[v][u] = u
    }

    for (v in 1..n) {
        for (s in 1..n) {
            for (e in 1..n) {
                if (graph[s][v] + graph[v][e] < graph[s][e]) {
                    graph[s][e] = graph[s][v] + graph[v][e]
                    pre[s][e] = pre[s][v]
                }
            }
        }
    }

    for (i in 1..n) {
        for (j in 1..n) {
            if (pre[i][j] == INF) {
                print("- ")
                continue
            }
            print("${pre[i][j]} ")
        }
        println()
    }
}
