import java.util.*

data class Node(val x: Int, val dis: Int = 0)

lateinit var graph: Array<MutableList<Int>>

lateinit var vis: Array<Boolean>
lateinit var recordVis: Array<Int>

var ans = 0

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { mutableListOf() }
    vis = Array(n + 1) { false }
    recordVis = Array(n + 1) { 0 }

    repeat(m) {
        val (s1, s2) = readLine().split(" ").map { it.toInt() }
        graph[s1].add(s2)
        graph[s2].add(s1)
    }

    repeat(n) {
        graph[it + 1].sortBy { it }
    }

    val (s, e) = readLine().split(" ").map { it.toInt() }

    ans += bfs(s, e)

    for (i in 1..n) {
        vis[i] = false
    }

    var tmp = recordVis[e]
    while (true) {
        if (tmp == s) break
        vis[tmp] = true
        tmp = recordVis[tmp]
    }

    ans += bfs(e, s)

    print(ans+2)
}

fun bfs(start: Int, des: Int): Int {
    val q = LinkedList<Node>()
    q.offer(Node(start))
    vis[start] = true

    while (true) {
        val cur = q.poll()
        for (node in graph[cur.x]) {
            if (vis[node]) continue
            if (node == des) {
                recordVis[node] = cur.x
                return cur.dis
            }
            q.offer(Node(node, cur.dis + 1))
            recordVis[node] = cur.x
            vis[node] = true
        }
    }
}