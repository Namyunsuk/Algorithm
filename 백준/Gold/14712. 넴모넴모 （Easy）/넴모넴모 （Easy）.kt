import kotlin.math.*

data class Pos(val x: Int, val y: Int)

const val BLANK = 0
const val FILLED = 1
val board = Array(26) { Array(26) { BLANK } }
val arr = mutableListOf<Pos>()
var maxCnt = 0


fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    for (i in 0..<n) {
        for (j in 0..<m) {
            arr.add(Pos(i, j))
        }
    }

    dfs(0, n, m)

    print(maxCnt+1)
}

fun dfs(index: Int, n: Int, m: Int) {
    for (i in index..<n * m) {
        val pos = arr[i]
        if (board[pos.x][pos.y] == FILLED) continue
        board[pos.x][pos.y] = FILLED

        if (!findSquare(n, m)) {
            maxCnt++
        }
        dfs(i + 1, n, m)

        board[pos.x][pos.y] = BLANK
    }
}

fun findSquare(n: Int, m: Int): Boolean {
    for (i in 0..<n - 1) {
        for (j in 0..<m - 1) {
            if (board[i][j] == FILLED && board[i][j + 1] == FILLED &&
                board[i + 1][j] == FILLED && board[i + 1][j + 1] == FILLED
            ) return true
        }
    }
    return false
}
