import java.util.*

val pq = PriorityQueue<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    repeat(n) {
        val num = readLine().toInt()
        pq.offer(num)
    }

    if (n == 1) {
        print(0)
        return
    }

    var result = 0
    while (true) {
        val n1 = pq.poll()
        val n2 = pq.poll()

        val total = n1+n2
        result += total
        if (pq.isEmpty()) {
            print(result)
            return
        }
        pq.offer(total)
    }
}