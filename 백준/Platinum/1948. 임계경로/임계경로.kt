import java.util.*

data class Edge(val node: Int, val weight: Int)

lateinit var graph: Array<MutableList<Edge>>
lateinit var degree: Array<Int>

lateinit var revGraph: Array<MutableList<Edge>>
lateinit var revDegree: Array<Int>

lateinit var history: Array<Int>
val q = LinkedList<Int>()

val resultRoads = HashSet<Int>()
var cnt = 0


fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()

    graph = Array(n + 1) { mutableListOf() }
    degree = Array(n + 1) { 0 }

    revGraph = Array(n + 1) { mutableListOf() }
    revDegree = Array(n + 1) { 0 }

    history = Array(n + 1) { 0 }

    repeat(m) {
        val (s, e, w) = readLine().split(" ").map { it.toInt() }
        graph[s].add(Edge(e, w))
        degree[e]++

        revGraph[e].add(Edge(s, w))
        revDegree[s]++
    }

    val (start, dest) = readLine().split(" ").map { it.toInt() }

    q.offer(start)
    while (!q.isEmpty()) {
        val cur = q.poll()
        graph[cur].forEach {
            val node = it.node
            val weight = it.weight

            history[node] = maxOf(history[node], history[cur] + weight)
            degree[node]--
            if (degree[node] == 0) q.offer(node)
        }
    }

    resultRoads.add(dest)
    q.offer(dest)
    while (!q.isEmpty()) {
        val cur = q.poll()
        revGraph[cur].forEach {
            val node = it.node
            val weight = it.weight

            revDegree[node]--
            if (resultRoads.contains(cur) && (history[cur] - history[node] == weight)) {
                resultRoads.add(node)
                cnt++
            }
            if (revDegree[node] == 0) q.offer(node)
        }
    }

    println(history[dest])
    println(cnt)
}
