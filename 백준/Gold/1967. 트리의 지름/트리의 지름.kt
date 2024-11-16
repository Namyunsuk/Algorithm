import kotlin.math.*

data class Node(val num: Int, val edge: Int)

val tree = Array(10002) { mutableListOf<Node>() }
val vis = Array(10002) { false }

var maxValue = 0

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    repeat(n - 1) {
        val (node1, node2, edge) = readLine().split(" ").map { it.toInt() }
        tree[node1].add(Node(node2, edge))
        tree[node2].add(Node(node1, edge))
    }

    for (i in 1..n) {
        vis[i] = true
        dfs(i, 0)
        vis[i] = false
    }

    print(maxValue)
}

fun dfs(start: Int, sum: Int) {
    maxValue = max(maxValue, sum)

    for (node in tree[start]) {
        if (vis[node.num]) continue
        vis[node.num] = true
        dfs(node.num, sum + node.edge)
        vis[node.num] = false
    }
}