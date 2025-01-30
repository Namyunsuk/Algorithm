import java.util.*

val graph = HashMap<String, HashSet<String>>()
val degree = HashMap<String, Int>()
val q = LinkedList<String>()
val peoples = mutableListOf<String>()
val result = HashMap<String, MutableList<String>>()


fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    peoples.addAll(readLine().split(" "))
    peoples.sort()

    peoples.forEach {
        result[it] = mutableListOf()
    }

    val m = readLine().toInt()
    repeat(m) {
        val (x, y) = readLine().split(" ")
        if (graph[y] == null) graph[y] = hashSetOf()
        graph[y]!!.add(x)
        degree[x] = degree.getOrDefault(x, 0) + 1
    }

    val ancestor = PriorityQueue<String>()

    peoples.forEach { name ->
        if (degree[name] == null) {
            q.add(name)
            ancestor.add(name)
        }
    }

    println(ancestor.size)
    while (!ancestor.isEmpty()) {
        print("${ancestor.poll()} ")
    }
    println()

    while (!q.isEmpty()) {
        val cur = q.poll()
        if (graph[cur] == null) continue
        graph[cur]!!.forEach {
            if (degree[it] == null) return@forEach
            degree[it] = degree[it]!! - 1
            if (degree[it] == 0) {
                q.offer(it)
                result[cur]!!.add(it)
            }
        }
    }

    for (people in peoples) {
        val res = result[people]!!
        print("$people ${res.size} ")
        res.forEach { print("$it ") }
        println()
    }
}