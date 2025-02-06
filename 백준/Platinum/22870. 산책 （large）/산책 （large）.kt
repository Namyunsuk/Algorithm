import java.util.*

const val INF = 1e9.toLong() + 10

class Edge(val node: Int, val weight: Long)

val graph = Array(200001) { mutableListOf<Edge>() }
val pq = PriorityQueue(compareBy<Edge> { it.weight })
val vis = HashSet<Int>()
var result = 0L

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    repeat(m) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u].add(Edge(v, w.toLong()))
        graph[v].add(Edge(u, w.toLong()))
    }

    val (s, e) = readLine().split(" ").map { it.toInt() }

    val dStoE = dijkstra(s)
    val dEtoS = dijkstra(e)

    result += dStoE[e]

    var pre = s
    var cur = s
    while (cur != e) {
        var minNode = Int.MAX_VALUE
        graph[cur].forEach {
            if (it.node == pre) return@forEach
            if (dStoE[cur] + it.weight + dEtoS[it.node] == dStoE[e]) {
                minNode = minOf(minNode, it.node)
            }
        }

        if (minNode != e) vis.add(minNode)
        pre = s
        cur = minNode
    }

    result += dijkstra(e)[s]

    println(result)

}

fun dijkstra(s: Int): Array<Long> {
    val d = Array(200001) { INF }
    val pre = Array(200001) { 0 }
    d[s] = 0
    pq.offer(Edge(s, 0))

    while (!pq.isEmpty()) {
        val cur = pq.poll()
        if (d[cur.node] != cur.weight) continue
        graph[cur.node].forEach {
            if (vis.contains(it.node)) return@forEach
            val totalWeight = (d[cur.node] + it.weight)
            if (totalWeight < d[it.node]) {
                d[it.node] = totalWeight
                pq.offer(Edge(it.node, totalWeight))
                pre[it.node] = cur.node
            }
        }
    }

    return d
}