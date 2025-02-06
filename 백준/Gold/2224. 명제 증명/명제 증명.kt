const val INF = 1e9.toInt()

val graph = Array(58) { Array(58) { INF } }
val range = mutableListOf<Char>()
val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    for (c in 'A'..'Z') {
        graph[c.toIdx()][c.toIdx()] = 0
        range.add(c)
    }

    for (c in 'a'..'z') {
        graph[c.toIdx()][c.toIdx()] = 0
        range.add(c)
    }

    repeat(n) {
        val (p, q) = readLine().split(" => ").map { it[0].toIdx() }
        graph[p][q] = 0
    }

    for (v in range) {
        for (s in range) {
            for (e in range) {
                val v1 = v.toIdx()
                val s1 = s.toIdx()
                val e1 = e.toIdx()
                graph[s1][e1] = minOf(graph[s1][e1], graph[s1][v1] + graph[v1][e1])
            }
        }
    }

    var cnt = 0
    for (s in range) {
        for (e in range) {
            if (s == e || graph[s.toIdx()][e.toIdx()] == INF) continue
            cnt++
            sb.appendLine("$s => $e")
        }
    }
    println(cnt)
    print(sb)
}

fun Char.toIdx() = this - 'A'