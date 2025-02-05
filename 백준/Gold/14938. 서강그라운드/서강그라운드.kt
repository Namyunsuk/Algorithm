const val INF = 1e9.toInt()

val items = Array(101) { 0 }
val graph = Array(101) { Array(101) { INF } }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m, r) = readLine().split(" ").map { it.toInt() }
    val input = readLine().split(" ").map { it.toInt() }
    input.forEachIndexed { i, v ->
        items[i + 1] = v
    }

    repeat(n) {
        graph[it + 1][it + 1] = 0
    }

    repeat(r) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u][v] = minOf(graph[u][v], w)
        graph[v][u] = minOf(graph[v][u], w)
    }

    for (v in 1..n) {
        for (s in 1..n) {
            for (e in 1..n) {
                graph[s][e] = minOf(graph[s][e], graph[s][v] + graph[v][e])
            }
        }
    }

    var result = 0

    for (s in 1..n) {
        var sum = items[s]
        for (e in 1..n) {
            if (s == e) continue
            if (graph[s][e] <= m) {
                sum += items[e]
            }
        }
        result = maxOf(result, sum)
    }

    print(result)
}