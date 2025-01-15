import java.util.*

data class Conf(val start: Int, val end: Int)

val pq = PriorityQueue<Int>()

val datas = mutableListOf<Conf>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    repeat(n) {
        val (s, e) = readLine().split(" ").map { it.toInt() }
        datas.add(Conf(s, e))
    }

    datas.sortWith(compareBy<Conf> {
        it.start
    }.thenBy {
        it.end
    })

    pq.offer(datas[0].end)

    for (i in 1 until n) {
        if (pq.peek() <= datas[i].start) {
            pq.poll()

        }
        pq.offer(datas[i].end)
    }

    print(pq.size)
}