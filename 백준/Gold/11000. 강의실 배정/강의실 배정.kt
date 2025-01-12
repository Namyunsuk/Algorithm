import java.util.*

data class Class(val start: Int, val end: Int)

val classes = mutableListOf<Class>()
val pq = PriorityQueue<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    repeat(n) {
        val (s, e) = readLine().split(" ").map { it.toInt() }
        classes.add(Class(s, e))
    }

    classes.sortWith(compareBy<Class> {
        it.start
    }.thenBy {
        it.end
    })

    pq.offer(classes[0].end)
    for (i in 1 until classes.size) {
        if (pq.peek() <= classes[i].start) {
            pq.poll()
        }
        pq.offer(classes[i].end)
    }

    print(pq.size)
}