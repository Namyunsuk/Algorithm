import kotlin.math.*
import java.util.*

const val INF = 1e9

class Node(val x: Double, val y: Double)

class Edge(val node: Int, val weight: Double)

val graph = Array(1001) { mutableListOf<Edge>() }
val d = Array(1001) { INF }
val nodes = HashMap<Int, Node>()
val pq = PriorityQueue<Edge> { e1, e2 -> (e1.weight - e2.weight).toInt() }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, w) = readLine().split(" ").map { it.toInt() }
    val m = readLine().toDouble()

    repeat(n) { i ->
        val (x, y) = readLine().split(" ").map { it.toDouble() }
        nodes[i + 1] = Node(x, y)
    }

    repeat(w) {
        val (u, v) = readLine().split(" ").map { it.toInt() }
        graph[u].add(Edge(v, 0.0))
        graph[v].add(Edge(u, 0.0))
    }

    for (i in 1..n) {
        for (j in i + 1..n) {
            if (i == j ) continue
            val node1 = nodes[i]!!
            val node2 = nodes[j]!!
            val len = calLen(node1, node2)
            graph[i].add(Edge(j, len))
            graph[j].add(Edge(i, len))
        }
    }


    d[1] = 0.0
    pq.offer(Edge(1, 0.0))

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

    print((d[n] * 1000).toInt())
}

fun calLen(node1: Node, node2: Node): Double {
    val x1 = (node1.x - node2.x).pow(2)
    val y1 = (node1.y - node2.y).pow(2)
    return sqrt(x1 + y1)
}