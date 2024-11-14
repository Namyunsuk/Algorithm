import java.util.*

data class Pos(val sword: Int, val x: Int, val y: Int)

val arr = Array(102) { Array(102) { 0 } }
val dist = Array(3) { Array(102) { Array(102) { 0 } } }
val vis = Array(3) { Array(102) { Array(102) { false } } }
val q = LinkedList<Pos>()
val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, -1, 1)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, t) = readLine().split(" ").map { it.toInt() }

    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            arr[i][j] = v
        }
    }

    q.offer(Pos(0, 0, 0))
    while (!q.isEmpty()) {
        val cur = q.poll()
        for (i in 0..3) {
            val sword = cur.sword
            val nx = cur.x + dx[i]
            val ny = cur.y + dy[i]

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue
            if (vis[sword][nx][ny] || dist[sword][nx][ny] > 0) continue
            if (nx == n - 1 && ny == m - 1) {
                val cnt = dist[sword][cur.x][cur.y] + 1
                if (cnt <= t) {
                    print(cnt)
                    return
                } else {
                    print("Fail")
                    return
                }
            }
            if (sword == 1) {
                dist[sword][nx][ny] = dist[sword][cur.x][cur.y] + 1
                q.offer(Pos(sword, nx, ny))
                vis[sword][nx][ny] = true
            } else {
                if (arr[nx][ny] == 1) continue
                if (arr[nx][ny] == 2) {
                    dist[1][nx][ny] = dist[sword][cur.x][cur.y] + 1
                    q.offer(Pos(1, nx, ny))
                    vis[1][nx][ny] = true
                } else {
                    dist[sword][nx][ny] = dist[sword][cur.x][cur.y] + 1
                    q.offer(Pos(sword, nx, ny))
                    vis[sword][nx][ny] = true
                }
            }
        }
    }
    print("Fail")
}
