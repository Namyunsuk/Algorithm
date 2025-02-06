const val INF = 1e9.toInt()

val graph = Array(401) { Array(401) { INF } }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }


    repeat(m) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u][v] = w
    }

    repeat(n) {
        graph[it + 1][it + 1] = 0
    }
    
    for(v in 1..n){
        for(s in 1..n){
            for(e in 1..n){
                graph[s][e] = minOf(graph[s][e] , graph[s][v] + graph[v][e])
            }
        }
    }

    var result = Int.MAX_VALUE

    for (s in 1..n) {
        for (e in s + 1..n) {
            if(graph[s][e]==INF||graph[e][s]==INF) continue
            result = minOf(result,graph[s][e] + graph[e][s])
        }
    }

    if(result==Int.MAX_VALUE) print(-1)
    else print(result)
}
