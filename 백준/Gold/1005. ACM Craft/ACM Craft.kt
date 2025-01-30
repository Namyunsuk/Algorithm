import java.util.*

lateinit var graph: Array<MutableList<Int>>
lateinit var degree: Array<Int>
lateinit var times: Array<Int>
lateinit var result: Array<Int>

val q = LinkedList<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()

    repeat(t) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        graph = Array(n + 1) { mutableListOf() }
        degree = Array(n + 1) { 0 }
        times = Array(n + 1) { 0 }
        result = Array(n + 1) { 0 }
        q.clear()

        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { i, v ->
            times[i + 1] = v
        }

        repeat(k) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            graph[x].add(y)
            degree[y]++
        }

        val dest = readLine().toInt()

        for (i in 1..n) {
            if (degree[i] == 0) {
                result[i] = times[i]
                q.offer(i)
            }
        }

        while (!q.isEmpty()) {
            val cur = q.poll()
            graph[cur].forEach {
                result[it] = maxOf(result[it], result[cur] + times[it])
                degree[it]--
                if (degree[it] == 0) {
                    q.offer(it)
                }
            }
        }

        println(result[dest])
    }
}