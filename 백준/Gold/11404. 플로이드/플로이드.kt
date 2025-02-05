const val INF = 1e9.toLong()

val graph = Array(101) { Array(101) { INF } }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()

    repeat(n) {
        graph[it + 1][it + 1] = 0
    }

    repeat(m) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u][v] = minOf(graph[u][v], w.toLong())
    }

    for (v in 1..n) {
        for (s in 1..n) {
            for (e in 1..n) {
                graph[s][e] = minOf(graph[s][e], graph[s][v] + graph[v][e])
            }
        }
    }

    for (s in 1..n) {
        for (e in 1..n) {
            if(graph[s][e]==INF) print("0 ")
            else print("${graph[s][e]} ")
        }
        println()
    }
}