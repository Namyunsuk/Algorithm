import java.util.*

class Pos(val x: Int, val y: Int)

val graph = Array(12) { mutableListOf<String>() }
var result = 0
val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, -1, 1)

fun main(): Unit = with(System.`in`.bufferedReader()) {
    repeat(12) { i ->
        graph[i].addAll(readLine().split("").filter { it.isNotBlank() })
    }

    while (true) {
        if (!burst()) {
            print(result)
            return
        }
        moveDown()
        result++
    }
}

fun moveDown() {
    for (i in 11 downTo 0) {
        for (j in 0 until 6) {
            val cur = graph[i][j]
            if (cur == ".") continue
            for (d in 11 downTo i + 1) {
                if (graph[d][j] == ".") {
                    graph[d][j] = cur
                    graph[i][j] = "."
                    break
                }
            }
        }
    }
}

fun burst(): Boolean {
    val vis = Array(12) { Array(6) { false } }
    val removedBlocks = mutableListOf<Pos>()
    var flag = false

    for (i in 0 until 12) {
        for (j in 0 until 6) {
            if (graph[i][j] == "." || vis[i][j]) continue
            val q = LinkedList<Pos>()
            q.offer(Pos(i, j))
            removedBlocks.add(Pos(i, j))
            vis[i][j] = true

            while (!q.isEmpty()) {
                val cur = q.poll()
                for (dir in 0 until 4) {
                    val nx = cur.x + dx[dir]
                    val ny = cur.y + dy[dir]

                    if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue
                    if (vis[nx][ny]) continue
                    if (graph[nx][ny] != graph[i][j]) continue
                    q.offer(Pos(nx, ny))
                    removedBlocks.add(Pos(nx, ny))
                    vis[nx][ny] = true
                }
            }
            if (removedBlocks.size >= 4) {
                removedBlocks.forEach {
                    graph[it.x][it.y] = "."
                }
                flag = true
            }
            removedBlocks.clear()
        }
    }

    return flag
}

