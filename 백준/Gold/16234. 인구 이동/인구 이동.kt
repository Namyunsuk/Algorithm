import java.util.*
import kotlin.math.*

data class Pos(val x: Int, val y: Int)

val arr = Array(51) { Array(51) { 0 } }
val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, -1, 1)

var flag = true
var ans = 0

fun main() = with(System.`in`.bufferedReader()) {
    val (n, l, r) = readLine().split(" ").map { it.toInt() }

    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            arr[i][j] = v
        }
    }

    while (true) {
        flag = true
        bfs(n, l, r)
        if (flag) {
            print(ans)
            return
        }
        ans++
    }
}

fun bfs(n: Int, l: Int, r: Int) {
    val vis = Array(51) { Array(51) { false } }
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (vis[i][j]) continue
            val q = LinkedList<Pos>()
            val union = mutableSetOf<Pos>()
            var sum = 0
            var cnt = 0
            q.offer(Pos(i, j))
            vis[i][j] = true
            union.add(Pos(i, j))
            cnt++
            sum += arr[i][j]
            while (!q.isEmpty()) {
                val cur = q.poll()
                for (dir in 0..3) {
                    val nx = cur.x + dx[dir]
                    val ny = cur.y + dy[dir]

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue
                    if (vis[nx][ny]) continue
                    if (!isValidDiff(arr[cur.x][cur.y], arr[nx][ny], l, r)) continue
                    union.add(Pos(nx, ny))
                    q.offer(Pos(nx, ny))
                    cnt++
                    sum += arr[nx][ny]
                    vis[nx][ny] = true
                    flag = false
                }
            }
            val avg = sum / cnt
            union.forEach {
                arr[it.x][it.y] = avg
            }
        }
    }
}

fun isValidDiff(n1: Int, n2: Int, l: Int, r: Int): Boolean {
    return abs(n1 - n2) in l..r
}