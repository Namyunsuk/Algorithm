import java.util.*

data class Edge(val node: Int, val weight: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int = this.weight - other.weight
}

const val INF = Int.MAX_VALUE
val graph = Array(801) { mutableListOf<Edge>() }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, e) = readLine().split(" ").map { it.toInt() }
    repeat(e) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u].add(Edge(v, w))
        graph[v].add(Edge(u, w))
    }

    val (v1, v2) = readLine().split(" ").map { it.toInt() }

    val result1: Long = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, n)
    val result2: Long = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, n)

    val result = minOf(result1, result2)
    if (result >= INF) {
        print(-1)
        return
    }

    print(result)
}

fun dijkstra(start: Int, end: Int): Long {
    val d = Array(801) { INF }
    val pq = PriorityQueue<Edge>()

    d[start] = 0
    pq.offer(Edge(start, 0))

    while (!pq.isEmpty()) {
        val cur = pq.poll()
        if (cur.weight != d[cur.node]) continue
        graph[cur.node].forEach {
            val totalWeight = d[cur.node] + it.weight
            if (totalWeight < d[it.node]) {
                d[it.node] = totalWeight
                pq.offer(Edge(it.node, totalWeight))
            }
        }
    }

    return d[end].toLong()
}