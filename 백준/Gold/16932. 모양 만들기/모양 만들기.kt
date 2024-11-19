import java.util.*
import kotlin.math.*

data class Pos(val x: Int, val y: Int)

lateinit var arr: Array<Array<Int>>
val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, -1, 1)
val blanks = mutableListOf<Pos>()
var maxSize = 0
val cntPerGroup = Array(1000000) { 0 }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = Array(n) { Array(m) { 0 } }

    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            arr[i][j] = v
            if (v == 0) blanks.add(Pos(i, j))
        }
    }

    grouping(n, m)

    for (blank in blanks) {
        var cnt = 0
        val nearByGroup = mutableSetOf<Int>()
        for (i in 0..<4) {
            val nx = blank.x + dx[i]
            val ny = blank.y + dy[i]

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue
            if (arr[nx][ny] == 0) continue
            nearByGroup.add(arr[nx][ny])
        }
        nearByGroup.forEach {
            cnt += cntPerGroup[it]
        }
        maxSize = max(maxSize, cnt + 1)
    }

    print(maxSize)
}

fun grouping(n: Int, m: Int) {
    val vis = Array(n) { Array(m) { false } }
    var groupNum = 1

    for (i in 0..<n) {
        for (j in 0..<m) {
            if (vis[i][j] || arr[i][j] == 0) continue
            var cnt = 0
            val q = LinkedList<Pos>()
            q.offer(Pos(i, j))
            arr[i][j] = groupNum
            vis[i][j] = true
            cnt++

            while (!q.isEmpty()) {
                val cur = q.poll()
                for (dir in 0..<4) {
                    val nx = cur.x + dx[dir]
                    val ny = cur.y + dy[dir]
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue
                    if (arr[nx][ny] == 0 || vis[nx][ny]) continue
                    q.offer(Pos(nx, ny))
                    arr[nx][ny] = groupNum
                    vis[nx][ny] = true
                    cnt++
                }
            }
            cntPerGroup[groupNum] = cnt
            groupNum++
        }
    }
}