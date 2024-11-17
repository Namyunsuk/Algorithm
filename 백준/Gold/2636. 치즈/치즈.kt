import java.util.*

data class Pos(val x: Int, val y: Int)

val arr = Array(100) { Array(100) { 0 } }
val vis = Array(100) { Array(100) { false } }
val q = LinkedList<Pos>()
val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, -1, 1)

var time = 0
var lastCnt = 0
var totalCnt = 0

fun main() = with(System.`in`.bufferedReader()) {
    val (m, n) = readLine().split(" ").map { it.toInt() }
    repeat(m) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            arr[i][j] = v
            if (v == 1) totalCnt++
            if ((i == 0 || j == 0 || i == m - 1 || j == n - 1) && v == 0) {
                q.offer(Pos(i, j))
                vis[i][j] = true
            }
        }
    }

    while (true) {
        if (totalCnt == 0) {
            println(time)
            println(lastCnt)
            return
        }
        time++
        lastCnt = totalCnt
        bfs(m, n)
    }
}

fun bfs(m: Int, n: Int) {
    val newAirs = mutableListOf<Pos>()

    while (!q.isEmpty()) {
        val cur = q.poll()
        for (i in 0..3) {
            val nx = cur.x + dx[i]
            val ny = cur.y + dy[i]

            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue
            if (vis[nx][ny]) continue
            if (arr[nx][ny] == 1) {
                arr[nx][ny] = 0
                vis[nx][ny] = true
                totalCnt--
                newAirs.add(Pos(nx, ny))
                continue
            }
            vis[nx][ny] = true
            q.offer(Pos(nx, ny))
        }
    }

    newAirs.forEach {
        q.offer(it)
    }
}