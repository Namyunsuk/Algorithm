import java.util.*

lateinit var graph: Array<MutableList<Int>>
lateinit var degree: Array<Int>
lateinit var result: Array<Int>

val q = LinkedList<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { mutableListOf() }
    degree = Array(n + 1) { 0 }
    result = Array(n + 1) { 0 }

    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        degree[b]++
    }

    for (i in 1..n) {
        if (degree[i] == 0) {
            result[i] = 1
            q.offer(i)
        }
    }

    while (!q.isEmpty()) {
        val cur = q.poll()
        graph[cur].forEach {
            degree[it]--
            if (degree[it] == 0) {
                result[it] = result[cur] + 1
                q.offer(it)
            }
        }
    }

    for(i in 1 .. n){
        print("${result[i]} ")
    }
}