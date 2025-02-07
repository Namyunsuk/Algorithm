import java.util.*

class Edge(val node: Int, val weight: Long)

const val INF = 1e9.toLong()
val graph = Array(100001) { mutableListOf<Edge>() }
val friends = mutableListOf<Int>()

var result = 0
var maxV = Long.MIN_VALUE

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    friends.addAll(readLine().split(" ").map { it.toInt() })
    val m = readLine().toInt()
    repeat(m) {
        val (u, v, w) = readLine().split(" ").map { it.toInt() }
        graph[u].add(Edge(v, w.toLong()))
        graph[v].add(Edge(u, w.toLong()))
    }

    val d1 = dijkstra(friends[0])
    val d2 = dijkstra(friends[1])
    val d3 = dijkstra(friends[2])

    for (i in 1..n) {
        val min = minOf(d1[i], d2[i], d3[i])
        if(maxV < min){
            maxV = min
            result =i
        }
    }

    print(result)
}

fun dijkstra(s: Int): Array<Long> {
    val d = Array(100001) { INF }
    val pq = PriorityQueue<Edge> { e1, e2 -> (e1.weight - e2.weight).toInt() }

    d[s] = 0
    pq.offer(Edge(s, 0L))

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

    return d
}