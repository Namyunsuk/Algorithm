import java.util.*
import kotlin.system.*

class Pos(val col: Int, val row: Int)

var n = 0
var m = 0
var h = 0

val ladders = Array(32) { Array(12) { Array(12) { false } } }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    h = st.nextToken().toInt()

    if (m == 0) {
        print(0)
        return
    }

    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        ladders[a][b][b + 1] = true
        ladders[a][b + 1][b] = true
    }

    for (i in 0..3) {
        dfs(1, 0, i)
    }

    print(-1)
}

fun dfs(r: Int, cnt: Int, max: Int) {
    if (cnt > max) {
        return
    }

    if (check()) {
        print(cnt)
        exitProcess(0)
    }

    for (row in r..h) {
        for (i in 1 until n) {
            if (ladders[row][i][i + 1]) continue
            if (i + 2 <= n && ladders[row][i + 1][i + 2]) continue
            if (i - 1 >= 1 && ladders[row][i][i - 1]) continue
            ladders[row][i][i + 1] = true
            ladders[row][i + 1][i] = true
            dfs(row, cnt + 1, max)
            ladders[row][i][i + 1] = false
            ladders[row][i + 1][i] = false
        }
    }
}

fun check(): Boolean {
    for (i in 1..n) {
        if (!isValid(i)) return false
    }
    return true
}

fun isValid(col: Int): Boolean {
    var cur = Pos(col, 1)
    while (cur.row <= h) {
        if (cur.col + 1 <= n && ladders[cur.row][cur.col][cur.col + 1]) { // 좌 -> 우
            cur = Pos(cur.col + 1, cur.row + 1)
        } else if (cur.col - 1 >= 1 && ladders[cur.row][cur.col][cur.col - 1]) { // 좌 <- 우
            cur = Pos(cur.col - 1, cur.row + 1)
        } else {
            cur = Pos(cur.col, cur.row + 1)
        }
    }

    return cur.col == col
}
