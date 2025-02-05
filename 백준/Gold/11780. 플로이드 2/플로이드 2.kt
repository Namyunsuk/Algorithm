const val INF = 1e9.toLong()

val graph = Array(101) { Array(101) { INF } }
val pre = Array(101) { Array(101) { 0 } }

val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()

    repeat(n) {
        graph[it + 1][it + 1] = 0
    }

    repeat(m) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        if (w < graph[u][v]) {
            graph[u][v] = w.toLong()
            pre[u][v] = v
        }
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
            val value = if (graph[i][j] == INF) 0 else graph[i][j]
            sb.append("$value ")
        }
        sb.appendLine()
    }

    for (i in 1..n) {
        for (j in 1..n) {
            if (i == j || graph[i][j]==INF) {
                sb.appendLine(0)
                continue
            }

            var st = i
            val path = mutableListOf(st)
            while (st != j) {
                path.add(pre[st][j])
                st = pre[st][j]
            }
            sb.append("${path.size} ")
            path.forEach { sb.append("$it ") }
            sb.appendLine()
        }
    }

    print(sb)
}