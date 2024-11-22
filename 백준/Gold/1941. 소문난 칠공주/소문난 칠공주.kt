import java.util.*

data class Pos(val x: Int, val y: Int, val group: String)

val board = Array(5) { Array(5) { "" } }
val arr = mutableListOf<Pos>()
val vis = Array(5) { Array(5) { false } }
var ans = 0
val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, -1, 1)

fun main(): Unit = with(System.`in`.bufferedReader()) {
    repeat(5) { i ->
        val input = readLine().split("").filter { it.isNotBlank() }
        input.forEachIndexed { j, v ->
            board[i][j] = v
            arr.add(Pos(i, j, v))
        }
    }

    dfs(0, mutableListOf(), 0, arr.size)

    print(ans)
}

fun dfs(index: Int, groups: MutableList<Pos>, sCount: Int, n: Int) {
    if (groups.size == 7) {
        if (sCount >= 4) {

            if (isConnect(groups)) {
                ans++
            }
        }
        return
    }
    for (i in index..<n) {
        groups.add(arr[i])
        if (arr[i].group == "S") {
            dfs(i + 1, groups, sCount + 1, n)
        } else {
            dfs(i + 1, groups, sCount, n)
        }
        groups.removeLast()
    }
}

fun isConnect(groups: MutableList<Pos>): Boolean {
    var count = 0
    val q = LinkedList<Pos>()
    q.offer(groups[0])
    vis[groups[0].x][groups[0].y] = true
    count++
    while (!q.isEmpty()) {
        val cur = q.poll()
        for (i in 0..<4) {
            val nx = cur.x + dx[i]
            val ny = cur.y + dy[i]

            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue
            if (vis[nx][ny]) continue
            if (!groups.contains(Pos(nx, ny, board[nx][ny]))) continue
            q.offer(Pos(nx, ny, board[nx][ny]))
            vis[nx][ny] = true
            count++
        }
    }


    for (i in 0..<5) {
        for (j in 0..<5) {
            vis[i][j] = false
        }
    }

    return count == 7
}