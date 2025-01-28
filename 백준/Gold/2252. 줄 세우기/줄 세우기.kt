import java.util.*

lateinit var graph: Array<HashSet<Int>>
lateinit var degree: Array<Int>
val q = LinkedList<Int>()
val result = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    degree = Array(n + 1) { 0 }
    graph = Array(n + 1) { hashSetOf() }

    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        degree[b]++
    }

    degree.forEachIndexed { i, v ->
        if (i == 0) return@forEachIndexed
        if (v == 0) {
            q.offer(i)
            result.add(i)
        }
    }


    while (!q.isEmpty()) {
        val cur = q.poll()
        graph[cur].forEach {
            degree[it]--
            if (degree[it] == 0) {
                q.offer(it)
                result.add(it)
            }
        }
    }

    result.forEach{print("$it ")}
}