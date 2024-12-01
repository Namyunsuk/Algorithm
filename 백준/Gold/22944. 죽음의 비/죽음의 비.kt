import java.util.*

data class Pos(val x: Int, val y: Int, val hp: Int, val umbrella: Int = 0, val dis: Int = 0)

lateinit var arr: Array<Array<String>>
lateinit var vis: Array<Array<Int>>
val q = LinkedList<Pos>()

val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, -1, 1)

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, h, d) = readLine().split(" ").map { it.toInt() }
    arr = Array(n) { Array(n) { "" } }
    vis = Array(n) { Array(n) { -1 } }
    repeat(n) { i ->
        val input = readLine().split("").filter { it.isNotBlank() }
        input.forEachIndexed { j, v ->
            arr[i][j] = v
            if (v == "S") {
                vis[i][j] = h
                q.offer(Pos(i, j, h))
                arr[i][j] = "."
            }
        }
    }

    while (!q.isEmpty()) {
        val cur = q.poll()
        for (i in 0..<4) {
            val nx = cur.x + dx[i]
            val ny = cur.y + dy[i]
            var hp = cur.hp
            var umbHp = cur.umbrella

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue
            if (vis[nx][ny] >= hp + umbHp) continue
            if (arr[nx][ny] == "E") {
                print(cur.dis + 1)
                return
            } else if (arr[nx][ny] == "U") {
                arr[nx][ny] = "."
                umbHp = d - 1
            } else {
                if (cur.umbrella > 0) {
                    umbHp--
                } else {
                    hp--
                }
            }
            if (hp == 0) continue
            if (vis[nx][ny] >= hp + umbHp) continue
            vis[nx][ny] = hp + umbHp
            q.offer(Pos(nx, ny, hp, umbHp, cur.dis + 1))
        }
    }

    print(-1)
}
