const val INF = 1e9.toInt()

val graph = Array(501) { Array(501) { INF } }
val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            graph[i + 1][j + 1] = v
        }
    }

    for (v in 1..n) {
        for (s in 1..n) {
            for (e in 1..n) {
                graph[s][e] = minOf(graph[s][e], graph[s][v] + graph[v][e])
            }
        }
    }

    repeat(m) {
        val (u, v, t) = readLine().split(" ").map { it.toInt() }
        if (graph[u][v] <= t) sb.appendLine("Enjoy other party")
        else sb.appendLine("Stay here")
    }

    print(sb)
}