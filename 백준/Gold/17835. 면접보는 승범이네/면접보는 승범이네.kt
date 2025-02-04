import java.util.*

class Edge(val node: Int, val weight: Long)

const val INF = Long.MAX_VALUE
lateinit var graph: Array<MutableList<Edge>>
val interviewPlaces = HashSet<Int>()
lateinit var d: Array<Long>

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m, k) = readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { mutableListOf() }
    d = Array(n + 1) { INF }

    repeat(m) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[v].add(Edge(u, w.toLong()))
    }
    interviewPlaces.addAll(readLine().split(" ").map { it.toInt() })

    if (n == k) {
        println(1)
        println(0)
        return
    }

    dijkstra()

    val maxValue = d.drop(1).max()
    println(d.indexOf(maxValue))
    println(maxValue)
}

fun dijkstra() {
    val pq = PriorityQueue<Edge> { e1, e2 -> (e1.weight - e2.weight).toInt() }

    interviewPlaces.forEach {
        d[it] = 0
        pq.offer(Edge(it, 0))
    }

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
}