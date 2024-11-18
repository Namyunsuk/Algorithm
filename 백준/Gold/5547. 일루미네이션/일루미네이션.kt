import java.util.*

data class Pos(val x: Int, val y: Int)

val q = LinkedList<Pos>()

lateinit var arr: Array<Array<Int>>
lateinit var vis: Array<Array<Boolean>>
var ans = 0
val tmp = mutableListOf<Pos>()

val odd_dx = listOf(0, 1, 0, 1, -1, 1)
val odd_dy = listOf(-1, -1, 1, 1, 0, 0)

val even_dx = listOf(-1, 0, -1, 0, -1, 1)
val even_dy = listOf(-1, -1, 1, 1, 0, 0)

fun main() = with(System.`in`.bufferedReader()) {
    val (m, n) = readLine().split(" ").map { it.toInt() }
    arr = Array(m + 2) { Array(n + 2) { 0 } }
    vis = Array(m + 2) { Array(n + 2) { false } }

    repeat(n) { j ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { i, v ->
            arr[i + 1][j + 1] = v

            if ((i + 1 == 1 || i + 1 == m || j + 1 == 1 || j + 1 == n)) {
                q.offer(Pos(i + 1, j + 1))
                if (v == 0) {
                    vis[i + 1][j + 1] = true
                }
            }
        }
    }

    while (!q.isEmpty()) {
        val cur = q.poll()

        for (i in 0..5) {
            var nx = 0
            var ny = 0
            if (cur.y % 2 == 1) { // y가 홀수일 경우
                nx = cur.x + odd_dx[i]
                ny = cur.y + odd_dy[i]
            } else { // y가 짝수일 경우
                nx = cur.x + even_dx[i]
                ny = cur.y + even_dy[i]
            }

            if (arr[cur.x][cur.y] == 1) {
                if (nx < 1 || nx > m || ny < 1 || ny > n) {
                    tmp.add(cur)
                    ans++
                }
                continue
            }
            if (nx < 1 || nx > m || ny < 1 || ny > n) continue
            if (vis[nx][ny]) continue
            if (arr[nx][ny] == 1) {
                tmp.add(Pos(nx, ny))
                ans++
                continue
            }
            q.offer(Pos(nx, ny))
            vis[nx][ny] = true
        }
    }

    print(ans)
}