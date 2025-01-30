import java.util.*

lateinit var graph: Array<MutableList<Int>>
lateinit var degree: Array<Int>
val result = mutableListOf<Int>()
val pq = PriorityQueue<Int>()
val noRelProb = PriorityQueue<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    graph = Array(n + 1) { mutableListOf() }
    degree = Array(n + 1) { 0 }

    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[a].sort()
        degree[b]++
    }

    for (i in 1..n) {
        if (degree[i] == 0) {
            if (graph[i].isEmpty()) noRelProb.offer(i)
            else pq.offer(i)
        }
    }

    while (!pq.isEmpty()) {
        val cur = pq.poll()
        result.add(cur)

        graph[cur].forEach {
            degree[it]--
            if (degree[it] == 0) pq.offer(it)
        }
    }

    for (num in result) {
        while (!noRelProb.isEmpty() && noRelProb.peek() < num) {
            print("${noRelProb.poll()} ")
        }

        print("$num ")
    }
}