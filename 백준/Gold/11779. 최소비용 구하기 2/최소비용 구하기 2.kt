import java.util.*

data class Edge(val node: Int, val weight: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int = this.weight - other.weight
}

const val INF = 1e9.toInt()

val graph = Array(1001) { mutableListOf<Edge>() }
val d = Array(1001) { INF }
val pre = Array(1001) { -1 }
val pq = PriorityQueue<Edge>()
val result = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()

    repeat(m) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u].add(Edge(v, w))
    }

    val (start, end) = readLine().split(" ").map { it.toInt() }
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
                pre[it.node] = cur.node
            }
        }
    }

    var idx = end
    while (true) {
        if (idx == start) {
            result.add(start)
            break
        }
        result.add(idx)
        idx = pre[idx]
    }
    result.reverse()

    println(d[end])
    println(result.size)
    result.forEach { print("$it ") }
}