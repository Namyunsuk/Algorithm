import java.util.*

const val INF = 1e9.toInt()

data class Edge(val node: Int = 0, val weight: Int = INF) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int = this.weight - other.weight
}

lateinit var graph: Array<MutableList<Edge>>
lateinit var d: Array<Int>
val pq = PriorityQueue<Edge>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (V, E) = readLine().split(" ").map { it.toInt() }
    graph = Array(V + 1) { mutableListOf() }
    d = Array(V + 1) { INF }

    val start = readLine().toInt()
    d[start] = 0
    pq.offer(Edge(start, 0))

    repeat(E) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u].add(Edge(v, w))
    }

    while (!pq.isEmpty()) {
        val cur = pq.poll()
        if (d[cur.node] != cur.weight) continue
        graph[cur.node].forEach {
            val totalWeight = d[cur.node] + it.weight
            if (totalWeight < d[it.node]) {
                d[it.node] = totalWeight
                pq.offer(Edge(it.node, totalWeight))
            }
        }
    }

    for (i in 1..V) {
        if (d[i] == INF) println("INF")
        else println(d[i])
    }

}