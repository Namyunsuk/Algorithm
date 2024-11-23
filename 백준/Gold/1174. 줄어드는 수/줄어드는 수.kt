import java.util.*

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val q = LinkedList<Long>()
    for (i in 0..9) {
        q.offer(i.toLong())
    }

    if (n <= 10) {
        print(n - 1)
        return
    }

    var count = 10

    while (!q.isEmpty()) {
        var cur = q.poll()
        for (i in 0..<cur%10) {
            val num = cur * 10 + i
            count++
            if (count == n) {
                print(num)
                return
            }
            q.offer(num)
        }
    }

    print(-1)
}