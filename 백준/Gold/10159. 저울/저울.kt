const val INF = 1e9.toInt()

val graph = Array(101) { Array(101) { INF } }

val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()

    repeat(n) {
        graph[it + 1][it + 1] = 0
    }

    repeat(m) {
        val (u, v) = readLine().split(" ").map { it.toInt() }
        graph[u][v] = 0
    }

    for (v in 1..n) {
        for (s in 1..n) {
            for (e in 1..n) {
                graph[s][e] = minOf(graph[s][e], graph[s][v] + graph[v][e])
            }
        }
    }

    for (s in 1..n) {
        var cnt = 0
        for (e in 1..n) {
            if (s == e) continue
            if (graph[s][e] == INF) cnt++
        }

        for (s2 in 1..n) {
            if (s == s2) continue
            if (graph[s2][s] != INF) cnt--
        }
        sb.appendLine(cnt)
    }
    print(sb)
}