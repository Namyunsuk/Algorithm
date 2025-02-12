import java.util.*
import kotlin.math.*

class Pos(val x: Int, val y: Int)

const val WALL = 6
const val BLANK = 0
const val SEARCHED = -1

val dx = listOf(0, 1, 0, -1)
val dy = listOf(1, 0, -1, 0) // 상 우 하 좌
val cctvs = mutableListOf<Pos>()
lateinit var st: StringTokenizer
var n = 0
var m = 0

val graph = Array(9) { Array(9) { BLANK } }
val tmpGraph = Array(9) { Array(9) { BLANK } }
var result = Int.MAX_VALUE
var blanksCnt = 0
var searchedCnt = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()

    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            graph[i][j] = v
            if (v in 1..5) cctvs.add(Pos(i, j))
            else if (v == BLANK) blanksCnt++
        }
    }

    val cases = 4.0.pow(cctvs.size) - 1

    for (i in 0..cases.toInt()) {
        resetGraph()
        searchedCnt = 0
        var directions = (i).toString(4)
        while (cctvs.size != 0 && directions.length != cctvs.size) {
            directions = "0" + directions
        }
        for (j in cctvs.indices) {
            val pos = cctvs[j]
            val cctv = graph[pos.x][pos.y]
            val dir = directions[j].code - '0'.code

            if (cctv == 1) {
                search(tmpGraph, pos, dir)
            } else if (cctv == 2) {
                search(tmpGraph, pos, dir)
                search(tmpGraph, pos, dir + 2)

            } else if (cctv == 3) {
                search(tmpGraph, pos, dir)
                search(tmpGraph, pos, dir + 1)
            } else if (cctv == 4) {
                search(tmpGraph, pos, dir)
                search(tmpGraph, pos, dir + 1)
                search(tmpGraph, pos, dir + 2)
            } else if (cctv == 5) {
                search(tmpGraph, pos, dir)
                search(tmpGraph, pos, dir + 1)
                search(tmpGraph, pos, dir + 2)
                search(tmpGraph, pos, dir + 3)
            }
        }
        result = minOf(result, blanksCnt - searchedCnt)
    }
    print(result)
}

fun search(graph: Array<Array<Int>>, pos: Pos, direction: Int) {
    var x = pos.x
    var y = pos.y
    val dir = direction % 4
    while (true) {
        val nx = x + dx[dir]
        val ny = y + dy[dir]
        if (oob(nx, ny)) break
        if (graph[nx][ny] == WALL) break
        else if (graph[nx][ny] == BLANK) {
            graph[nx][ny] = SEARCHED
            searchedCnt++
        }
        x = nx
        y = ny
    }
}

fun oob(x: Int, y: Int): Boolean {
    return x < 0 || x >= n || y < 0 || y >= m
}

fun resetGraph() {
    for (i in 0 until n) {
        for (j in 0 until m) {
            tmpGraph[i][j] = graph[i][j]
        }
    }
}