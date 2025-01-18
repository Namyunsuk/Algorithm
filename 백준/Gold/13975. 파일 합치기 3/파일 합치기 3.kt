import java.util.*

val pq = PriorityQueue<Long>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    repeat(t) {
        val n = readLine().toInt()
        val input = readLine().split(" ").map { it.toInt() }
        pq.clear()
        input.forEach { pq.offer(it.toLong()) }

        var result = 0L
        while (true) {
            val n1 = pq.poll()
            val n2 = pq.poll()
            val sum = n1 + n2
            result+=sum
            if(pq.isEmpty()){
                println(result)
                break
            }
            pq.offer(sum)
        }
    }
}