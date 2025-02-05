const val INF = 1e9.toInt()

val graph = Array(201) { Array(201) { INF } }
val friends = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    repeat(m) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u][v] = minOf(graph[u][v], w)
    }
    val fNum = readLine()
    friends.addAll(readLine().split(" ").map { it.toInt() })

    repeat(n) {
        graph[it + 1][it + 1] = 0
    }

    for (v in 1..n) {
        for (s in 1..n) {
            for (e in 1..n) {
                graph[s][e] = minOf(graph[s][e], graph[s][v] + graph[v][e])
            }
        }
    }

    var result = mutableListOf<Int>()
    var min = Int.MAX_VALUE

    for (city in 1..n) {
        var max = Int.MIN_VALUE
        for (friend in friends) {
            max = maxOf(max, graph[friend][city] + graph[city][friend])
        }
        if (max == min) {
            result.add(city)
        } else if (max < min) {
            min = max
            result.clear()
            result.add(city)
        }
    }

    result.forEach { print("${it} ") }
}