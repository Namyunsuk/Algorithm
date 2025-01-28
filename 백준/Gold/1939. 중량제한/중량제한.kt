import java.util.*

//val bridges = HashMap<Int, MutableList<Edge>>()
val bridges = Array(100001) { mutableListOf<Edge>() }
lateinit var vis: Array<Boolean>

data class Edge(val node: Int, val weight: Int)

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    var maxWeight = Int.MIN_VALUE
    repeat(m) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        maxWeight = maxOf(maxWeight, c)
        bridges[a].add(Edge(b, c))
        bridges[b].add(Edge(a, c))

//        val curEdge1 = bridges[a]!!.find { it.node == b }
//        if (curEdge1 != null) {
//            if (curEdge1.weight < c) {
//                bridges[a]!!.remove(curEdge1)
//                bridges[a]!!.add(Edge(b, c))
//            }
//        } else {
//            bridges[a]!!.add(Edge(b, c))
//        }
//
//        val curEdge2 = bridges[b]!!.find { it.node == a }
//        if (curEdge2 != null) {
//            if (curEdge2.weight < c) {
//                bridges[b]!!.remove(curEdge2)
//                bridges[b]!!.add(Edge(a, c))
//            }
//        } else {
//            bridges[b]!!.add(Edge(a, c))
//        }
    }
    val (start, des) = readLine().split(" ").map { it.toInt() }

    var s = 1
    var e = maxWeight

    while (s < e) {
        val mid = (s + e + 1) / 2
        if (isValid(bridges, start, des, mid)) s = mid
        else e = mid - 1
    }

    print(s)
}

fun isValid(arr: Array<MutableList<Edge>>, s: Int, des: Int, weight: Int): Boolean {
    vis = Array(100001) { false }
    val q = LinkedList<Int>()
    q.offer(s)
    vis[s] = true

    while (!q.isEmpty()) {
        val cur = q.poll()
        if (arr[cur].isEmpty()) return false
        arr[cur].forEach { edge ->
            if (vis[edge.node]) return@forEach
            if (edge.weight < weight) return@forEach
            if (edge.node == des) {
                return true
            }
            q.offer(edge.node)
            vis[edge.node] = true
        }
    }

    return false
}