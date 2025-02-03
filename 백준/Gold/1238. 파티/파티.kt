import java.util.*

data class Edge(val node: Int, val weight: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int = this.weight - other.weight
}

val graph = Array(1001) { mutableListOf<Edge>() }
lateinit var times: Array<Int>
const val INF = 1e9.toInt()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m, x) = readLine().split(" ").map { it.toInt() }
    times = Array(n + 1) { 0 }
    repeat(m) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u].add(Edge(v, w))
    }

    var result = Int.MIN_VALUE

    for (i in 1..n) {
        if (i == x) continue
        times[i] = dijkstra(i, x) + dijkstra(x, i)

        if (times[i] > result) {
            result = times[i]
        }
    }

    print(result)
}

fun dijkstra(start: Int, end: Int): Int {
    val d = Array(1001) { INF }
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


    return d[end]
}