import java.util.*

data class Pos(val x: Int, val y: Int, val cnt: Int = 0)

val datas = HashSet<Pair<Int, Int>>()
val q = LinkedList<Pos>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, t) = readLine().split(" ").map { it.toInt() }
    repeat(n) {
        val input = readLine().split(" ").map { it.toInt() }
        datas.add(input[0] to input[1])
    }

    q.offer(Pos(0, 0))
    while (!q.isEmpty()) {
        val cur = q.poll()
        for (i in -2..2) {
            for (j in -2..2) {
                val nx = cur.x + i
                val ny = cur.y + j
                val cnt = cur.cnt
                val pos = Pos(nx, ny, cnt)

                if (nx < 0 || ny < 0 || nx > 1_000_000) continue
                if (nx to ny !in datas) continue
                if (ny >= t) {
                    print(cnt + 1)
                    return
                }
                q.offer(Pos(nx, ny, cnt + 1))
                datas.remove(nx to ny)
            }
        }
    }

    print(-1)
}