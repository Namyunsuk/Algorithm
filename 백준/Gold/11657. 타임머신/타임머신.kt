const val INF = 1e9.toInt()

val graph = Array(501) { Array(501) { INF } }
val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    repeat(n) {
        graph[it + 1][it + 1] = 0
    }

    repeat(m) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u][v] = minOf(graph[u][v], w)
    }

    for (v in 1..n) {
        for (s in 1..n) {
            for (e in 1..n) {
                if(graph[s][v]==INF || graph[v][e]==INF) continue
                if (graph[s][v] + graph[v][e] < graph[s][e]) {
                    graph[s][e] = graph[s][v] + graph[v][e]
                }
            }
        }
    }

    for(i in 1..n){
        if (graph[1][i]!=INF && graph[i][i]<0){ // 음수 사이클이 있으면, i->i로 돌아오늘 값이 음수가 된다.
            print(-1)
            return
        }
    }

    for (e in 2..n) {
        if(graph[1][e]==INF) sb.appendLine(-1)
        else sb.appendLine(graph[1][e])
    }

    print(sb)
}
