import java.util.*

lateinit var graph: Array<HashSet<Int>>
lateinit var degree: Array<Int>
val q = LinkedList<Int>()
val result = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { hashSetOf() }
    degree = Array(n + 1) { 0 }

    repeat(m) {
        val input = readLine().split(" ").map { it.toInt() }
        for (i in 1 until input.size - 1) {
            if (graph[input[i]].contains(input[i + 1])) continue
            graph[input[i]].add(input[i + 1])
            degree[input[i + 1]]++
        }
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

    if (result.size != n) {
        print(0)
        return
    }

    result.forEach { println(it) }
}