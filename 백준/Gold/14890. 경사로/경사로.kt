import kotlin.math.*
import java.util.*

class Pos(val x: Int, val y: Int)

val graph = Array(101) { Array(101) { 0 } }
var isBridged = Array(101) { Array(101) { false } }
var n = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    n = st.nextToken().toInt()
    val l = st.nextToken().toInt()
    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            graph[i][j] = v
        }
    }

    var result = 0

    for (i in 0 until n) {
        var flag = true
        for (j in 0 until n) {
            if (j == n - 1) break
            val cur = Pos(i, j)
            val next = Pos(i, j + 1)
            if (graph[cur.x][cur.y] == graph[next.x][next.y]) continue
            if (abs(graph[cur.x][cur.y] - graph[next.x][next.y]) >= 2) {
                flag = false
                break
            }
            if (!canBridge(cur, next, l, false)) {
                flag = false
                break
            }
            setBridge(cur, next, l, false)
        }
        if (flag) result++
    }

    isBridged = Array(101) { Array(101) { false } }

    for (j in 0 until n) {
        var flag = true
        for (i in 0 until n) {
            if (i == n - 1) break
            val cur = Pos(i, j)
            val next = Pos(i + 1, j)
            if (graph[cur.x][cur.y] == graph[next.x][next.y]) continue
            if (abs(graph[cur.x][cur.y] - graph[next.x][next.y]) >= 2) {
                flag = false
                break
            }
            if (!canBridge(cur, next, l, true)) {
                flag = false
                break
            }
            setBridge(cur, next, l, true)
        }
        if (flag) result++
    }

    println(result)
}

fun canBridge(cur: Pos, next: Pos, l: Int, isCol: Boolean): Boolean {
    if (graph[cur.x][cur.y] < graph[next.x][next.y]) { // 올라갈 때
        for (i in 0 until l) {
            if (isCol) {
                if (cur.x - i < 0) return false
                if (graph[cur.x - i][cur.y] != graph[cur.x][cur.y]) return false
                if (isBridged[cur.x - i][cur.y]) return false
            } else {
                if (cur.y - i < 0) return false
                if (graph[cur.x][cur.y - i] != graph[cur.x][cur.y]) return false
                if (isBridged[cur.x][cur.y - i]) return false
            }
        }
    } else { // 내려갈 때
        for (i in 0 until l) {
            if (isCol) {
                if (next.x + i >= n) return false
                if (graph[next.x + i][next.y] != graph[next.x][next.y]) return false
                if (isBridged[next.x + i][next.y]) return false
            } else {
                if (next.y + i >= n) return false
                if (graph[next.x][next.y + i] != graph[next.x][next.y]) return false
                if (isBridged[next.x][next.y + i]) return false
            }
        }
    }

    return true
}

fun setBridge(cur: Pos, next: Pos, l: Int, isCol: Boolean) {
    if (graph[cur.x][cur.y] < graph[next.x][next.y]) { // 올라갈 때
        for (i in 0 until l) {
            if (isCol) {
                isBridged[cur.x - i][cur.y] = true
            } else {
                isBridged[cur.x][cur.y - i] = true
            }
        }
    } else { // 내려갈 때
        for (i in 0 until l) {
            if (isCol) {
                isBridged[next.x + i][next.y] = true
            } else {
                isBridged[next.x][next.y + i] = true
            }
        }
    }
}