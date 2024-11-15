import java.util.*

val walls = mutableListOf<Pos>()
val vis = Array(1001) { Array(1001) { false } }
val q = LinkedList<Pos>()
val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, -1, 1)

data class Pos(val x: Int, val y: Int, val dis: Int = 0)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            if (v == 1) {
                walls.add(Pos(i + 1, j + 1))
                vis[i + 1][j + 1] = true
            }
        }
    }

    val input = readLine().split(" ").map { it.toInt() }
    val (h, w) = input.subList(0, 2)
    val (sr, sc, fr, fc) = input.subList(2, 6)
    if (sr == fr && sc == fc) {
        print(0)
        return
    }
    q.offer(Pos(sr, sc))

    while (!q.isEmpty()) {
        val cur = q.poll()
        for (i in 0..3) {
            val nx = cur.x + dx[i]
            val ny = cur.y + dy[i]

            if (oob(nx, ny, h, w, n, m)) continue
            if (isWall(nx, ny, h, w)) continue
            if (nx == fr && ny == fc) {
                print(cur.dis + 1)
                return
            }
            if (vis[nx][ny]) continue

            q.offer(Pos(nx, ny, cur.dis + 1))
            vis[nx][ny] = true
        }
    }
    print(-1)
}

fun isWall(x: Int, y: Int, h: Int, w: Int): Boolean {
    var flag = false
    walls.forEach {
        val curX = it.x
        val curY = it.y
        if (curX in x..x + h - 1 && curY in y..y + w - 1) {
            flag = true
            return@forEach
        }
    }
    return flag
}


fun oob(x: Int, y: Int, h: Int, w: Int, n: Int, m: Int): Boolean {
    return check(x, y, n, m) || check(x + h - 1, y, n, m) ||
            check(x, y + w - 1, n, m) || check(x + h - 1, y + w - 1, n, m)
}

fun check(x: Int, y: Int, n: Int, m: Int): Boolean {
    return x < 1 || x > n || y < 1 || y > m
}