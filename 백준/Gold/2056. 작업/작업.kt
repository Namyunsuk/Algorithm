import java.util.*

lateinit var graph: Array<MutableList<Int>>
lateinit var degree: Array<Int>
lateinit var times: Array<Int>
lateinit var result: Array<Int>

val q = LinkedList<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    graph = Array(n + 1) { mutableListOf() }
    degree = Array(n + 1) { 0 }
    times = Array(n + 1) { 0 }
    result = Array(n + 1) { 0 }

    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        times[i + 1] = input[0]
        if (input[1] != 0) {
            for (j in 2..input.size - 1) {
                graph[input[j]].add(i+1)
                degree[i+1]++
            }
        }
    }

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
            if (degree[it] == 0) q.offer(it)
        }
    }


    print(result.max())
}