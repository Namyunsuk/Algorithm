import java.util.*

private const val SQUARE = -1
val board = Array(102) { Array(102) { 0 } }
val vis = Array(102) { Array(102) { false } }
val q = LinkedList<Pair<Int, Int>>()
val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, -1, 1)
var cnt = 0
var widths = mutableListOf<Int>()

fun main() = with(System.`in`.bufferedReader()) {
    val (m, n, k) = readLine().split(" ").map { it.toInt() }

    repeat(k) {
        val (y1, x1, y2, x2) = readLine().split(" ").map { it.toInt() }
        for (i in x1..x2 - 1) {
            for (j in y1..y2 - 1) {
                board[i][j] = SQUARE
                vis[i][j] = true
            }
        }
    }

    for (i in 0 until m) {
        for (j in 0 until n) {
            var width = 0
            if (board[i][j] == SQUARE || vis[i][j]) continue
            q.offer(i to j)
            vis[i][j] = true
            width++
            while (!q.isEmpty()) {
                val cur = q.poll()
                for (dir in 0..3) {
                    val nx = cur.first + dx[dir]
                    val ny = cur.second + dy[dir]

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue
                    if (board[nx][ny] == SQUARE || vis[nx][ny]) continue
                    q.offer(nx to ny)
                    vis[nx][ny] = true
                    width++
                }
            }
            if (width > 0) {
                widths.add(width)
                cnt++
            }
        }
    }

    widths.sortBy { it }

    println(cnt)
    widths.forEach { print("$it ") }
}

