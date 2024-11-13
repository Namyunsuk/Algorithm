import java.util.*

data class Pos(val z: Int, val x: Int, val y: Int)

const val WALL = -1
const val EXIT = -2
val dz = listOf(0, 0, 0, 0, -1, 1)
val dx = listOf(-1, 1, 0, 0, 0, 0)
val dy = listOf(0, 0, -1, 1, 0, 0)
val q = LinkedList<Pos>()


fun main() = with(System.`in`.bufferedReader()) {
    while (true) {
        val arr = Array(32) { Array(32) { Array(32) { 0 } } }
        val vis = Array(32) { Array(32) { Array(32) { false } } }
        var exitFlag = false
        val (l, r, c) = readLine().split(" ").map { it.toInt() }
        if (l == 0 && r == 0 && c == 0) break

        repeat(l) { z ->
            repeat(r) { x ->
                val input = readLine().split("").filter { it.isNotBlank() }
                input.forEachIndexed { y, value ->
                    if (value == "S") {
                        q.offer(Pos(z, x, y))
                        vis[z][x][y] = true
                    } else if (value == "#") {
                        arr[z][x][y] = WALL
                        vis[z][x][y] = true
                    } else if (value == "E") {
                        arr[z][x][y] = EXIT
                    }
                }
            }
            readLine()
        }

        while (!q.isEmpty()) {
            val cur = q.poll()

            for (i in 0..5) {
                val nz = cur.z + dz[i]
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]

                if (nz < 0 || nz >= l || nx < 0 || nx >= r || ny < 0 || ny >= c) continue
                if (arr[nz][nx][ny] == EXIT) {
                    println("Escaped in ${arr[cur.z][cur.x][cur.y] + 1} minute(s).")
                    exitFlag = true
                    break
                }
                if (arr[nz][nx][ny] == WALL || vis[nz][nx][ny]) continue
                arr[nz][nx][ny] = arr[cur.z][cur.x][cur.y] + 1
                vis[nz][nx][ny] = true
                q.offer(Pos(nz, nx, ny))
            }

            if (exitFlag) break
        }

        if (!exitFlag) {
            println("Trapped!")
        }
        q.clear()
    }
}
