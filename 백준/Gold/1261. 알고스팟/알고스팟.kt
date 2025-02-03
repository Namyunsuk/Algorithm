import java.util.*

data class Node(val x: Int, val y: Int)
data class Edge(val node: Node, val weight: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int = this.weight - other.weight
}

const val INF = Int.MAX_VALUE
val graph = Array(101) { Array(101) { -1 } }
val d = Array(101) { Array(101) { INF } }
val pq = PriorityQueue<Edge>()

val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, -1, 1)

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (m, n) = readLine().split(" ").map { it.toInt() }
    repeat(n) { i ->
        val input = readLine().split("").filter { it.isNotBlank() }.map { it.toInt() }
        input.forEachIndexed { j,v ->
            graph[i + 1][j + 1] = v
        }
    }

    d[1][1] = 0
    pq.offer(Edge(Node(1, 1), 0))
    
    while (!pq.isEmpty()) {
        val cur = pq.poll()
        val curNode = cur.node
        if (cur.weight != d[curNode.x][curNode.y]) continue

        for (i in 0..3) {
            val nx = curNode.x + dx[i]
            val ny = curNode.y + dy[i]

            if (nx <= 0 || nx > n || ny <= 0 || ny > m) continue
            val totalWeight = d[curNode.x][curNode.y] + graph[nx][ny]
            if (totalWeight < d[nx][ny]) {
                d[nx][ny] = totalWeight
                pq.offer(Edge(Node(nx, ny), totalWeight))
            }
        }
    }

    print(d[n][m])
}