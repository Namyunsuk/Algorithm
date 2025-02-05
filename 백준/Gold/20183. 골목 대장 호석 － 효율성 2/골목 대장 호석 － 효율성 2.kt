import java.util.*

class Edge(val node: Int, val weight: Long)

val graph = Array(100001) { mutableListOf<Edge>() }
const val INF = Long.MAX_VALUE

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m, start, end, c) = readLine().split(" ").map { it.toLong() }

    repeat(m.toInt()) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u].add(Edge(v, w.toLong()))
        graph[v].add(Edge(u, w.toLong()))
    }


    var s = 1L
    var e = c

    while (s < e) {
        val mid = (s + e) / 2
        if (dijkstra(start.toInt(), end.toInt(), c, mid)) e = mid
        else s = mid + 1
    }

    if (dijkstra(start.toInt(), end.toInt(), c, s)) print(s)
    else print(-1)
}

fun dijkstra(start: Int, end: Int, c: Long, max: Long): Boolean {
    val d = Array(100001) { INF }
    val pq = PriorityQueue<Edge> { e1, e2 -> (e1.weight - e2.weight).toInt() }

    d[start] = 0
    pq.offer(Edge(start, 0L))

    while (!pq.isEmpty()) {
        val cur = pq.poll()
        if (cur.weight != d[cur.node]) continue

        graph[cur.node].forEach {
            if (it.weight > max) return@forEach
            val totalWeight = d[cur.node] + it.weight
            if (totalWeight < d[it.node]) {
                d[it.node] = totalWeight
                pq.offer(Edge(it.node, totalWeight))
            }
        }
    }

    return d[end] <= c
}