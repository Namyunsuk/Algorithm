import java.util.*

val q = LinkedList<Pos>()
val water = mutableSetOf<Int>()
val houses = mutableSetOf<Int>()
var ans = 0L

val dx = listOf(-1, 1)

data class Pos(val x: Int, val dist: Int = 0)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val input = readLine().split(" ").map { it.toInt() }
    input.forEach {
        q.offer(Pos(it))
        water.add(it)
    }

    while (true) {
        val cur = q.poll()
        for (i in 0..1) {
            val nx = cur.x + dx[i]

            if (water.contains(nx)) continue
            if (houses.contains(nx)) continue
            ans += cur.dist + 1
            q.offer(Pos(nx, cur.dist + 1))
            houses.add(nx)
            if (houses.size == k) {
                print(ans)
                return
            }
        }
    }
}