import java.util.*

data class Pos(val x: Int, val y: Int)

const val NONE = 0
const val WALL = -1

val arr = Array(8) { Array(8) { 0 } }
val q = LinkedList<Pos>()
val wallsPos = mutableListOf<Pos>()

val dx = listOf(-1, 1, 0, 0, -1, 1, -1, 1, 0)
val dy = listOf(0, 0, -1, 1, -1, -1, 1, 1, 0)

fun main(): Unit = with(System.`in`.bufferedReader()) {
    repeat(8) { i ->
        val input = readLine().split("").filter { it.isNotBlank() }
        input.forEachIndexed { j, v ->
            if (v == "#") {
                arr[i][j] = WALL
                wallsPos.add(Pos(i, j))
            }
        }
    }

    q.offer(Pos(7, 0))
    while (!q.isEmpty()) {
        val vis = Array(8) { Array(8) { false } }

        for (j in 0..<q.size) {
            val cur = q.poll()
            if (arr[cur.x][cur.y] == WALL) continue
            if (cur.x == 0 && cur.y == 7) {
                print(1)
                return
            }
            for (i in 0..8) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]


                if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) continue
                if (arr[nx][ny] == WALL || vis[nx][ny]) continue
                q.offer(Pos(nx, ny))
                vis[nx][ny] = true
            }
        }
        moveWall()
    }
    print(0)
}

fun moveWall() {
    for (i in arr.size - 1 downTo 1) {
        for (j in arr[i].indices) {
            arr[i][j] = arr[i - 1][j]
        }
    }
    arr[0] = Array(arr[0].size) { 0 }
}